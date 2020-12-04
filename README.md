# 👨‍🚀 sopt_27th-seminar Android_assignment 👩‍🚀

<br>

### 1주차 View와 ViewGroup (20.10.10)

<br>

✔️ 필수 과제 / 성장 과제 1 (20.10.17 완료)

> SignUpActivity 만들기 / 화면 이동 + @

1. 로그인 화면에서 "회원가입하러가기" 클릭 시 SignUpActivity로 이동

   ```kotlin
   tv_signup.setOnClickListener { //회원가입하러가기  
        val intent = Intent(this, SignUpActivity::class.java)  
        startActivityForResult(intent, REQ_CODE)  
   }
   ```

   <br>

2. 이름 / 아이디 / 비밀번호 입력 후 회원가입 진행  ( 하나라도 입력이 안되어 있는 경우 ToastMessage 출력 💬 )

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

3. 회원가입 성공 시 이전 로그인 화면으로 이동   ( 회원가입 시 입력한 아이디 / 비밀번호 입력된 상태 )

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

✔️ 성장 과제 2 (20.10.19 완료)

> 자동 로그인

📕 간단한 설정 값이나 문자열 같은 데이터는 _SharedPreferences_를 사용해서 관리 및 사용 가능

1. SharedPreferences와 SharedPreferences에 데이터를 저장하기 위한 Editor 객체 생성

   ```kotlin
   var pref : SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)  
   var editor : SharedPreferences.Editor = pref.edit()
   ```

   <br>

2. Editor를 사용해서 파일에 아이디 / 비밀번호 정보 저장

   ```kotlin
   editor.putString("id", et_id.text.toString())  
   editor.putString("password", et_password.text.toString())  
   editor.commit()  
   //데이터 저장 및 삭제 시 commit 필수
   ```

     <br>

3. SharedPreferences에 저장된 값 가져와서 자동 로그인 진행

   ```kotlin
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
   ```

   <br><br>

### 2주차 RecyclerView (20.10.17)

<br>

✔️ 필수 과제 (20.11.08 완료)

> 포트폴리오 Recyclerview 만들기

1. 반복될 item view 생성 - item_portpolio.xml

2. 배치방향 설정 - activity_portfolio.xml / PortfolioActivity.kt

3. data 형태 결정 - PortfolioData.kt

4. viewHolder 생성 - PortfolioViewHolder.kt

   📕 viewHolder는 반복될 item view 하나에 대한 데이터를 넣을 **위치 정보**

5. adapter 생성 - PortfolioAdapter.kt

6. recyclerView에 adapter 적용 - PortfolioActivity.kt

   ```kotlin
   portfolioAdapter = PortfolioAdapter(this)  
   rcv_portfolio.adapter = portfolioAdapte
   ```

7. item 클릭 시 상세 화면으로 이동하기 위한 RecyclerView click event 처리

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
   🎃  data가 있으면 adapter가 data list 중 하나를 viewHolder에게 전달하고 viewHolder는 전달 받은 data를 view에 뿌려준다

<br><br>

✔️ 성장 과제 1 (20.11.16 완료)

> GridLayout 만들기

📋 menu에서 LinearLayout 클릭 시 배치 방향 -> Linear / GridLayout 클릭 시 배치 방향 -> Grid


<img width="230" src="https://user-images.githubusercontent.com/63586451/99229911-fee71e00-2831-11eb-8f29-3752844270bd.PNG">   <img width="230" src="https://user-images.githubusercontent.com/63586451/99229001-c1ce5c00-2830-11eb-88d1-07917ff3c9d7.png">   <img width="230" src="https://user-images.githubusercontent.com/63586451/99229003-c2ff8900-2830-11eb-8754-2e3da07534bd.png">
<br>

1. 배치 방향 선택을 위한 menu 생성

   i ) res 폴더 안에 _menu_ directory 생성 후 menu directory 안에 _xml 파일_ 생성해서 _menu item_ 설정

   ii ) option menu 지정을 위해 onCreateOptionsMenu() 재정의

   ```kotlin
   override fun onCreateOptionsMenu(menu: Menu?): Boolean {  
       val inflater: MenuInflater = menuInflater  
       inflater.inflate(R.menu.layout, menu)  
       return true  
   }   
   ```

2. Grid 방향을 위한 새로운 layout 작성

