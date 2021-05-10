package com.example.food_delivery_restaurant.ui.add_dishes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.food_delivery_restaurant.R
import com.example.food_delivery_restaurant.database.*
import com.example.food_delivery_restaurant.databinding.FragmentAddDishesBinding
import com.example.food_delivery_restaurant.utilities.downloadAndSetImage
import com.example.food_delivery_restaurant.utilities.showToast


@Suppress("NAME_SHADOWING")
class AddDishesFragment : Fragment(R.layout.fragment_add_dishes) {

    private lateinit var binding: FragmentAddDishesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddDishesBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        if (COMMON.image_dishes.isNotEmpty()) {
            binding.addDishesFragmentImageViewDishesImage.downloadAndSetImage(COMMON.image_dishes)
        }
        binding.addDishesFragmentTextViewDescription.text = getString(R.string.add_dishes_menu_item)

        binding.addDishesFragmentImageViewDishesImage.setOnClickListener { setDishesPhoto() }
    }

    private fun setDishesPhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"
        startActivityForResult(intent, 1)
    }

    //Get picture
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            val uidDishes = REF_DATABASE_ROOT.child(NODE_DISHES).child(UID).push().key.toString()
            val path = REF_STORAGE_ROOT.child(FOLDER_PHOTO_DISHES).child(UID).child(uidDishes)
            putFileToStorage(uri!!, path) {
                getUrlFromStorage(path) {
                    putUrlToDatabase(it) {
                        COMMON.image_dishes = it
                        val dateMap = mutableMapOf<String, Any>()
                        dateMap[CHILD_NAME_DISHES] = binding.textInputEdtName.text.toString()
                        dateMap[CHILD_COAST_DISHES] = binding.textInputEdtCoast.text.toString()
                        dateMap[CHILD_IMAGE_DISHES] = COMMON.image_dishes
                        REF_DATABASE_ROOT.child(NODE_DISHES).child(UID).child(uidDishes).updateChildren(dateMap)
                    }
                }
            }
        }
    }
}