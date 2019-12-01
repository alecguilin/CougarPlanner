package csusm.cs443.cougarplanner.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import csusm.cs443.cougarplanner.Logged_in_main_view

import csusm.cs443.cougarplanner.R

//validates correct email syntax
class EmailValidator {
    companion object {
        @JvmStatic
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

        fun isEmailValid(email: String): Boolean {
            return EMAIL_REGEX.toRegex().matches(email);
        }
    }
}

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        var mAuth = FirebaseAuth.getInstance()
        var firebaseDatabase = FirebaseDatabase.getInstance()
        var database = firebaseDatabase.getReference("Users").push()

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val signupbutton = findViewById<Button>(R.id.Login)
        val pb = findViewById(R.id.loading) as ProgressBar

        signupbutton.setOnClickListener {

            val email: String = username.text.toString()
            val pass: String =password.text.toString()
            var attemptSuccess = true

            pb.visibility = ProgressBar.VISIBLE

            if(!EmailValidator.isEmailValid(email)) {
                Toast.makeText(applicationContext, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                attemptSuccess = false
            }
            if (pass.trim().length < 5){
                Toast.makeText(applicationContext, "Password must be at least five characters long", Toast.LENGTH_SHORT).show()
                attemptSuccess = false
            }

            if (attemptSuccess == true)
            {
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        // Sign in success
                        Toast.makeText(applicationContext, "Sign Up Success!", Toast.LENGTH_SHORT).show()

                        //remove progress bar
                        pb.visibility = ProgressBar.INVISIBLE

                        //once successfully logged in, logged_in_main_view becomes the new root activity
                        //this removes the ability for the user to use the back button to access the previous 2 activities

                        //when user re opens app they are brought back to the beginning, requiring another log in
                        //we can change this implementation later

                        // Create Blank Template for user in the database
                        var user = User("", email)
                        database.setValue(user)


                        val intent = Intent(this, Logged_in_main_view::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    } else {
                        pb.visibility = ProgressBar.INVISIBLE
                        Toast.makeText(applicationContext, "Could not create account. Password must include characters and numbers. Please try again later.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            pb.visibility = ProgressBar.INVISIBLE
        }
    }
    data class User(var username: String, var email: String)
}