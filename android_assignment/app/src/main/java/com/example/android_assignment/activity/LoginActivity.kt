package com.example.android_assignment.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android_assignment.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val REQ_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        autoLogin()

        btn_login.setOnClickListener {
            when {
                et_id_login.text.isNullOrBlank() -> Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
                et_password_login.text.isNullOrBlank() -> Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                else -> {
                    Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
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
            /* 성장과제 1
            et_id_login.setText(data!!.getStringExtra("id"))
            et_password_login.setText(data!!.getStringExtra("password")) */
            finish()
        }
    }

    fun autoLogin() {
        var pref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)

        if (!(pref.getString("id", null).isNullOrBlank() || pref.getString("password", null).isNullOrBlank())) {
            val id = pref.getString("id", null).toString()
            //값을 가져올 때 파라미터로 key와 default값을 넣어준다

            if (!id.isNullOrBlank()) {
                Toast.makeText(this, "${id}님이 자동로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                val intent = Intent(this, MainActivity::class.java)
                startActivityForResult(intent, REQ_CODE)
            }
        }
    }
}