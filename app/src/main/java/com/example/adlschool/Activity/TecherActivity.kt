package com.example.adlschool.Activity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.adlschool.Adapter.CustomAdapterTeacher
import com.example.adlschool.Model.Teacher
import com.example.adlschool.databinding.ActivityTecherBinding
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONArray
import java.net.URL
import java.util.Scanner


class TecherActivity :  AppCompatActivity() {
    lateinit var binding: ActivityTecherBinding
    lateinit var teachers:ArrayList<Teacher>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTecherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listTeacher.layoutManager = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)
        teachers = ArrayList()
        getdata()

        binding.btnBack.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in))
            finish()
        }

        binding.edtSerch.addTextChangedListener { s->
            var teachers = ArrayList<Teacher>()
            this.teachers.forEach {
                if (it.name.contains(s.toString()))
                    teachers.add(it)
            }
            binding.listTeacher.adapter = CustomAdapterTeacher(teachers,this)
        }
    }

    fun getdata(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/ali-saranj/Adl_School/master/Teacher"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                teachers = parseToTeacher(response)
                binding.progressBarListTeacher.visibility = GONE
                binding.listTeacher.adapter = CustomAdapterTeacher(teachers,this)
            },
            {
                Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    fun parseToTeacher(s :String):ArrayList<Teacher> {
        var teachers =ArrayList<Teacher>()
        var jsonArray = JSONArray(s)
        for (i in 0 until jsonArray.length()){
            teachers.add(Teacher(jsonArray.getJSONObject(i).getString("image"),jsonArray.getJSONObject(i).getString("lesone"),jsonArray.getJSONObject(i).getString("name"),))
        }
        return teachers
    }

}