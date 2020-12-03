package com.example.android_assignment.remote

import com.example.android_assignment.remote.request.SignInRequest
import com.example.android_assignment.remote.request.SignUpRequest
import com.example.android_assignment.remote.response.SignInResponse
import com.example.android_assignment.remote.response.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created By kimdahyee
 * on 11월 30일, 2020
 */
 
interface SoptService {

    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    fun signup(
        @Body body: SignUpRequest
    ): Call<SignUpResponse>

    @Headers("Content-Type:application/json")
    @POST("/users/signin")
    fun signin(
        @Body body: SignInRequest
    ): Call<SignInResponse>
}