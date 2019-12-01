package csusm.cs443.cougarplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_view_my_classes.*

class ViewMyClasses : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_my_classes)

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var database = firebaseDatabase.getReference("Courses").push()

        var course1 = Course("CS441","Zheng", "Thursday", "Red" )
        var course2 = Course("CS436","Nahid", "Thursday", "Green" )
        var course3 = Course("CS421","Rika Yoshi", "Tuesday", "Blue" )
        var course4 = Course("CS485","Ignatovsky", "Wednesday", "Yellow" )

        //database.setValue(course1)
        database.setValue(course4)
//        database.setValue(course3)
//        database.setValue(course4)

        // Read from db
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var value = dataSnapshot!!.value as HashMap<String, Any>
                Log.d("Value", value.get("name").toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error", error!!.message)
            }
        })

        addClassBtn.setOnClickListener {
            startActivity(Intent(this, CreateNewClass::class.java))
        }
    }
    data class Course(var name: String, var professor: String, var meetings: String, var color: String)
}