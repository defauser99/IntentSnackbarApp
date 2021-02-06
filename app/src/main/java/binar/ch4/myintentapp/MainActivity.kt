package binar.ch4.myintentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import binar.ch4.myintentapp.databinding.ActivityMainBinding
import binar.ch4.myintentapp.databinding.CustomDialogLayoutBinding
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
                    Toast.makeText(this, "Undo Successful", Toast.LENGTH_SHORT).show()
                }.show()
            }
        }

        //Standard Dialog
        //showStandardDialog("Welcome", "Click anywhere to close")

        //Action Dialog
        //showActionDialog("Do you enjoyed this app?", "Would you like to rate this app?")

        //Custom Dialog
        showCustomDialog("Hello, who are you?")

        //Fragment Dialog
        //showFragmentDialog("This is fragment dialog")
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

    private fun showStandardDialog(title:String, message:String){
        val standardDialog = AlertDialog.Builder(this)
        standardDialog.setTitle(title)
        standardDialog.setMessage(message)
        standardDialog.setCancelable(true)
        standardDialog.show()
    }

    private fun showActionDialog(title:String, message:String){
        val actionDialog = AlertDialog.Builder(this)
        actionDialog.setTitle(title)
        actionDialog.setMessage(message)
        actionDialog.setIcon(R.drawable.ic_baseline_star_rate_24)
        actionDialog.setCancelable(false)
        actionDialog.setPositiveButton("Sure"){ dialogInterface, _ ->
            Toast.makeText(this,"Thank you",Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }
        actionDialog.setNegativeButton("No, thank you"){ dialogInterface, _ ->
            Toast.makeText(this, "Dialog closed",Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }
        actionDialog.setNeutralButton("Remind me, later"){ dialogInterface, _ ->
            Toast.makeText(this, "Ok, we will remind you later",Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }
        actionDialog.create().show()
    }

    private fun showCustomDialog(title:String){
        val customDialogView = CustomDialogLayoutBinding.inflate(layoutInflater)
        val customDialogBuilder = AlertDialog.Builder(this)
        customDialogBuilder.setView(customDialogView.root)
        val customDialog = customDialogBuilder.create()
        customDialogView.textView.text = title
        customDialogView.btnSubmit.setOnClickListener {
            val name = customDialogView.editName.text?.toString()
            Toast.makeText(this, "Welcome, $name",Toast.LENGTH_SHORT).show()
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun showFragmentDialog(title:String){
        val customDialogFragment = CustomDialogFragment.newInstance(title,"")
        customDialogFragment.show(supportFragmentManager, "custom")
    }
}