package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import com.example.instagram.navigation.AlarmFragment
import com.example.instagram.navigation.DetailViewFragment
import com.example.instagram.navigation.GridFragment
import com.example.instagram.navigation.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val bottom_navigation : LinearLayout = findViewById(R.id.bottom_navigation) -> error
        // val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation) -> 아래와 결과 같음
        val bottom_navigation : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener() { item ->
            when(item.itemId){
                R.id.action_home ->{
                    val detailViewFragment = DetailViewFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_content, detailViewFragment).commit()
                    true
                }
                R.id.action_search ->{
                    val gridFragment = GridFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_content, gridFragment).commit()
                    true
                }
                R.id.action_add_photo ->{

                    true
                }
                R.id.action_favorite_alarm ->{
                    val alarmFragment = AlarmFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_content, alarmFragment).commit()
                    true
                }
                R.id.action_account ->{
                    val userFragment = UserFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_content, userFragment).commit()
                    true
                }
                else -> false
            }
        }
    }
}

