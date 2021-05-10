package com.example.food_delivery_restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.food_delivery_restaurant.activity.AuthActivity
import com.example.food_delivery_restaurant.database.AUTH
import com.example.food_delivery_restaurant.database.initCommon
import com.example.food_delivery_restaurant.database.initFirebase
import com.example.food_delivery_restaurant.databinding.ActivityMainBinding
import com.example.food_delivery_restaurant.ui.`object`.AppDrawer
import com.example.food_delivery_restaurant.ui.account.AccountFragment
import com.example.food_delivery_restaurant.utilities.APP_ACTIVITY
import com.example.food_delivery_restaurant.utilities.replaceActivity
import com.example.food_delivery_restaurant.utilities.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
    }

    override fun onResume() {
        super.onResume()
        initFields()
        initFunc()
        initCommon()
    }

    private fun initFields(){
        appDrawer = AppDrawer(binding)
        initFirebase()
    }

    private fun initFunc(){
        if(AUTH.currentUser != null){
            appDrawer.create()
            replaceFragment(AccountFragment())
        }
        else{
            replaceActivity(AuthActivity())
            AUTH.signOut()
        }
    }
}