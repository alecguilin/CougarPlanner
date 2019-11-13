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

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
//        val name = findViewById<EditText>(R.id.TaskName)
//        val dDate = findViewById<EditText>(R.id.DueDate)
//        val aClass = findViewById<Spinner>(R.id.AssociatedClassDropDown)
//        val aType = findViewById<Spinner>(R.id.AssnTypeDropDown)

        data class Task(
            val name: String = "",
            val dDate: String = "",
            val aClass: String = "",
            val aType: String = "",
            var uuid: String = "")

        fun loadDatabase(firebaseData: DatabaseReference) {
            val availableSalads: List<Task> = mutableListOf(
                Task("Gherkin", "Fresh and delicious", "3", "4"),
                Task("Lettuce", "Easy to prepare", "5", "6"),
                Task("Tomato", "Boring but healthy", "6", "7"),
                Task("Zucchini", "Healthy and gross", "8", "9")
            )
            availableSalads.forEach {
                val key = firebaseData.child("").push().key!!
                it.uuid = key
                firebaseData.child("salads").child(key).setValue(it)
            }
        }
    }
}
