package com.example.food_delivery_restaurant.ui.`object`

import com.example.food_delivery_restaurant.R
import com.example.food_delivery_restaurant.databinding.ActivityMainBinding
import com.example.food_delivery_restaurant.ui.account.AccountFragment
import com.example.food_delivery_restaurant.ui.add_dishes.AddDishesFragment
import com.example.food_delivery_restaurant.ui.dishes_list.DishesListFragment
import com.example.food_delivery_restaurant.ui.order_list.OrderListFragment
import com.example.food_delivery_restaurant.utilities.APP_ACTIVITY
import com.example.food_delivery_restaurant.utilities.replaceFragment

class AppDrawer(private val binding: ActivityMainBinding) {

    fun create(){
        createBottomNav()
    }

    private fun createBottomNav() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.restaurant_account -> APP_ACTIVITY.replaceFragment(AccountFragment())
                R.id.restaurant_dishes_list -> APP_ACTIVITY.replaceFragment(DishesListFragment())
                R.id.restaurant_add_dishes -> APP_ACTIVITY.replaceFragment(AddDishesFragment())
                R.id.restaurant_order_list -> APP_ACTIVITY.replaceFragment(OrderListFragment())
            }
            true
        }
    }

}