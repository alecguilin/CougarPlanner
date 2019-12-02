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

import csusm.cs443.cougarplanner.ViewMyClasses

import com.google.firebase.auth.FirebaseAuth
////import androidx.core.app.ComponentActivity
////import androidx.core.app.ComponentActivity.ExtraData
//import androidx.core.content.ContextCompat.getSystemService
//import android.icu.lang.UCharacter.GraphemeClusterBreak.T
//import sun.jvm.hotspot.utilities.IntArray

class CreateNewClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_class)

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var database = firebaseDatabase.getReference("Users").push()

        val cName  = findViewById<EditText>(R.id.TaskName)
        val pName = findViewById<EditText>(R.id.editText)
        val addCourseButton = findViewById<Button>(R.id.addCourseButton)
        // meeting days
        //val mTime = findViewById<EditText>(R.id.timeInput)
        val color = findViewById<Spinner>(R.id.AssnTypeDropDown)

        addCourseButton.setOnClickListener{
            val course_name: String = cName.text.toString()
            val professor: String = pName.text.toString()

            var course = Course(course_name, professor)


            val firebaseUser = FirebaseAuth.getInstance().currentUser
            var userReference = firebaseUser

            var uid = firebaseUser?.getUid().toString()

            database.child(uid).setValue(course)
            startActivity(Intent(this, ViewMyClasses::class.java))
//            database.setValue(course)
//            startActivity(Intent(this, ViewMyClasses::class.java))

        }
   }
    data class Course(var name: String, var professor: String)
}
