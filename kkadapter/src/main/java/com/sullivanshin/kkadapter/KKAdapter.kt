package com.sullivanshin.kkadapter

import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.concurrent.atomic.AtomicInteger
import kotlin.reflect.KClass
import kotlin.reflect.full.superclasses

class KKAdapter constructor(private val layoutMap: Map<KClass<*>, Int>, private var items: List<*>) : RecyclerView.Adapter<KKAdapter.VH>() {

    private val viewTypeLayoutMap = mutableMapOf<Int, Int>()
    private val classViewTypeMap = mutableMapOf<KClass<*>, Int>()
    private val incremental = AtomicInteger(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        LayoutInflater.from(parent.context)
                .inflate(viewTypeLayoutMap[viewType]!!, parent, false)
                .let { return VH(it) }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        if (item is ViewInjector) {
            item.inject(holder)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position] as Any
        val viewTypeId = classViewTypeMap.getOrPut(item::class, { incremental.getAndIncrement() })

        viewTypeLayoutMap.getOrPut(viewTypeId, {
            layoutMap[item::class]
                    ?: item::class.superclasses.firstOrNull { layoutMap[it] != null }?.let { layoutMap[it] }
                    ?: throw IllegalStateException("can't resolve view type ${item::class}")
        })

        return viewTypeId
    }

    class Builder {
        private val layoutMap = mutableMapOf<KClass<*>, Int>()
        fun register(clazz: KClass<*>, layoutId: Int): Builder {
            layoutMap[clazz] = layoutId
            return this
        }

        fun build(list: List<*>): KKAdapter {
            return KKAdapter(layoutMap, list)
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val intMap = mutableMapOf<Int, View>()

        @Suppress("UNCHECKED_CAST")
        fun <T : View> present(@IdRes id: Int, function: T.() -> Unit) {
            val view = intMap.getOrPut(id) {
                itemView.findViewById(id)
                        ?: throw IllegalArgumentException("can't find view " + itemView.resources.getResourceName(id))
            }
            (view as T).function()
        }
    }

    interface ViewInjector {
        fun inject(viewHolder: VH)
    }
}




