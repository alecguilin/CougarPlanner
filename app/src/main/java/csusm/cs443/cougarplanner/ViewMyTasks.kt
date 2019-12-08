package csusm.cs443.cougarplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_new_task.*

class ViewMyTasks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_my_tasks)

//        val firebaseUser = FirebaseAuth.getInstance().currentUser
//        var uid = firebaseUser?.getUid().toString()
//
//
//        val courseRefernce = FirebaseDatabase.getInstance().reference.child("Users").
//                child(uid)
//
//        courseRefernce.once('value', function())



    }


}
