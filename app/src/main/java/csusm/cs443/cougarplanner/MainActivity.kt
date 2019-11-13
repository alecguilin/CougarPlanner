package csusm.cs443.cougarplanner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import csusm.cs443.cougarplanner.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Hi there! This is a Toast.", Toast.LENGTH_LONG).show()

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Name")

        myRef.setValue("Bobby Jackson")

        BIGBUTTON.setOnClickListener {
            startActivity(Intent(this, Logged_in_main_view::class.java))
        }
    }
}

