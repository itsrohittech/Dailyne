package com.example.dailyne.classes

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.dailyne.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AuthorizationActivity : AppCompatActivity() {

    lateinit var MainText : TextView
    lateinit var SwitchTabs : TabLayout
    lateinit var Pager : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.authorization)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        MainText = findViewById<TextView>(R.id.mainTextview)
        SwitchTabs = findViewById<TabLayout>(R.id.tabLayout)
        Pager = findViewById<ViewPager2>(R.id.viewPager)
        Pager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(SwitchTabs, Pager) { tab, position ->
            tab.text = if (position == 0) "Login" else "Sign Up"
        }.attach()

        startActivities()

    }

    fun startActivities() {
        Handler(Looper.getMainLooper()).postDelayed({
            val anim = AnimationUtils.loadAnimation(this, R.anim.b_to_m)
            SwitchTabs.startAnimation(anim)
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            SwitchTabs.visibility = View.VISIBLE
            val anim = AnimationUtils.loadAnimation(this, R.anim.b_to_m)
            SwitchTabs.startAnimation(anim)
        }, 1500)

        Handler(Looper.getMainLooper()).postDelayed({
            Pager.visibility = View.VISIBLE
            val anim = AnimationUtils.loadAnimation(this, R.anim.b_to_m)
            Pager.startAnimation(anim)
        }, 1500)
    }

    class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int) : Fragment {
            return when (position) {
                0 -> LoginFragment()
                else -> SignUpFragment()
            }
        }

    }

}