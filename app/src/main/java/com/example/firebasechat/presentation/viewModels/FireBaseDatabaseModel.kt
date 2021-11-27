package com.example.firebasechat.presentation.viewModels

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.firebasechat.data.models.LoginRep
import com.example.firebasechat.data.models.User
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class FireBaseDatabaseModel: ViewModel()  {

    private val loginRep: LoginRep = LoginRep()

    private val filename = UUID.randomUUID().toString()
    val refStorage = FirebaseStorage.getInstance().getReference("/images/$filename")


    val uid = loginRep.firebaseAuth.uid
    val refDatabase = FirebaseDatabase.getInstance("https://chat-f4ee0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("/users/$uid")


}