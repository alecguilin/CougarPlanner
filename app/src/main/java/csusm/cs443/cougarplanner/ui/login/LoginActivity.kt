package csusm.cs443.cougarplanner.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import csusm.cs443.cougarplanner.Logged_in_main_view

import csusm.cs443.cougarplanner.R

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val Login = findViewById<Button>(R.id.Login)


        Login.setOnClickListener {
            val uname: String = username.text.toString()
            val pass: String =password.text.toString()

            if(uname.trim().length == 0) {
                Toast.makeText(applicationContext, "Must enter a username", Toast.LENGTH_SHORT).show()
            }
            if (pass.trim().length == 5){
                Toast.makeText(applicationContext, "Password must be at least five characters long", Toast.LENGTH_SHORT).show()
            }
            if (uname.equals("TeamNIWWD") && (pass.equals("cougars"))){
                val intent = Intent(this, Logged_in_main_view::class.java)
                startActivity(intent)
            }

            else{
                Toast.makeText(applicationContext, "ERROR: wrong username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }


}