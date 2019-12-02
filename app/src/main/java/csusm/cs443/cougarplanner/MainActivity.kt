package csusm.cs443.cougarplanner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import csusm.cs443.cougarplanner.ui.login.LoginActivity
import csusm.cs443.cougarplanner.ui.login.SignUp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Toast.makeText(this, "Welcome!", Toast.LENGTH_LONG).show()

        val mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        //if user is already signed in, bypass login page and redirect to logged_in_main_view
        if(currentUser!=null)
        {
            //Toast.makeText(this, "User Already Signed In!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Logged_in_main_view::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        // test database push
        //val database = FirebaseDatabase.getInstance()
        //val myRef = database.getReference("Name")
        //myRef.setValue("Bobby Jackson")

        BIGBUTTON.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))

        }

        BIGBUTTON2.setOnClickListener {
            startActivity(Intent( this, SignUp::class.java))
        }
    }
}

