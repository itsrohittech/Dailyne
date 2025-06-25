package com.example.dailyne

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
//import com.example.dailyne.fragments.HomeFragment
//import com.example.dailyne.fragments.ShortsFragment
//import com.example.dailyne.fragments.SubscriptionFragment
//import com.example.dailyne.fragments.LibraryFragment

class DashboardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

////        replaceFragment(HomeFragment())
//        binding.bottomNavigationView.setBackground(null)
//
//        binding.bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.home -> replaceFragment(HomeFragment())
//                R.id.shorts -> replaceFragment(ShortsFragment())
//                R.id.subscriptions -> replaceFragment(SubscriptionFragment())
//                R.id.library -> replaceFragment(LibraryFragment())
//            }
//            true
//        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
