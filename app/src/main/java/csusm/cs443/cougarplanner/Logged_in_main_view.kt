package csusm.cs443.cougarplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import csusm.cs443.cougarplanner.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_logged_in_main_view.*
import kotlinx.android.synthetic.main.activity_main.*

class Logged_in_main_view : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in_main_view)

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
    }
}
