package com.sullivanshin.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.sullivanshin.kkadapter.KKAdapter
import com.sullivanshin.kkadapter.ViewLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = listOf(
                SimpleTextView("Hello World"),
                SimpleTextView("Hello World 2"),
                TwoLineTextView("Terraria", "awesome game"),
                TwoLineTextView("Dark Soul", "God game")
        )
        recyclerView.adapter = KKAdapter(data)

    }

    @ViewLayout(android.R.layout.simple_list_item_1)
    class SimpleTextView(val title: String) : KKAdapter.ViewInjector {
        override fun inject(viewHolder: KKAdapter.VH) {
            viewHolder.present<TextView>(android.R.id.text1) {
                text = title
            }
        }
    }

    @ViewLayout(android.R.layout.simple_list_item_2)
    class TwoLineTextView(val title: String, val description: String) : KKAdapter.ViewInjector {
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
