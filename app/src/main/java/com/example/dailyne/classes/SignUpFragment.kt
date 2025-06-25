package com.example.dailyne.classes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.dailyne.R
import com.example.dailyne.clients.RetrofitClient
import com.example.dailyne.responses.SignupResponse
import com.example.dailyne.classes.AuthorizationActivity.ViewPagerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {

    lateinit var AuthActivity : FragmentActivity

    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var mobileEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        nameEditText = view.findViewById(R.id.nameEditText)
        ageEditText = view.findViewById(R.id.ageEditText)
        mobileEditText = view.findViewById(R.id.mobileNumberEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText)
        signupButton = view.findViewById(R.id.signUpBtn)

        AuthActivity = requireActivity()

        signupButton.setOnClickListener {
            registerUser()
        }

        return view
    }

    private fun registerUser() {
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString()
        val mobile = mobileEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        if (name.isEmpty() || age.isEmpty() || mobile.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(context, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }

        RetrofitClient.instance.registerUser(name, age, mobile, password)
            .enqueue(object : Callback<SignupResponse> {
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(context, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        AuthActivity.findViewById<ViewPager2>(R.id.viewPager).currentItem = 0
                    } else {
                        Toast.makeText(context, "Sign Up Failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
