package com.example.firebasechat.data.models

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

data class LoginRep(
    val userMutableLiveData: MutableLiveData<FirebaseUser> = MutableLiveData(),
    val firebaseAuth: FirebaseAuth = Firebase.auth,
    val regOrNotMutableLiveData: MutableLiveData<Boolean> = MutableLiveData(), ) {



    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                    regOrNotMutableLiveData.postValue(task.result?.additionalUserInfo?.isNewUser)
                    //Toast.makeText(MainActivity(), "Registration success: ", Toast.LENGTH_SHORT).show();


                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    //Toast.makeText(MainActivity(), "Registration failed: ", Toast.LENGTH_SHORT).show();

                }
            }
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "LoginWithEmail:success")
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                    //Toast.makeText(MainActivity(), "Login success: ", Toast.LENGTH_SHORT).show();
                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "LoginWithEmail:failure", task.exception)
                   // Toast.makeText(MainActivity(), "Login failed: ", Toast.LENGTH_SHORT).show();

                }
            }
    }

}