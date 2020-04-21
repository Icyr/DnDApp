package com.dndapp.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.ref.Reference
import java.lang.ref.WeakReference
import java.util.*

class BaseObservableLiveData<T : BaseObservable>(initialValue: T) : MutableLiveData<T>(initialValue) {
    init {
        initialValue.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                postValue(value)
            }
        })
    }
}

/**
 * The original idea is taken from https://proandroiddev.com/singleliveevent-to-help-you-work-with-livedata-and-events-5ac519989c70 (https://github.com/android/architecture-samples/blob/dev-todo-mvvm-live/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/SingleLiveEvent.java)
 * But the implementation from the article is poor:
 * - [LiveData.postValue], [LiveData.removeObserver], [LiveData.observeForever] are not implemented,
 * - contract for [LiveData.observe] is violated: no check for duplicate observers,
 * - contains redundant AtomicBoolean synchronization.
 * So we reimplement it in a more robust way.
 *
 * The original problem is that [LiveData] observers are notified on every configuration change. So, for example,
 * multiple error dialogs are shown on screen rotations.
 * The solution is to consume "event" (notify observers about value changed) only once per event. This is because we cannot distinguish
 * two different [LifecycleOwner]s (activity, fragment, [Fragment.getViewLifecycleOwner], e.g. two fragments are watching
 * the same background process), that we would rather notify if we can, from two instances of "the same" UI controller
 * before and after configuration changes, that we need to avoid been notified.
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    /**
     * Currently [LiveData.postValue] calls [LiveData.setValue]. But we cannot rely on that internal implementation details,
     * because that is not a part of the public contract.
     * Calls to [LiveData.postValue] and [LiveData.setValue] are asynchronous, dispatch ordering is unspecified and unpredictable,
     * sequential [LiveData.postValue] values may be merged, values may be not dispatched if observer is inactive.
     * It's not easy if ever possible to manage this through process synchronization. That's why we introduce
     * [MutableLiveData] delegate to keep the "consumed" flag ([ConsumableValue.consumed]) inside events.
     */
    private val delegate = MutableLiveData<ConsumableValue<T>>()

    /**
     * WeakHashMap's values are wrapped within WeakReferences to not strongly refer to keys.
     */
    private val observer2singleConsumeObserver: MutableMap<Observer<in T>, Reference<SingleConsumeObserver<T>>> =
        WeakHashMap()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        // Normally no observers must be here, because on configuration changes the previous being destroyed LifecycleOwner
        // observer is removed at this point. But we don't throw an IllegalStateException here to avoid
        // random sporadic crashes, because in Android all kinds of oddities happens. For example, for activity transitions
        // both instances of the same activity (more accurately, the same activity record) may be in active state at the same moment.
        if (hasObservers()) {
            Log.e(
                SingleLiveEvent::class.java.canonicalName,
                "Registering multiple observers - only unpredictable one will be notified!"
            )
        }

        val singleConsumeObserver = SingleConsumeObserver(observer)
        delegate.observe(owner, singleConsumeObserver)
        observer2singleConsumeObserver.getOrPut(observer) { WeakReference(singleConsumeObserver) }
    }

    @MainThread
    override fun observeForever(observer: Observer<in T>) {
        if (hasObservers()) {
            Log.e(
                SingleLiveEvent::class.java.canonicalName,
                "Registering multiple observers - only unpredictable one will be notified!"
            )
        }

        val singleConsumeObserver = SingleConsumeObserver(observer)
        delegate.observeForever(singleConsumeObserver)
        observer2singleConsumeObserver.getOrPut(observer) { WeakReference(singleConsumeObserver) }
    }

    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        observer2singleConsumeObserver[observer]?.get()?.let {
            delegate.removeObserver(it)
        }
    }

    @MainThread
    override fun removeObservers(owner: LifecycleOwner) {
        delegate.removeObservers(owner)
    }

    @MainThread
    override fun setValue(value: T?) {
        delegate.value = ConsumableValue(value)
    }

    override fun postValue(value: T) {
        delegate.postValue(ConsumableValue(value))
    }

    override fun getValue(): T? {
        return delegate.value?.value
    }

    override fun hasActiveObservers(): Boolean {
        return delegate.hasActiveObservers()
    }

    override fun hasObservers(): Boolean {
        return delegate.hasObservers()
    }
}

private class ConsumableValue<T>(val value: T?) {

    var consumed = false
}

/**
 * The class is data class to utilize observer's equal() method.
 */
private data class SingleConsumeObserver<T>(val observer: Observer<in T>) : Observer<ConsumableValue<T>> {

    override fun onChanged(consumableValue: ConsumableValue<T>) {
        if (!consumableValue.consumed) {
            consumableValue.consumed = true
            observer.onChanged(consumableValue.value)
        }
    }
}
