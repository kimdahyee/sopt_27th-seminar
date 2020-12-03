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
import com.example.android_assignment.remote.request.SignUpRequest
import com.example.android_assignment.remote.response.SignUpResponse
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private val soptServiceImpl = SoptServiceImpl

    /*var pref : SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
    //"pref"이라는 파일 이름으로 SharedPreferences 객체 생성
    //해당 앱만 읽기, 쓰기가 가능하도록 권한 설정
    var editor : SharedPreferences.Editor = pref.edit()
    //데이터를 저장하기 위한 Editor 객체 생성*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_signup.setOnClickListener {
            when {
                et_name.text.isNullOrBlank() -> Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
                et_id.text.isNullOrBlank() -> Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
                et_password.text.isNullOrBlank() -> Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                else -> {
                    requestSignUpToServer()
                    /*  1차 세미나 성장과제2 - autoLogin
                    //Toast.makeText(this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)

                    /* 1차 세미나 성장과제 1
                    intent.putExtra("id", et_id.text.toString())
                    intent.putExtra("password", et_password.text.toString())
                    setResult(Activity.RESULT_OK, intent) */

                    editor.putString("id", et_id.text.toString())
                    editor.putString("password", et_password.text.toString())
                    //editor를 사용해서 파일에 key-value 형태로 id, password 정보 저장
                    //저장할 수 있는 데이터 타입 boolean, int, float, long, string
                    editor.commit()
                    //데이터 저장 및 삭제 시 commit 필수

                    startActivity(intent)
                    finish() */
                }
            }
        }
    }

    private fun requestSignUpToServer() {
        soptServiceImpl.service
            .signup(
                SignUpRequest(
                    email = et_id.toString(),
                    password = et_password.toString(),
                    userName = et_name.toString()
                )
            ).enqueue(object : Callback<SignUpResponse> {
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) { // 통신 실패
                    Log.d("fail", t.message.toString())
                }

                override fun onResponse(
                    call: Call<SignUpResponse>, response: Response<SignUpResponse>
                ) { // 통신 성공
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            Toast.makeText(this@SignUpActivity, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                            val intent = Intent()
                            intent.putExtra("id", et_id.text.toString())
                            intent.putExtra("password", et_password.text.toString())
                            setResult(Activity.RESULT_OK, intent)
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
}