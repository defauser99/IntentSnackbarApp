package binar.ch4.myintentapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import binar.ch4.myintentapp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private var binding: ActivityMain2Binding? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        //Bundle
        val bundle = intent.extras
        val username = bundle?.getString("USERNAME","ANONYMOUS")
        val comment = bundle?.getString("COMMENT")

        //Serialize
        // val person = intent.getSerializableExtra("AN_OBJECT") as Person

        //Parcelable
        // val person2 = intent.getParcelableExtra<Person2>("AN_OBJECT")

        binding?.let {
            it.usernameView.text = "Comment from: $username"
            it.commentView.text = comment
        }
    }
}