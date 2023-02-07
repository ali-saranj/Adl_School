package com.example.adlschool.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.adlschool.Adapter.CustomAdapterTeacher
import com.example.adlschool.Model.Teacher
import com.example.adlschool.databinding.ActivityMangerBinding
import org.json.JSONArray
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class MangerActivity : AppCompatActivity() {
    lateinit var binding: ActivityMangerBinding
    lateinit var teachers: ArrayList<Teacher>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listManger.layoutManager = GridLayoutManager(this,3,
            GridLayoutManager.VERTICAL,false)
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
            binding.listManger.adapter = CustomAdapterTeacher(teachers,this)
        }
    }

    fun getdata(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/ali-saranj/Adl_School/master/mnager"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                teachers = parseToTeacher(response)
                binding.progressBarListManger.visibility = View.GONE
                binding.listManger.adapter = CustomAdapterTeacher(teachers,this)
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