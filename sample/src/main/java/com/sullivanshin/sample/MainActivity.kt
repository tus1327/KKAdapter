package com.sullivanshin.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.sullivanshin.kkadapter.KKAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutMap: Map<KClass<*>, Int> = mapOf(
                SimpleTextView::class to SimpleTextView.LAYOUT_ID,
                TwoLineTextView::class to TwoLineTextView.LAYOUT_ID
        )
        val data = listOf(
                SimpleTextView("Hello World"),
                SimpleTextView("Hello World 2"),
                TwoLineTextView("Terraria" , "awesome game"),
                TwoLineTextView("Dark Soul", "God game")
        )
        recyclerView.adapter = KKAdapter(layoutMap, data)
    }

    class SimpleTextView(val title: String) : KKAdapter.ViewInjector {
        companion object {
            const val LAYOUT_ID = android.R.layout.simple_list_item_1
        }

        override fun inject(viewHolder: KKAdapter.VH) {
            viewHolder.present<TextView>(android.R.id.text1) {
                text = title
            }
        }
    }

    class TwoLineTextView(val title: String, val description: String) : KKAdapter.ViewInjector {
        companion object {
            const val LAYOUT_ID = android.R.layout.simple_list_item_2
        }

        override fun inject(viewHolder: KKAdapter.VH) {
            viewHolder.present<TextView>(android.R.id.text1) {
                text = title
            }
            viewHolder.present<TextView>(android.R.id.text2) {
                text = description
            }
        }
    }
}
