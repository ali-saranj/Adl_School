package com.example.adlschool.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adlschool.Adapter.CustomAdapterNew
import com.example.adlschool.Adapter.CustomAdapterTeacher
import com.example.adlschool.Model.New
import com.example.adlschool.Model.Teacher
import com.example.adlschool.databinding.ActivityNewBinding
import org.json.JSONArray
import java.net.URI
import java.net.URL
import java.util.Scanner

class NewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    lateinit var news : ArrayList<New>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        news = ArrayList()

        binding.listNew.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        getdata()

        binding.btnBack.setOnClickListener{
            it.startAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in))
            finish()
        }

        binding.edtSerch.addTextChangedListener { s->
            var news = ArrayList<New>()
            this.news.forEach {
                if (it.text.contains(s.toString()))
                    news.add(it)
            }
            binding.listNew.adapter = CustomAdapterNew(news,this)
        }
    }

    fun getdata(){
        var s = ""
        Thread{
            try {
                var input = Scanner(URL("https://raw.githubusercontent.com/ali-saranj/Adl_School/master/news").openStream())
                while (input.hasNext()) {
                    s += input.nextLine()
                }
                var news = parseToTeacher(s)
                this.news.addAll(news)
                runOnUiThread {
                    binding.progressBarListTeacher.visibility = View.GONE
                    binding.listNew.adapter = CustomAdapterNew(news,this)
                }

            }catch (e:Exception){
                e.printStackTrace()
            }
        }.start()
    }

    fun parseToTeacher(s :String):ArrayList<New> {
        var news =ArrayList<New>()
        var jsonArray = JSONArray(s)
        for (i in 0 until jsonArray.length()){
            news.add(New(jsonArray.getJSONObject(i).getString("image"),jsonArray.getJSONObject(i).getString("text")))
        }
        return news
    }
}