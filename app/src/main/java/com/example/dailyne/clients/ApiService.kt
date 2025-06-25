package com.example.dailyne.clients

import com.example.dailyne.responses.LoginResponse
import com.example.dailyne.responses.SignupResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("signup.php")  // Replace with actual PHP filename
    fun registerUser(
        @Field("name") name: String,
        @Field("age") age: String,
        @Field("mobile_number") mobile: String,
        @Field("password") password: String
    ): Call<SignupResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun checkUser(
        @Field("name") name : String,
        @Field("password") password : String
    ): Call<LoginResponse>
}