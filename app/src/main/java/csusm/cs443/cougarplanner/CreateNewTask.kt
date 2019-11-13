package csusm.cs443.cougarplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner

class CreateNewTask : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_task)

        val name = findViewById<EditText>(R.id.TaskName)
        val dDate = findViewById<EditText>(R.id.DueDate)
        val aClass = findViewById<Spinner>(R.id.AssociatedClassDropDown)
        val aType = findViewById<Spinner>(R.id.AssnTypeDropDown)
    }
}
