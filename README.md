

# 👨‍🚀 sopt_27th-seminar Android_assignment 👩‍🚀


### 1주차 View와 ViewGroup (20.10.10)
:heavy_check_mark: 필수 과제 / 성장 과제 1 (20.10.17 완료) 

> SignUpActivity 만들기 / 화면 이동 + @

1. 로그인 화면에서 "회원가입하러가기" 클릭 시 SignUpActivity로 이동 :arrow_right:

	    tv_signup.setOnClickListener { //회원가입하러가기
			val intent = Intent(this, SignUpActivity::class.java)
			startActivityForResult(intent, REQ_CODE)
		}

2. 이름 / 아이디 / 비밀번호 입력 후 회원가입 진행
 (하나라도 입력이 안되어 있는 경우 ToastMessage 출력 :speech_balloon:)

	    btn_signup.setOnClickListener {
			when {
					et_name.text.isNullOrBlank() -> Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
					et_id.text.isNullOrBlank() -> Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
					et_password.text.isNullOrBlank() -> Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
					else -> {
						Toast.makeText(this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
						val intent = Intent(this, LoginActivity::class.java)
						intent.putExtra("id", et_id.text.toString())
						intent.putExtra("password", et_password.text.toString())
						setResult(Activity.RESULT_OK, intent) 
						finish()
				}
			}
		}

3. 회원가입 성공 시 이전 로그인 화면으로 이동 :arrow_right:
 (회원가입 시 입력한 아이디 / 비밀번호 입력된 상태)

	    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
			super.onActivityResult(requestCode, resultCode, data)
			if (requestCode == REQ_CODE && resultCode == Activity.RESULT_OK) {
				et_id_login.setText(data!!.getStringExtra("id"))
				et_password_login.setText(data!!.getStringExtra("password")) 
				finish()
			}
		}


:heavy_check_mark: 성장 과제 1 (20.10.19 완료) 

> 자동 로그인

:bell: 간단한 설정 값이나 문자열 같은 데이터는 *SharedPreferences*를 사용해서 관리 및 사용 가능 :bell:

1. SharedPreferences와 SharedPreferences에 데이터를 저장하기 위한 Editor 객체 생성 :file_folder: :pencil2:

	    var pref : SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
		var editor : SharedPreferences.Editor = pref.edit()
2. Editor를 사용해서 파일에 아이디 / 비밀번호 정보 저장 :key:

	    editor.putString("id", et_id.text.toString())
		editor.putString("password", et_password.text.toString())
		editor.commit()
		//데이터 저장 및 삭제 시 commit 필수
3. SharedPreferences에 저장된 값 가져와서 자동 로그인 진행 :unlock:

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
