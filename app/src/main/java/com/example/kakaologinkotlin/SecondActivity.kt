package com.example.kakaologinkotlin

import android.content.ContentValues
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kakao.sdk.user.UserApiClient

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(ContentValues.TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.i(
                    ContentValues.TAG, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                val Nickname = findViewById<View>(R.id.tvNickname) as TextView
                Nickname.text = "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
            }
        }

        val kakao_logout_button = findViewById<Button>(R.id.kakao_logout_button) as Button

        kakao_logout_button.setOnClickListener {
                UserApiClient.instance.logout { error ->
                if (error != null) {
                    Toast.makeText(this, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
            }
        }

        val kakao_unlink_button = findViewById<Button>(R.id.kakao_unlink_button) as Button

        kakao_unlink_button.setOnClickListener {
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Toast.makeText(this, "회원 탈퇴 실패 $error", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                }
            }
        }

        val tabLayout=findViewById<TabLayout>(R.id.tab)
        val viewpager2=findViewById<ViewPager2>(R.id.viewPager2)

        val adapter = FragmentAdapter(supportFragmentManager, lifecycle)

        viewpager2.adapter=adapter

        TabLayoutMediator(tabLayout, viewpager2){tab, position->
            when(position){
                0->{
                    tab.text = "Contact"
                    tab.setIcon(R.drawable.ic_baseline_person_24)
                }
                1->{
                    tab.text = "Gallery"
                    tab.setIcon(R.drawable.ic_baseline_photo_24)
                }
                else->{
                    tab.text = "Map"
                    tab.setIcon(R.drawable.ic_baseline_map_24)
                }
            }
        }.attach()
    }
}