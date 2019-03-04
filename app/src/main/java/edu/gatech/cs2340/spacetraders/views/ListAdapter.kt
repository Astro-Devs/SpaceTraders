package edu.gatech.cs2340.spacetraders.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.SolarSystem

class ListAdapter(context: Context, myList: ArrayList<SolarSystem>) : BaseAdapter() {


    class ListViewHolder: LinearLayout {
        private var myContext: Context

        constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
            myContext = context
            setup()
        }

        constructor(context: Context) : super(context) {
            myContext = context
            setup()
        }

        private fun setup() {
            var inflater = myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.universe_card, this)
        }

    }
    private var myList = myList
    private var context = context

    override fun getCount(): Int {
        return myList.size
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any {
        return myList.get(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView as ListViewHolder
        if (view == null) {
            view = ListViewHolder(context)
        }
        return view
    }

}