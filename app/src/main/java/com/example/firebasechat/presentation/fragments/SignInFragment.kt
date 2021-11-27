package com.example.firebasechat.presentation.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.firebasechat.R
import com.example.firebasechat.presentation.viewModels.LoginViewModel
import com.google.firebase.auth.FirebaseUser


class SignInFragment : Fragment() {

    private lateinit var loginView: EditText
    private lateinit var passwordView: EditText
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button

    private val loginViewModel: LoginViewModel by viewModels()







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {




            val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        loginViewModel.userMutableLiveData.observe(viewLifecycleOwner,
            Observer<FirebaseUser?> { firebaseUser ->
                if (firebaseUser != null) {
                    Toast.makeText(context, "already", Toast.LENGTH_SHORT).show()
                }
            })

        loginViewModel.regOrNotMutableLiveData.observe(viewLifecycleOwner,
            Observer { regUser ->
                if(regUser == true){
                    findNavController().navigate(R.id.action_signInFragment_to_enterNameFragment)
                }
             })


        loginView = view.findViewById(R.id.email_view)
        passwordView = view.findViewById(R.id.password_view)
        loginButton = view.findViewById(R.id.login_button)
        registerButton = view.findViewById(R.id.register_button)

        loginButton.setOnClickListener {
            val email = loginView.text.toString().trim()
            val password = passwordView.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                loginViewModel.login(email, password)

            }


        }

        registerButton.setOnClickListener {
            val email = loginView.text.toString().trim()
            val password = passwordView.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                loginViewModel.register(email, password)

            }


        }

        return view
    }



}