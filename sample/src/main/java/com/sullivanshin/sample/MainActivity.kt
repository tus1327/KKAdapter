package com.sullivanshin.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sullivanshin.kkadapter.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = KKAdapter(listOf(
                SimpleListItem1("Hello World"),
                SimpleListItem1("Hello World 2"),
                SimpleListItem2("Terraria", "awesome game"),
                SimpleListItem2("Dark Soul", "God game"),
                SimpleListItemSingleChoice("Choice Group 1 : A") { true },
                SimpleListItemSingleChoice("Choice Group 1 : B") { false },
                SimpleListItemMultipleChoice("Choice Group 2 : A") { true },
                SimpleListItemMultipleChoice("Choice Group 2 : B") { false }
        ))
    }

}
