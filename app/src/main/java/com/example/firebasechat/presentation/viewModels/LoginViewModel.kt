package com.example.firebasechat.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasechat.data.models.LoginRep
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(): ViewModel() {


    private val loginRep: LoginRep = LoginRep()

    val userMutableLiveData: MutableLiveData<FirebaseUser> = loginRep.userMutableLiveData
    val regOrNotMutableLiveData: MutableLiveData<Boolean> = loginRep.regOrNotMutableLiveData
    val firebaseAuth = loginRep.firebaseAuth



    fun register(email: String, password: String){
        loginRep.register(email, password)

    }

    fun login(email: String, password: String){
        loginRep.login(email, password)
    }

}