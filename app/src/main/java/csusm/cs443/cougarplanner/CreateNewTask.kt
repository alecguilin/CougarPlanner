package csusm.cs443.cougarplanner

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import android.app.TimePickerDialog
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ArrayAdapter
import csusm.cs443.cougarplanner.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_create_new_task.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.*


class CreateNewTask : AppCompatActivity() {

    internal lateinit var classOptions: Spinner
    internal lateinit var typeOptions: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_task)

//        classOptions = findViewById<Spinner>(R.id.AssociatedClassDropDown)
//        typeOptions = findViewById<Spinner>(R.id.AssnTypeDropDown)

//        val aClass = findViewById<Spinner>(R.id.AssociatedClassDropDown).toString()
//        val aType = findViewById<Spinner>(R.id.AssnTypeDropDown).toString()

        // Calendar
        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)

        // Time
        val mPickTimeBtn = findViewById<Button>(R.id.TimeBtn)
        val time     = findViewById<TextView>(R.id.Timetxt)



//        val classes = arrayOf("CS 441", "CS 421", "CS 436")
//        val types = arrayOf("HW", "Lab", "Project", "Reading", "Essay", "Exam")
//        val cAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, classes)
//        val tAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, types)
//        classOptions.adapter = cAdapter
//        typeOptions.adapter = tAdapter

        // Sets the Time for the Event
        mPickTimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        // Sets the Date for the Event
        DateBtn.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                Datetxt.setText(""+ mMonth + "/"+ mDay +"/" +mYear)
                year = mYear
                day = mDay
                month = mMonth+1
            }, year, month, day)
            dpd.show()
        }

        // Submits task to Firebase
        TaskSubmitButton.setOnClickListener {

            //sets whatever button is selected as task color
            var btnColor = "no color"

            if (redBtn.isChecked() == true)
                btnColor = "#FF0000"

            else if (blueBtn.isChecked() == true)
                btnColor = "#00BFFF"

            else if (greenBtn.isChecked() == true)
                btnColor = "#32CD32"

            else if (yellowBtn.isChecked() == true)
                btnColor = "#FFFF00"

            else if (purpleBtn.isChecked() == true)
                btnColor = "#9370DB"

            else if (pinkBtn.isChecked() == true)
                btnColor = "#FFC0CB"

            // Get Task Title and Notes
            var tTitle = findViewById<EditText>(R.id.TaskName).text.toString()
            var tNotes = findViewById<EditText>(R.id.Notes).text.toString()

            // Convert Date to String Format
            var dateString = year.toString() + "/"+ month.toString() + "/"+ day.toString()

            // Create Task Object
            var task = Task(tTitle, dateString, time.text.toString(), btnColor, tNotes.toString())

            // Get Current User uid to set path
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            var uid = firebaseUser?.getUid().toString()

            // Get Reference for Logged in user and push the new course
            val courseReference =
                FirebaseDatabase.getInstance().reference.
                    child("Users").child(uid).child("Tasks")
            courseReference.push().setValue(task)
        }
    }
    data class Task(
        var title: String = "",
        var due_date: String = "",
        var due_time: String = "",
        var color: String = "",
        var notes: String = "")
}
