package com.example.food_delivery_restaurant.ui.dishes_list

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_delivery_restaurant.R
import com.example.food_delivery_restaurant.model.CommonModel
import com.example.food_delivery_restaurant.utilities.downloadAndSetImage

class DishesListAdapter(private var dishesList: MutableList<CommonModel>):
    RecyclerView.Adapter<DishesListAdapter.DishesHolder>() {

    class DishesHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val nameDishes: TextView? = itemView.findViewById(R.id.name_dishes)
    val coastDishes: TextView? = itemView.findViewById(R.id.coast_dishes)
    val imageDishes: ImageView? = itemView.findViewById(R.id.image_dishes)
    }

    override fun getItemCount() = dishesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_dishes, parent, false)
        return DishesListAdapter.DishesHolder(itemView)
    }

    override fun onBindViewHolder(holder: DishesHolder, position: Int) {
        holder.nameDishes?.text = dishesList[position].name_dishes
        holder.coastDishes?.text = dishesList[position].coast_dishes
        holder.imageDishes?.downloadAndSetImage(dishesList[position].image_dishes)
    }

    fun setList(list: List<CommonModel>){
        dishesList = list as MutableList<CommonModel>
        notifyDataSetChanged()
    }
}