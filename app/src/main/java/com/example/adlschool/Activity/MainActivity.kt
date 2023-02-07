package com.example.adlschool.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adlschool.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageMain.animate().alpha(1f).setDuration(3000).duration
        Thread{
            Thread.sleep(3500)
            finish()
            startActivity(Intent(this, SchoolActivity::class.java))
        }.start()
    }
}