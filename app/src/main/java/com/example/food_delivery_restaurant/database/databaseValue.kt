package com.example.food_delivery_restaurant.database

import com.example.food_delivery_restaurant.model.CommonModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference

lateinit var AUTH: FirebaseAuth
lateinit var COMMON: CommonModel
lateinit var UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference

//Nodes
const val NODE_RESTAURANT = "restaurant"
const val NODE_ORDERS = "orders"
const val NODE_DISHES = "dishes"

//Folder
const val FOLDER_PHOTO_DISHES = "photo_dishes"

//Const
const val RESTAURANT_ROLE = "restaurant"

//Child
const val CHILD_ROLE = "role"
const val CHILD_NAME_DISHES = "name_dishes"
const val CHILD_IMAGE_DISHES = "image_dishes"
const val CHILD_COAST_DISHES = "coast_dishes"