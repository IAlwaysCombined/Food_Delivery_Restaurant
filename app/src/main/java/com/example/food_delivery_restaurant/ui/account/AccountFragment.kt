package com.example.food_delivery_restaurant.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.food_delivery_restaurant.R
import com.example.food_delivery_restaurant.activity.AuthActivity
import com.example.food_delivery_restaurant.database.AUTH
import com.example.food_delivery_restaurant.database.COMMON
import com.example.food_delivery_restaurant.databinding.FragmentAccountBinding
import com.example.food_delivery_restaurant.utilities.replaceActivity


class AccountFragment : Fragment(R.layout.fragment_account) {

    private lateinit var binding: FragmentAccountBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.accountFragmentBtnExit.setOnClickListener { exitBtn() }
        initFields()
    }

    private fun initFields() {
        binding.accountFragmentTextViewName.text = COMMON.name_dishes
        binding.accountFragmentImageViewRestaurantImage
    }

    //Exit account button
    private fun exitBtn() {
        replaceActivity(AuthActivity())
        AUTH.signOut()
    }
}