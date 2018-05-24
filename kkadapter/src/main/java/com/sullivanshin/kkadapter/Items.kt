package com.sullivanshin.kkadapter

import android.graphics.drawable.Drawable
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView

@ViewLayout(android.R.layout.simple_list_item_1)
class SimpleListItem1(private val value : String) : KKAdapter.ViewInjector {
    override fun inject(viewHolder: KKAdapter.VH) {
        viewHolder.present<TextView>(android.R.id.text1) {
            text = value
        }
    }
}

@ViewLayout(android.R.layout.simple_list_item_2)
class SimpleListItem2(private val value : String, private val value2 : String) : KKAdapter.ViewInjector {
    override fun inject(viewHolder: KKAdapter.VH) {
        viewHolder.present<TextView>(android.R.id.text1) {
            text = value
        }
        viewHolder.present<TextView>(android.R.id.text2) {
            text = value2
        }
    }
}

@ViewLayout(android.R.layout.simple_list_item_single_choice)
class SimpleListItemSingleChoice(private val value : String, private val predicate : () -> Boolean) : KKAdapter.ViewInjector {
    override fun inject(viewHolder: KKAdapter.VH) {
        viewHolder.present<CheckedTextView>(android.R.id.text1) {
            text = value
            isChecked = predicate()
        }
    }
}

@ViewLayout(android.R.layout.simple_list_item_multiple_choice)
class SimpleListItemMultipleChoice(private val value : String, private val predicate : () -> Boolean) : KKAdapter.ViewInjector {
    override fun inject(viewHolder: KKAdapter.VH) {
        viewHolder.present<CheckedTextView>(android.R.id.text1) {
            text = value
            isChecked = predicate()
        }
    }
}