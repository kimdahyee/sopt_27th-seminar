package com.example.android_assignment.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.android_assignment.R
import com.example.android_assignment.remote.SoptServiceImpl
import com.example.android_assignment.remote.request.SignInRequest
import com.example.android_assignment.remote.response.SignInResponse
import com.example.android_assignment.remote.response.SignUpResponse
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val REQ_CODE = 100
    private val soptServiceImpl = SoptServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //autoLogin()

        btn_login.setOnClickListener {
            when {
                et_id_login.text.isNullOrBlank() -> Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
                et_password_login.text.isNullOrBlank() -> Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                else -> {
                    requestSignInToServer()
                    /* 1차 세미나
                    Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()*/
                }
            }
        }

        tv_signup.setOnClickListener { //회원가입하러가기
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQ_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE && resultCode == Activity.RESULT_OK) {
            et_id_login.setText(data!!.getStringExtra("id"))
            et_password_login.setText(data.getStringExtra("password"))
            finish()
        }
    }

    private fun requestSignInToServer() {
        soptServiceImpl.service
            .signin(
                SignInRequest(
                    email = et_id_login.text.toString(),
                    password = et_password_login.text.toString()
                )
            ).enqueue(object : Callback<SignInResponse> {

                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    // 통신 실패
                    Log.d("fail", t.message.toString())
                }

                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            Toast.makeText(this@LoginActivity, "로그인 성공 !! 이제 들어가보자 :)", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } ?: showError(response.errorBody())
                }
            })
    }

    private fun showError(error: ResponseBody?) {
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"), Toast.LENGTH_SHORT).show()
    }

    private fun autoLogin() {
        var pref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)

        if (!(pref.getString("id", null).isNullOrBlank() || pref.getString("password", null)
                .isNullOrBlank())
        ) {
            val id = pref.getString("id", null).toString()
            //값을 가져올 때 파라미터로 key와 default값을 넣어준다

            if (!id.isNullOrBlank()) {
                Toast.makeText(this, "${id}님이 자동로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivityForResult(intent, REQ_CODE)
            }
        }
    }
}