package com.example.dailyne.classes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.dailyne.DashboardActivity
import com.example.dailyne.R
import com.example.dailyne.classes.AuthorizationActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.dailyne.clients.RetrofitClient
import com.example.dailyne.responses.LoginResponse
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment() {

    lateinit var Email : TextInputEditText
    lateinit var Password : TextInputEditText
    lateinit var LoginButton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_login, container, false)
        Email = view.findViewById<TextInputEditText>(R.id.emailEditText)!!
        Password = view.findViewById<TextInputEditText>(R.id.passwordEditText)!!
        LoginButton = view.findViewById<Button>(R.id.loginBtn)!!



        LoginButton.setOnClickListener {
            val name = Email.text.toString()
            val password = Password.text.toString()

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(context , "Please Fill in all Fields" , Toast.LENGTH_SHORT).show()
            } else {
                checkUser(name , password)
            }
        }

        return view

    }

    private fun checkUser(name : String , password : String) {

        RetrofitClient.instance.checkUser(name , password)
            .enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse?>,
                    response: Response<LoginResponse?>
                ) {
                    if (response.isSuccessful && response.body() !=null) {
                        val animation = AnimationUtils.loadAnimation(context, R.anim.b_to_m)
                        val intent = Intent(requireContext() , DashboardActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(context , "Invalid Credentials" , Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<LoginResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }

            }
            )
    }

}