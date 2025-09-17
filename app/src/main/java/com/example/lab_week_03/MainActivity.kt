package com.example.lab_week_03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity(), MainActivity.CoffeeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val fragmentContainer = findViewById<FragmentContainerView>(R.id.fragment_container)
        ViewCompat.setOnApplyWindowInsetsListener(fragmentContainer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            val listFragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .add(fragmentContainer.id, listFragment)
                .commit()
        }
    }

    override fun onSelected(id: Int) {
        val fragmentContainer = findViewById<FragmentContainerView>(R.id.fragment_container)
        val detailFragment = DetailFragment.newInstance(id)
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainer.id, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    interface CoffeeListener {
        fun onSelected(id: Int)
    }
}
