package com.app.edgebottom

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnApplyWindowInsetsListener(null)
        bottomNav.setPadding(0, 0, 0, 0)

        val homeFragment: Fragment = HomeFragment()
        val profileFragment: Fragment = ProfileFragment()

        var activeFragment: Fragment = homeFragment

        supportFragmentManager.beginTransaction().apply {
            add(R.id.content, homeFragment)
            add(R.id.content, profileFragment)
            hide(profileFragment)
            commit()
        }


        bottomNav.setOnItemSelectedListener { item ->
            val newFragment =
                when (item.itemId) {
                R.id.home -> homeFragment
                R.id.profile -> profileFragment
                else -> return@setOnItemSelectedListener false
            }
            if (activeFragment != newFragment) {
                supportFragmentManager.beginTransaction().apply {
                    hide(activeFragment)
                    show(newFragment)
                    commit()
                }
                activeFragment = newFragment
            }
            else{
                Toast.makeText(this,"Duplicate",Toast.LENGTH_SHORT).show()
            }
            true
        }

    }



}