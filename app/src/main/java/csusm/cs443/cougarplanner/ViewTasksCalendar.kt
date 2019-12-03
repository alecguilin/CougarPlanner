package csusm.cs443.cougarplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_tasks_calendar.*

class ViewTasksCalendar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks_calendar)



        AddTaskBtn.setOnClickListener {
            startActivity(Intent(this, CreateNewTask::class.java))
        }
    }
}
