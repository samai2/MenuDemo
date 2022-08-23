package com.example.menudemo

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.ContextMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.menudemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var bidnig: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bidnig = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bidnig.root)
        setupAdapter()
    }

    private fun setupAdapter() {
        val list = listOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")
        bidnig.recyclerView.adapter = MenuDemoAdapter(list)
        onCreateContextMenu = createMenu
    }

    private val createMenu = { menu: ContextMenu, adapterPosition: Int, stringToDisplay: String ->

        val itemWithtext = menuIconWithText(
            AppCompatResources.getDrawable(
                this,
                com.google.android.material.R.drawable.ic_clock_black_24dp
            ), stringToDisplay
        )
        val item = menu.add(0, 123456, 0, itemWithtext).setOnMenuItemClickListener {
            true
        }
        item.setOnMenuItemClickListener {
            Toast.makeText(this, "MAKE ACTION YOU NEED", Toast.LENGTH_SHORT).show()
            true
        }
        Unit
    }

    private fun menuIconWithText(r: Drawable?, title: String): CharSequence {
        r ?: return ""
        r.setBounds(0, 0, r.intrinsicWidth, r.intrinsicHeight)
        val sb = SpannableString("    $title")
        val imageSpan = ImageSpan(r, ImageSpan.ALIGN_BOTTOM)
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return sb
    }
}