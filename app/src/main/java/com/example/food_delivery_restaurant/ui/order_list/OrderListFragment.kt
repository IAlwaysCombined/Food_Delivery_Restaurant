package com.example.food_delivery_restaurant.ui.order_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.food_delivery_restaurant.R
import com.example.food_delivery_restaurant.databinding.FragmentOrderListBinding

class OrderListFragment : Fragment(R.layout.fragment_order_list) {

    private lateinit var binding: FragmentOrderListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderListBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.orderListFragmentTextViewDescription.text = getString(R.string.order_list_menu_item)
    }
}