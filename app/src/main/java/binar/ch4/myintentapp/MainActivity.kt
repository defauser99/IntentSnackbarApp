package binar.ch4.myintentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import binar.ch4.myintentapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.let {

            it.sendButton2.setOnClickListener {
                val comment = binding!!.commentLayout.editText?.text.toString()
                shareIntent(comment)
            }

            it.sendButton.setOnClickListener {
                val username = binding!!.usernameLayout.editText?.text.toString()
                val comment = binding!!.commentLayout.editText?.text.toString()
                postIntent(username, comment)
            }

            it.commentLayout.setEndIconOnClickListener {
                val temp = binding!!.commentLayout.editText?.text.toString()
                binding!!.commentLayout.editText?.text?.clear()
                Snackbar.make(binding!!.root, "Comment Deleted", Snackbar.LENGTH_LONG).setAction("Undo") {
                    binding!!.commentLayout.editText?.setText(temp)
                }.show()
            }
        }
    }

    private fun shareIntent(comment: String) {
        val intent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, comment)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    private fun postIntent(username: String, comment: String) {
        val intent = Intent(this, MainActivity2::class.java).apply {
            //Bundle
            val bundle = Bundle()
            bundle.putString("USERNAME", username)
            bundle.putString("COMMENT", comment)
            this.putExtras(bundle)

            //Serialize
            //val person = Person(username, comment)
            //intent.putExtra("AN_OBJECT", person)

            //Parcelable
            //val person2 = Person2(username, comment)
            //intent.putExtra("AN_OBJECT", person2)
        }
        startActivity(intent)
    }
}