package csusm.cs443.cougarplanner

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



class CreateNewTask : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_task)

        val database = FirebaseDatabase.getInstance().reference


        database.setValue("Hello, World!")

        val name = findViewById<EditText>(R.id.TaskName).toString()
        val dDate = findViewById<EditText>(R.id.DueDate).toString()
        val aClass = findViewById<Spinner>(R.id.AssociatedClassDropDown).toString()
        val aType = findViewById<Spinner>(R.id.AssnTypeDropDown).toString()

        data class Task(
            val name: String = "",
            val dDate: String = "",
            val aClass: String = "",
            val aType: String = "",
            var uuid: String = "")

        fun writeNewTask(name: String, dDate: String, aClass: String, aType:String) {
            val task = Task(name, dDate, aClass, aType )
            database.child("task").child(name).setValue(task)
        }
        
//        writeNewTask(name, dDate, aClass, aType)
          writeNewTask("New", "11/10", "CS441", "HW")
    }
}
