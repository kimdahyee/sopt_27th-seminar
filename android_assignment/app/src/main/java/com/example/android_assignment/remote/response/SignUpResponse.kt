package com.example.android_assignment.remote.response

/**
 * Created By kimdahyee
 * on 11월 30일, 2020
 */
 
data class SignUpResponse(
    val data: Data,
    val status: Int,
    val success: Boolean,
    val message: String
) {
    data class Data(
        val email: String,
        val password: String,
        val userName: String
    )
}