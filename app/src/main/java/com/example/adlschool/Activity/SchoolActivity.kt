package com.example.adlschool.Activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adlschool.Adapter.CustomAdapterImage
import com.example.adlschool.R
import com.example.adlschool.databinding.ActivitySchoolBinding


class SchoolActivity : AppCompatActivity() {
    lateinit var binding: ActivitySchoolBinding
    var i= 0

    var html = "<p>دوره دوم متوسطه- نظری پسرانه عدل دانشگاه ، از جمله مدارس غیر دولتی استان اصفهان بوده که در محدوده ناحیه 3 اصفهان قرار دارد. مدرسه عدل دانشگاه به مدیریت ، در نشانی خیابان هزار جریب، مقابل سازمان جهاد کشاورزی، درب جنگلبانی دانشگاه اصفهان، پشت دبیرستان البرز واقع شده است.\n" +
            "\n" +
            "این مدرسه که در لیست مدارس غیر دولتی اصفهان قرار دارد دارای امکانات علمی و آموزشی متنوعی برای دانش آموزان دوره دوم متوسطه- نظری می باشد و آمادگی پاسخگویی مستمر به سوالات اولیاء گرامی اصفهان را با تماس با تلفن true فراهم نموده است." +
            "<h2>تاسیس</h2>" +
            "<p>مدرسه پسرانه عدل دانشگاه با مشارکت و تلاش بی وقفه ی جمعی از خیرین مدرسه ساز پس از 2ساله در سال 1353 وارد چرخه آموزشی کشور شده و پذیرای فرزندان ایران زمین بوده است.\n" +
            "\n" +
            "پسرانه دوره دوم متوسطه- نظری عدل دانشگاه، دارای بنای آموزشی 335 مترمربع می باشد. همچنین مساحت محیط ورزشی و سرباز مدرسه ی عدل دانشگاه، به میزان 513 متر مربع بوده که از این منظر، نمره قابل قبولی دارد.</p>"
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listImage.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.listImage.adapter = CustomAdapterImage(resources.getTextArray(R.array.url_image),this)


        binding.tvSescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(html)
        }

        Thread{
            while(true){
                Thread.sleep(2000)
                runOnUiThread{
                    binding.listImage.scrollToPosition(i)
                }
                i++
                if (i>resources.getTextArray(R.array.url_image).size)
                    i=0
            }
        }.start()

        binding.call.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),1)
            }else{
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:031 3669 8877")))
            }
        }

        binding.gps.setOnClickListener {
            try {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/32.6803004,51.7678502/%D8%B9%D8%AF%D9%84+%D8%AF%D8%A7%D9%86%D8%B4%DA%AF%D8%A7%D9%87+%D8%A7%D8%B5%D9%81%D9%87%D8%A7%D9%86%E2%80%AD%E2%80%AD/@32.6309359,51.7897809,12z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3fbc37caa54988d3:0x9eda9b7ffd23fb7e!2m2!1d51.6630161!2d32.6034533"))
                startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this, "No application can handle this request."
                            + " Please install a webbrowser", Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }
        }

        binding.tvSescription.setOnClickListener {
            if (binding.tvSescription.maxLines == 5){
                binding.tvSescription.maxLines = 100
            }else{
                binding.tvSescription.maxLines = 5
            }
        }

    }
}