# 👨‍🚀 sopt_27th-seminar Android_assignment 👩‍🚀

### 1주차 View와 ViewGroup (20.10.10)
<br>
✔️ 필수 과제 / 성장 과제 1 (20.10.17 완료)

> SignUpActivity 만들기 / 화면 이동 + @

1.  로그인 화면에서 "회원가입하러가기" 클릭 시 SignUpActivity로 이동

	```kotlin
	tv_signup.setOnClickListener { //회원가입하러가기  
	     val intent = Intent(this, SignUpActivity::class.java)  
	     startActivityForResult(intent, REQ_CODE)  
	}
	```
    <br>
2.  이름 / 아이디 / 비밀번호 입력 후 회원가입 진행  ( 하나라도 입력이 안되어 있는 경우 ToastMessage 출력 💬 ​)
    
    ```kotlin
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
	```
    <br>
3.  회원가입 성공 시 이전 로그인 화면으로 이동 ​  ( 회원가입 시 입력한 아이디 / 비밀번호 입력된 상태 )
    
    ```kotlin
	 override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {  
	     super.onActivityResult(requestCode, resultCode, data) 
	      
	     if (requestCode == REQ_CODE && resultCode == Activity.RESULT_OK) {  
		     et_id_login.setText(data!!.getStringExtra("id"))  
		     et_password_login.setText(data!!.getStringExtra("password"))   
		     finish()  
	     }  
    }
	```
    <br>
----------

✔️ 성장 과제 2 (20.10.19 완료)

> 자동 로그인

📕 간단한 설정 값이나 문자열 같은 데이터는 _SharedPreferences_를 사용해서 관리 및 사용 가능

1.  SharedPreferences와 SharedPreferences에 데이터를 저장하기 위한 Editor 객체 생성

	```kotlin
	var pref : SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)  
    var editor : SharedPreferences.Editor = pref.edit()
	```
    <br>
2.  Editor를 사용해서 파일에 아이디 / 비밀번호 정보 저장
    
    ```kotlin
	editor.putString("id", et_id.text.toString())  
    editor.putString("password", et_password.text.toString())  
    editor.commit()  
    //데이터 저장 및 삭제 시 commit 필수
	```
      <br>
3.  SharedPreferences에 저장된 값 가져와서 자동 로그인 진행
    
    ```kotlin
	var pref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)  
    ​  
	if (!(pref.getString("id", null).isNullOrBlank() || pref.getString("password", null).isNullOrBlank())) {  
		     val id = pref.getString("id", null).toString()  
		     //값을 가져올 때 파라미터로 key와 default값을 넣어준다  
		     
		     if (!id.isNullOrBlank()) {  
			     Toast.makeText(this, "${id}님이 자동로그인 되었습니다.", Toast.LENGTH_SHORT).show();  
			     val intent = Intent(this, MainActivity::class.java)  
			     startActivityForResult(intent, REQ_CODE)  
			 }    
    }
	```    
<br><br>
---
### 2주차 RecyclerView (20.10.17)
<br>
✔️ 필수 과제 (20.11.08 완료)

> 포트폴리오 Recyclerview 만들기

1.  반복될 item view 생성 - item_portpolio.xml
    
2.  배치방향 설정 - activity_portfolio.xml / PortfolioActivity.kt
    
3.  data 형태 결정 - PortfolioData.kt
    
4.  viewHolder 생성 - PortfolioViewHolder.kt
    
    📕 viewHolder는 반복될 item view 하나에 대한 데이터를 넣을 **위치 정보**
    
5.  adapter 생성 - PortfolioAdapter.kt
    
6.  RecyclerView에 adapter 적용 - PortfolioActivity.kt
    
    ```kotlin
	portfolioAdapter = PortfolioAdapter(this)  
    rcv_portfolio.adapter = portfolioAdapte
	```
   
