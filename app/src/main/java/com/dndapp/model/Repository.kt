package com.dndapp.model

interface Repository<T> {

    fun loadAll(): List<T>
}