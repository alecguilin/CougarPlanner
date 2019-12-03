package csusm.cs443.cougarplanner

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.text.SimpleDateFormat
import android.app.TimePickerDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseReference
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.widget.*
import com.google.android.gms.tasks.Task
import csusm.cs443.cougarplanner.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_create_new_task.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.*
import kotlin.collections.HashMap


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
                btnColor =  "red"//"#FF0000"
            else if (blueBtn.isChecked() == true)
                btnColor = "blue"//"#00BFFF"
            else if (greenBtn.isChecked() == true)
                btnColor = "green"//"#32CD32"
            else if (yellowBtn.isChecked() == true)
                btnColor = "yellow"//"#FFFF00"
            else if (purpleBtn.isChecked() == true)
                btnColor = "purple"//"#9370DB"
            else if (pinkBtn.isChecked() == true)
                btnColor = "pink"//"#FFC0CB"

            // Get Task Title and Notes
            var tTitle = findViewById<EditText>(R.id.TaskName).text.toString()
            var tNotes = findViewById<EditText>(R.id.Notes).text.toString()

            // Convert Date to String Format
            var dateString = year.toString() + "/"+ month.toString() + "/"+ day.toString()

            // Create Task Object

            var tTime = time.text.toString()
            if (time.text.toString() == "Time"){
                tTime = "12:00"
            }

            var task = nTask(tTitle, dateString, tTime, btnColor, tNotes)

            // Get Current User uid to set path
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            var uid = firebaseUser?.getUid().toString()

            // Get Reference for Logged in user and push the new course
            val userReference =
                FirebaseDatabase.getInstance().reference.
                    child("Users").child(uid).child("Tasks")
            val key = userReference.push().key
            userReference.child(key.toString()).setValue(task)

            val tasks: MutableList<nTask> = mutableListOf()

            val menuListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    tasks.clear()
                    dataSnapshot.children.mapNotNullTo(tasks) { it.getValue<nTask>(nTask::class.java) }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    println("loadPost:onCancelled ${databaseError.toException()}")
                }
            }
            userReference.child("Tasks").addListenerForSingleValueEvent(menuListener)


//            var taskList = HashMap<String, String>()
//            taskList?.put(uid,key.toString())

//            val taskReference =
//                FirebaseDatabase.getInstance().reference.
//                    child("Users").child(uid).child("TaskList")
//            taskReference.setValue(taskList)

            // Iterate through HashMap
            Toast.makeText(applicationContext, "Tasks: ${tasks}", Toast.LENGTH_SHORT).show()
//            Toast.makeText(applicationContext, "Task has been successfully added.", Toast.LENGTH_SHORT).show()
        }

    }
//    data class Task(
//        var title: String = "",
//        var due_date: String = "",
//        var due_time: String = "",
//        var color: String = "",
//        var notes: String = "")
}
