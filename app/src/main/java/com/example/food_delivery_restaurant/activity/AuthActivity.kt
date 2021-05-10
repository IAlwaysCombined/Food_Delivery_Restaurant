package com.example.food_delivery_restaurant.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.food_delivery_restaurant.MainActivity
import com.example.food_delivery_restaurant.R
import com.example.food_delivery_restaurant.database.*
import com.example.food_delivery_restaurant.databinding.ActivityAuthBinding
import com.example.food_delivery_restaurant.utilities.AppValueEventListener
import com.example.food_delivery_restaurant.utilities.replaceActivity
import com.example.food_delivery_restaurant.utilities.showToast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.authActivityBtnEnter.setOnClickListener { enterAccount() }
        binding.authActivityTextViewRestorePassword.setOnClickListener { restorePassword() }

    }

    //Restore password
    private fun restorePassword() {
        val emailRestaurant = binding.textInputEdtEmail.text.toString()
        if (emailRestaurant.isEmpty()) {
            showToast(getString(R.string.auth_activity_toast_email_fill))
            return
        }
        AUTH.sendPasswordResetEmail(emailRestaurant)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) showToast(getString(R.string.auth_activity_toast_url_send_email))
                else showToast(getString(R.string.something_went_wrong))
            }
    }

    //Change email and pass user and replace Activity
    private fun enterAccount() {
        val emailRestaurant = binding.textInputEdtEmail.text.toString()
        val passwordRestaurant = binding.textInputEdtPassword.text.toString()
        if (emailRestaurant.isEmpty()) {
            showToast(getString(R.string.auth_activity_toast_email_fill))
            return
        }
        else if (passwordRestaurant.isEmpty()) {
            showToast(getString(R.string.auth_activity_toast_password_fill))
            return
        }
        AUTH.signInWithEmailAndPassword(binding.textInputEdtEmail.text.toString(), binding.textInputEdtPassword.text.toString())
            .addOnCompleteListener { task ->
                val uid = AUTH.currentUser?.uid.toString()
                REF_DATABASE_ROOT.child(NODE_RESTAURANT).child(uid)
                    .addValueEventListener(object: ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val roleUser = snapshot.child(CHILD_ROLE).value.toString()
                            if (task.isSuccessful && roleUser == RESTAURANT_ROLE){
                                replaceActivity(MainActivity())
                            }
                            else{
                                showToast(getString(R.string.something_went_wrong))
                                AUTH.signOut()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            showToast(getString(R.string.something_went_wrong))
                        }
                    })

            }
    }


}