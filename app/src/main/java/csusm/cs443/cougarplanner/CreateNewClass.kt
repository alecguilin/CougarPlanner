package csusm.cs443.cougarplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.content.Intent
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
//import sun.jvm.hotspot.utilities.IntArray
import android.widget.Button
import com.google.firebase.auth.FirebaseUser
import android.widget.ArrayAdapter
import csusm.cs443.cougarplanner.ViewMyClasses

import com.google.firebase.auth.FirebaseAuth
////import androidx.core.app.ComponentActivity
////import androidx.core.app.ComponentActivity.ExtraData
//import androidx.core.content.ContextCompat.getSystemService
//import android.icu.lang.UCharacter.GraphemeClusterBreak.T
//import sun.jvm.hotspot.utilities.IntArray



class CreateNewClass : AppCompatActivity() {

    internal lateinit var colorOptions: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_class)

        colorOptions = findViewById<Spinner>(R.id.color)

        val colors = arrayOf("Red", "Blue", "Yellow", "Green", "")
        //var firebaseDatabase = FirebaseDatabase.getInstance()
        //var database = firebaseDatabase.getReference("Users").push()
        //var database = FirebaseDatabase.getInstance().reference

        val cName  = findViewById<EditText>(R.id.ClassName)
        val pName = findViewById<EditText>(R.id.editText)
        val cAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, colors)
        val addCourseButton = findViewById<Button>(R.id.addCourseButton)

        colorOptions.adapter = cAdapter
        // meeting days
        //val mTime = findViewById<EditText>(R.id.timeInput)
        val color = findViewById<Spinner>(R.id.color)

        addCourseButton.setOnClickListener{
            val course_name: String = cName.text.toString()
            val professor: String = pName.text.toString()

            var course = Course(course_name, professor)


            val firebaseUser = FirebaseAuth.getInstance().currentUser   // Instance of Current User
            var uid = firebaseUser?.getUid().toString()// Get Current User's ID and converts to string

            //database.child(uid).setValue(course)

            // Get Reference for Logged in user and push the new course
            val courseReference =
                FirebaseDatabase.getInstance().reference.
                    child("Users").child(uid).child("Courses")
            courseReference.push().setValue(course)

            // Go Back to Class View
            startActivity(Intent(this, ViewMyClasses::class.java))
        }
   }
    data class Course(var name: String, var professor: String)
}
