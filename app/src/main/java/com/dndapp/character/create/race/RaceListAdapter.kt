package com.dndapp.character.create.race

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.dndapp.R
import com.dndapp.model.race.Race

class RaceListAdapter(private val context: Context) : BaseAdapter() {

    private var items: List<Race> = emptyList()

    fun setItems(list: List<Race>) {
        items = list
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val race = items[position]
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_race, parent, false)
        (view as? TextView)?.text = race.name
        return view
    }

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = items[position].id.hashCode().toLong()

    override fun getCount(): Int = items.size
}