3. adapter에서 viewType에 따라 inflate되는 view를 지정

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
         
   override fun getItemViewType(position: Int): Int {  
        return viewType  
   }
   ```

4. activity에서 선택되는 menu item에 따라 viewType과 LayoutManager를 지정

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

   <br><br>

### 3주차 Fragment, ViewPager, BottomNavigation, TabLayout (20.10.31)

<br>

✔️ 필수 과제  (20.11.30 완료)

> BottomNavigation + ViewPager + TabLayout 사용해서 프로필 view 구현하기

<img width="230" src="https://user-images.githubusercontent.com/63586451/101140204-fc6c2d00-3655-11eb-8e5c-988b6feecb5c.png">  <img width="230" src="https://user-images.githubusercontent.com/63586451/101140245-0d1ca300-3656-11eb-849f-d6a9b8ae018c.png">  <img width="230" src="https://user-images.githubusercontent.com/63586451/101140257-0ee66680-3656-11eb-8575-09861a34f2bf.png">  <img width="230" src="https://user-images.githubusercontent.com/63586451/101140279-173ea180-3656-11eb-9289-9cdbc0a6fd26.png">

<br><br>



**BottomNavigation + ViewPager**

1. res 폴더 안에 _menu_ directory 생성 후 menu directory 안에 _xml 파일_ 생성해서 _menu item_ 설정 - navigation.xml

2. res 폴더 안에 _color_ directory 생성 후 color directory 안에 _xml 파일_ 생성해서 _icon selector_ 설정 - selector_nav.xml

3. 배치 설정 - activity_main.xml

4. viewPager adapter 생성 - MainPagerAdapter.kt

   ```kotlin
   class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
   
       override fun getItem(position: Int): Fragment {
           return when(position) {
               0 -> ProfileFragment()  //index가 0일때
               1 -> LinearFragment()
               else -> GridFragment()
           }
       }
   
       override fun getCount() = 3
   }
   ```

5. viewPager에  adapter 적용 - MainActivity.kt

   ```kotlin
   viewPager_main.adapter = MainPagerAdapter(supportFragmentManager)
   ```

6. viewPager의 화면전환을 감지하는 listener 설정 - MainActivity.kt

   ```kotlin
   viewPager_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
               // viewPager의 이동 -> 하단 탭의 체크 상태 변화
               override fun onPageScrollStateChanged(state: Int) {
               }
   
               override fun onPageScrolled(
                   position: Int,
                   positionOffset: Float,
                   positionOffsetPixels: Int
               ) {
               }
   
               override fun onPageSelected(position: Int) {
                   // navigation 메뉴 아이템 체크
                   main_bottomNavigation.menu.getItem(position).isChecked = true
               }
   })
   ```

7. bottomNavigation item 클릭 시 클릭 이벤트를 처리할 listener 설정 - MainActivity.kt

   ```kotlin
   main_bottomNavigation.setOnNavigationItemSelectedListener {
               // 하단 탭의 체크 이벤트 -> 해당하는 페이지로 이동
               var index by Delegates.notNull<Int>()
               when (it.itemId) {
                   R.id.nav_profile -> index = 0
                   R.id.nav_linear -> index = 1
                   R.id.nav_grid -> index = 2
               }
               viewPager_main.currentItem = index
               true
   }
   ```



**TabLayout**

1. 배치 설정 - fragment_profile.xml

2. viewPager adapter 생성 - ProfilePagerAdapter.kt

3. viewPager에 adapter 적용 - ProfileFragment.kt

4. tabLayout에 ViewPager 연동 - ProfileFragment.kt

   ```kotlin
   tabLayout_profile.setupWithViewPager(viewPager_profile)
   ```

5. tab title 설정 - ProfileFragment.kt

   📕 반드시 viewPager와 연동 후 작성해야한다

   ```kotlin
   tabLayout_profile.apply {
               getTabAt(0)?.text = "INFO"
               getTabAt(1)?.text = "OTHER"
   }
   ```

<br><br>

### 6주차 Server (20.11.21)

<br>

✔️ 필수 과제  (20.12.03 완료)

> 로그인/회원가입 서버 통신 구현하기

<img width="550" src="https://user-images.githubusercontent.com/63586451/101156117-765ae100-366b-11eb-9c15-1caa4271a492.PNG">  <img width="550" src="https://user-images.githubusercontent.com/63586451/101156119-7824a480-366b-11eb-821e-065334a67749.PNG"> 

<br>

<img width="230" src="https://user-images.githubusercontent.com/63586451/101156512-10228e00-366c-11eb-8fb4-0cc38785c35b.png"> <img width="230" src="https://user-images.githubusercontent.com/63586451/101156515-10bb2480-366c-11eb-916e-570c700accdf.png">

<br>



1. Request/Response 객체 생성 

   - SignUpRequest.kt 

     ```kotlin
     data class SignUpRequest (
         val email: String,
         val password: String,
         val userName: String
     )
     ```

   - SignUpResponse.kt

     ```kotlin
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
     ```

   - SignInRequest.kt

     ```kotlin
     data class SignInRequest(
         val email: String,
         val password: String
     )
     ```

   - SignInResponse.kt

     ```kotlin
     data class SignInResponse(
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
     ```

     <br>

2.  retrofit interface 생성 - SoptService.kt

   ```kotlin
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
   ```

3. 구현체 생성 - SoptServiceImpl.kr

   ```kotlin
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
   ```

   

