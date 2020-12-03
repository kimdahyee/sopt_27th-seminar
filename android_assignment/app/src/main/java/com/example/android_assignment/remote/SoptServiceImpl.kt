package com.example.android_assignment.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By kimdahyee
 * on 11월 30일, 2020
 */
 
object SoptServiceImpl {
    // 싱글톤으로 만드는 실제 구현체
    // -> 객체는 하나만 생성하고 프로젝트 어디서나 사용할 수 있게 하는 디자인 패턴
    // object: 싱글톤 객체로 사용하기 위해 object로 선언

    private const val BASE_URL = "http://15.164.83.210:3000"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
            //gson 연동: retrofit에서 받아오는 데이터를 다루기 쉽게 변환
        .build()
    // Retrofit 객체 생성

    val service: SoptService = retrofit.create(SoptService::class.java)
    // Interface 객체를 넘겨 실제 구현체 생성
}