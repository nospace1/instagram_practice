package com.example.instagram

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import bolts.Task
import com.example.instagram.navigation.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        setToolbarDefault()
        when(item.itemId){
            R.id.action_home ->{
                val detailViewFragment = DetailViewFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, detailViewFragment).commit()
                return true
            }
            R.id.action_search ->{
                val gridFragment = GridFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, gridFragment).commit()
                return true
            }
            R.id.action_add_photo ->{

                if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    startActivity(Intent(this, AddPhotoActivity::class.java))
                }
                return true
            }
            R.id.action_favorite_alarm ->{
                val alarmFragment = AlarmFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, alarmFragment).commit()
                return true
            }
            R.id.action_account ->{
                val userFragment = UserFragment()
                var bundle = Bundle()
                var uid = FirebaseAuth.getInstance().currentUser?.uid
                bundle.putString("destinationUid",uid)
                userFragment.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.main_content, userFragment).commit()
                return true
            }
        }
        return false
    }
    fun setToolbarDefault(){
        toolbar_username.visibility = View.GONE
        toolbar_btn_back.visibility = View.GONE
        toolbar_title_image.visibility = View.VISIBLE
    }
    fun registerPushToken(){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(this)


        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        // Set default screen
        bottom_navigation.selectedItemId = R.id.action_home

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == UserFragment.PICK_PROFILE_FROM_ALBUM && resultCode == Activity.RESULT_OK){
            var imageUri = data?.data
            var uid = FirebaseAuth.getInstance().currentUser?.uid
            var storageRef = FirebaseStorage.getInstance().reference.child("userProfileImages").child(uid!!)
            storageRef.putFile(imageUri!!).continueWithTask { task: com.google.android.gms.tasks.Task<UploadTask.TaskSnapshot> ->
                return@continueWithTask storageRef.downloadUrl
            }.addOnSuccessListener { uri ->
                var map = HashMap<String, Any>()
            }
        }
    }
}



