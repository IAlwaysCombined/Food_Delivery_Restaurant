package com.example.food_delivery_restaurant.ui.dishes_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food_delivery_restaurant.R
import com.example.food_delivery_restaurant.database.*
import com.example.food_delivery_restaurant.databinding.FragmentDishesListBinding
import com.example.food_delivery_restaurant.model.CommonModel
import com.example.food_delivery_restaurant.utilities.AppValueEventListener
import com.google.firebase.database.DatabaseReference


class DishesListFragment : Fragment(R.layout.fragment_dishes_list) {

    private lateinit var binding: FragmentDishesListBinding
    private lateinit var adapter: DishesListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var refDishes: DatabaseReference
    private lateinit var dishesListener: AppValueEventListener
    private var dishesList = mutableListOf<CommonModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDishesListBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.dishesListFragmentTextViewDescription.text = getString(R.string.dishes_list_menu_item)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = binding.dishesListFragmentRecyclerView
        adapter = DishesListAdapter(mutableListOf())
        refDishes = REF_DATABASE_ROOT.child(NODE_DISHES).child(UID)
        recyclerView.adapter = adapter
        dishesListener = AppValueEventListener { dataSnapshot ->
            dishesList = dataSnapshot.children.map { it.getCommonModel() } as MutableList<CommonModel>
            adapter.setList(dishesList)
            recyclerView.smoothScrollToPosition(adapter.itemCount)
        }
        refDishes.addValueEventListener(dishesListener)
    }
}