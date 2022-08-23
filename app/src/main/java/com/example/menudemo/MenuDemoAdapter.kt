package com.example.menudemo

import android.view.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.menudemo.databinding.SimpleItemBinding

class MenuDemoAdapter(val stringList: List<String>) : RecyclerView.Adapter<MenuDemoAdapter.MenuHolder>() {

    var onClickFunction: ((view: View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SimpleItemBinding.inflate(inflater, parent, false)
        return MenuHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.bind(stringList[position])
    }

    override fun getItemCount() = stringList.size

    class MenuHolder(private val binding: SimpleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(textToDisplay: String) {

            binding.textView.text = textToDisplay
            binding.button.setOnCreateContextMenuListener { menu, _, _ ->
                onCreateContextMenu?.invoke(menu, adapterPosition ,textToDisplay)
            }
            binding.button.setOnClickListener {
                it.showContextMenu(it.x,it.y)
            }
        }
    }
}

var onCreateContextMenu: ((menu: ContextMenu, adapterPosition: Int, str: String) -> Unit)? = null

