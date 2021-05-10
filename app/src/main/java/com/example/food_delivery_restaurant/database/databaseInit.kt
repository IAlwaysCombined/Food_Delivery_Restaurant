package com.example.food_delivery_restaurant.database

import android.net.Uri
import com.example.food_delivery_restaurant.model.CommonModel
import com.example.food_delivery_restaurant.utilities.AppValueEventListener
import com.example.food_delivery_restaurant.utilities.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    UID = AUTH.currentUser?.uid.toString()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    COMMON = CommonModel()
}

//get common model
fun DataSnapshot.getCommonModel(): CommonModel =
    this.getValue(CommonModel::class.java) ?: CommonModel()

//Initial common
fun initCommon() {
    REF_DATABASE_ROOT.child(NODE_RESTAURANT).child(UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            COMMON = it.getValue(CommonModel::class.java) ?: CommonModel()
        })
}

//Send URL in realtime database
inline fun putUrlToDatabase(url: String, crossinline function: () -> Unit) {
    val uidDishes = REF_DATABASE_ROOT.child(NODE_DISHES).child(UID).push().key.toString()
    REF_DATABASE_ROOT.child(NODE_DISHES).child(UID).child(uidDishes).child(FOLDER_PHOTO_DISHES).setValue(url)
        .addOnSuccessListener { function() }
        .addOnFailureListener { showToast(it.message.toString()) }
    REF_DATABASE_ROOT.child(NODE_DISHES).child(UID).child(uidDishes).child(FOLDER_PHOTO_DISHES).removeValue()
}

//Get URL image in storage
inline fun getUrlFromStorage(path: StorageReference, crossinline function: (url: String) -> Unit) {
    path.downloadUrl
        .addOnSuccessListener { function(it.toString()) }
        .addOnFailureListener { showToast(it.message.toString()) }
}

//Send image in storage
inline fun putFileToStorage(uri: Uri, path: StorageReference, crossinline function: () -> Unit) {
    path.putFile(uri)
        .addOnSuccessListener { function() }
        .addOnFailureListener { showToast(it.message.toString()) }
}