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
import com.google.firebase.auth.FirebaseAuth
import csusm.cs443.cougarplanner.Logged_in_main_view

import csusm.cs443.cougarplanner.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        val mAuth = FirebaseAuth.getInstance()

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val Login = findViewById<Button>(R.id.Login)

        //add progress bar
        val pb = findViewById(R.id.loading) as ProgressBar


        Login.setOnClickListener {
            val uname: String = username.text.toString()
            val pass: String =password.text.toString()

            if(uname.trim().length == 0) {
                Toast.makeText(applicationContext, "Must enter a username", Toast.LENGTH_SHORT).show()
            }
            if (pass.trim().length == 5){
                Toast.makeText(applicationContext, "Password must be at least five characters long", Toast.LENGTH_SHORT).show()
            }

            pb.visibility = ProgressBar.VISIBLE

            //firebase authentication
            //authenticates the user via firebase given user input

            //our account:
            //email: TeamNIWWD@gmail.com
            //pass: cougars
            mAuth.signInWithEmailAndPassword(uname, pass).addOnCompleteListener(this) { task ->



                if (task.isSuccessful) {

                    // Sign in success
                    Toast.makeText(applicationContext, "Login Success!", Toast.LENGTH_SHORT).show()

                    //remove progress bar
                    pb.visibility = ProgressBar.INVISIBLE

                    //once successfully logged in, logged_in_main_view becomes the new root activity
                    //this removes the ability for the user to use the back button to access the previous 2 activities

                    //when user re opens app they are brought back to the beginning, requiring another log in
                    //we can change this implementation later

                    val intent = Intent(this, Logged_in_main_view::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "ERROR: wrong username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
