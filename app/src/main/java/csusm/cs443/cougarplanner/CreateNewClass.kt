package csusm.cs443.cougarplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner

class CreateNewClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_class)


        val cName  = findViewById<EditText>(R.id.TaskName)
        val pName = findViewById<EditText>(R.id.editText)
        // meeting days
        val mTime = findViewById<EditText>(R.id.timeInput)
        val color = findViewById<Spinner>(R.id.AssnTypeDropDown)
    }
}
