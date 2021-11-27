package com.example.firebasechat.presentation.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.firebasechat.R
import com.example.firebasechat.data.models.User
import com.example.firebasechat.presentation.viewModels.FireBaseDatabaseModel
import com.example.firebasechat.presentation.viewModels.LoginViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*


class EnterNameFragment : Fragment() {



    private lateinit var editName: EditText
    private lateinit var submitButton: Button
    private lateinit var selectPhotoButton: Button

    private val fireBaseDatabaseModel: FireBaseDatabaseModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val view = inflater.inflate(R.layout.fragment_enter_name, container, false)

        editName = view.findViewById(R.id.enterNameId)
        submitButton = view.findViewById(R.id.submitButton)
        selectPhotoButton = view.findViewById(R.id.photoSelect)

        selectPhotoButton.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)

            selectPhotoButton.text = null

        }


        submitButton.setOnClickListener {

            fireBaseDatabaseModel.refStorage.putFile(selectedPhotoUri!!)

            val user = User(fireBaseDatabaseModel.uid ?: "", editName.text.toString(), fireBaseDatabaseModel.refStorage.downloadUrl.toString())

            fireBaseDatabaseModel.refDatabase.setValue(user)
        }

        return view
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){

            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, selectedPhotoUri)
            

            val bitmapDrawable = BitmapDrawable(bitmap)

            selectPhotoButton.background = bitmapDrawable
        }
    }




}