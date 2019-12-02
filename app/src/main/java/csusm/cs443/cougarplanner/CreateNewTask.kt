package csusm.cs443.cougarplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
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


class CreateNewTask : AppCompatActivity() {

    internal lateinit var classOptions: Spinner
    internal lateinit var typeOptions: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_task)

        classOptions = findViewById<Spinner>(R.id.AssociatedClassDropDown)
        typeOptions = findViewById<Spinner>(R.id.AssnTypeDropDown)

        val classes = arrayOf("CS 441", "CS 421", "CS 436")
        val types = arrayOf("HW", "Lab", "Project", "Reading", "Essay", "Exam")
        val cAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, classes)
        val tAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, types)
        classOptions.adapter = cAdapter
        typeOptions.adapter = tAdapter

        TaskSubmitButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.TaskName).text.toString()
            val dDate = findViewById<EditText>(R.id.DueDate).text.toString()
            val aClass = findViewById<Spinner>(R.id.AssociatedClassDropDown).toString()
            val aType = findViewById<Spinner>(R.id.AssnTypeDropDown).toString()

            var task = Task(name, dDate, aClass, aType)

            database.setValue(task)



        }
  //         writeNewTask(name, dDate, aClass, aType)
    //      writeNewTask("New", "11/10", "CS441", "HW")
    }

    private val database = FirebaseDatabase.getInstance().reference


    data class Task(
        val name: String = "",
        val dDate: String = "",
        val aClass: String = "",
        val aType: String = "",
        var uuid: String = "")

//    private fun writeNewTask(name: String, dDate: String, aClass: String, aType:String) {
//        val task = Task(name, dDate, aClass, aType )
//        database.child("task").child(name).setValue(task)
//    }




}
