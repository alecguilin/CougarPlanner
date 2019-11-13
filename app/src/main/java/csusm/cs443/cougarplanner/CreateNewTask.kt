package csusm.cs443.cougarplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class CreateNewTask : AppCompatActivity() {

    lateinit var option : Spinner
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_task)

        val name = findViewById<EditText>(R.id.TaskName)
        val dDate = findViewById<EditText>(R.id.DueDate)
        val aClass = findViewById<Spinner>(R.id.AssociatedClassDropDown)
        val aType = findViewById<Spinner>(R.id.AssnTypeDropDown)

        val testArry: Array<String> = arrayOf("This", "That", "Kitty Cat")

        option = findViewById(R.id.AssociatedClassDropDown) as Spinner
    }
}
