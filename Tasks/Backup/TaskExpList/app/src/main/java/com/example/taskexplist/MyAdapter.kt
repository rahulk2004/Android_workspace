package com.example.taskexplist

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MyAdapter(var context:Context,val expandableListTitle: List<String>,val expandableListDetail: HashMap<String, List<String>>) :BaseExpandableListAdapter()
{

    private var selectedChildPosition: Pair<Int, Int>? = null // Track selected child

    override fun getGroupCount(): Int
    {
        return expandableListTitle.size
    }

    override fun getChildrenCount(groupPosition: Int): Int
    {

        return  expandableListTitle.size
    }

    override fun getGroup(groupPosition: Int): Any
    {

        return expandableListTitle[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {

        return expandableListDetail[expandableListTitle[groupPosition]]!!.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {

        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long
    {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean
    {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View
    {
        var convertView = convertView
        val listTitle = getGroup(groupPosition) as String
        if (convertView == null)
        {
            val layoutInflater: LayoutInflater = LayoutInflater.from(context)
            //  Context.LAYOUT_INFLATER_SERVICE as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_group, null)
        }
        val listTitleTextView = convertView!!.findViewById<View>(R.id.listTitle) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View
    {
        var convertView = convertView
        val expandedListText = getChild(groupPosition, childPosition) as String
        if (convertView == null) {
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_item, null)
        }
        val expandedListTextView = convertView!!.findViewById<View>(R.id.expandedListItem) as TextView
        expandedListTextView.text = expandedListText





        // Get the CardView to change background color
        val cardView = convertView.findViewById<CardView>(R.id.main)

        // Change background color if this item is selected
        if (selectedChildPosition == Pair(groupPosition, childPosition)) {
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.ls))
        } else {
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
        }

        convertView.setOnClickListener {
            selectedChildPosition = Pair(groupPosition, childPosition)
            notifyDataSetChanged() // Refresh the list to update backgrounds
        }




        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean
    {
        return false
    }

}