7.  item 클릭 시 상세 화면으로 이동하기 위한 RecyclerView click event 처리
    
    ( click event를 adapter에서 처리하지 않고, activity (또는 fragment)에서 처리하고자 할 때의 방법 )
    
    i ) adapter 내에 custom click listener interface 정의
    
    ```kotlin
	interface ItemClickListener {  
	   fun onItemClick(view: View, position: Int)  
    }
	```
  
    ii ) listener 객체를 전달하는 method와 전달된 객체를 저장할 변수 추가
    
    ```kotlin
	//listener 객체 참조를 저장하는 변수  
    private lateinit var itemClickListener: ItemClickListener  
    ​  
    //ItemClickListener 객체 참조를 adapter에 전달하는 method  
    fun setItemClickListener(itemClickListener: ItemClickListener) {  
	    this.itemClickListener = itemClickListener  
    }
	```
    
    iii ) item view와 click listener를 연결
    
    ```kotlin
	override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {  
	    holder.onBind(data[position])  
	    holder.itemView.setOnClickListener{  
		    itemClickListener.onItemClick(it, position)  
	    }  
    }
	```
   
    iv ) activity (또는 fragment)에서 custom listener 객체 생성 및 전달
    
    ```kotlin
	portfolioAdapter.setItemClickListener(object : PortfolioAdapter.ItemClickListener {  
	    override fun onItemClick(view: View, position: Int) {  
		    val clickedPortfolioIntent = Intent(view.context, PortfolioDetailActivity::class.java)  
		    startActivity(clickedPortfolioIntent)  
    })
	```
   <br>
🎃  _data가 있으면 adapter가 data list 중 하나를 viewHolder에게 전달하고 viewHolder는 전달 받은 data를 view에 뿌려준다_


----------

✔️ 성장 과제 1 (20.11.16 완료)

> GridLayout 만들기

📋 menu에서 LinearLayout 클릭 시 배치 방향 -> Linear / GridLayout 클릭 시 배치 방향 -> Grid
<img width="230" src="https://user-images.githubusercontent.com/63586451/99229911-fee71e00-2831-11eb-8f29-3752844270bd.PNG">   <img width="230" src="https://user-images.githubusercontent.com/63586451/99229001-c1ce5c00-2830-11eb-88d1-07917ff3c9d7.png">   <img width="230" src="https://user-images.githubusercontent.com/63586451/99229003-c2ff8900-2830-11eb-8754-2e3da07534bd.png">
<br>
1.  배치 방향 선택을 위한 menu 생성
    
    i ) res 폴더 안에 _menu_ directory 생성 후 menu directory 안에 _xml 파일_ 생성해서 _menu item_ 설정
    
    ii ) option menu 지정을 위해 onCreateOptionsMenu() 재정의
    
    ```kotlin
	override fun onCreateOptionsMenu(menu: Menu?): Boolean {  
	    val inflater: MenuInflater = menuInflater  
	    inflater.inflate(R.menu.layout, menu)  
	    return true  
    }
	```
    <br>   

2.  Grid 방향을 위한 새로운 layout 작성
   <br>
3.  adapter에서 viewType에 따라 inflate되는 view를 지정
    
    ```kotlin
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {  
	     var view = when (viewType) {  
		     1 -> {  
			     LayoutInflater.from(context).inflate(R.layout.item_portfolio, parent, false)  
		     }  
		     else -> {  
			     LayoutInflater.from(context).inflate(R.layout.item_portfolio_grid, parent, false)  
		     }  
		 }  
		 return PortfolioViewHolder(view)  
	}  
    ​  
    override fun getItemViewType(position: Int): Int {  
	     return viewType  
    }
	``` 
	<br>   

4.  activity에서 선택되는 menu item에 따라 viewType과 LayoutManager를 지정
    
    ```kotlin
	override fun onOptionsItemSelected(item: MenuItem): Boolean {  
	     when (item?.itemId) {  
		     R.id.menu_linear -> {  
			     portfolioAdapter.viewType = 1  
			     rcv_portfolio.layoutManager = LinearLayoutManager(this)  
		     }  
		     R.id.menu_grid -> {  
			     portfolioAdapter.viewType = 2  
			     rcv_portfolio.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)  
		     }  
		 }  
	     return super.onOptionsItemSelected(item)  
    }
	```
	
