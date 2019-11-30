package csusm.cs443.cougarplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import csusm.cs443.cougarplanner.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_logged_in_main_view.*
import kotlinx.android.synthetic.main.activity_main.*

class Logged_in_main_view : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in_main_view)

        val mAuth = FirebaseAuth.getInstance()

        goto_my_courses.setOnClickListener {
            // Needs to go to course list page
            startActivity(Intent(this, ViewMyClasses::class.java))
        }

        goto_my_tasks.setOnClickListener {
            // Needs to go to task list page
            startActivity(Intent(this, ViewTasksCalendar::class.java))
        }

        goto_my_friends.setOnClickListener{
            startActivity(Intent(this, ViewFriends::class.java))
        }

        sign_out.setOnClickListener {
            mAuth!!.signOut()
            val user = FirebaseAuth.getInstance().currentUser

            //testing purposes
            if ( user == null)
                Toast.makeText(applicationContext, "Successfully signed out", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, "USER IS STILL SIGNED IN ", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

    }
}
