package csusm.cs443.cougarplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.widget.ListView
import android.widget.AdapterView
import android.view.View
import android.widget.Toast
import android.widget.AbsListView
import com.hudomju.swipe.SwipeToDismissTouchListener
import com.hudomju.swipe.adapter.ListViewAdapter
import java.util.ArrayList
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_view_my_classes.*

class ViewMyClasses : AppCompatActivity() {


    //    val firebaseUser = FirebaseAuth.getInstance().currentUser
//    var uid = firebaseUser?.getUid().toString()
//    var ref = FirebaseDatabase.getInstance().reference.child("Users").child(uid)
//    val taskList: MutableList<Task> = mutableListOf()
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_my_classes)

        listView = findViewById(R.id.task_list_view)

        //val taskList = nTask.getTask("tasks.json", this)
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        var uid = firebaseUser?.getUid().toString()

        // Get Reference for Logged in user and push the new course
        val reference =
            FirebaseDatabase.getInstance().reference.child("Tasks")

        val taskList: MutableList<nTask> = mutableListOf()

        fun initTaskList() {
            val taskListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    taskList.clear()
                    dataSnapshot.children.mapNotNullTo(taskList) { it.getValue<nTask>(nTask::class.java) }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("loadPost:onCancelled ${databaseError.toException()}")
                }
            }
            reference.addValueEventListener(taskListener)
        }

        initTaskList()
        val adapter = TaskAdapter(this, taskList)
        listView.adapter = adapter

        val touchListener = SwipeToDismissTouchListener(
            ListViewAdapter(listView),
            object : SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter> {
                override fun canDismiss(position: Int): Boolean {
                    return true
                }

                override fun onDismiss(view: ListViewAdapter, position: Int) {
                    adapter!!.remove(position)
                }
            })
        listView!!.setOnTouchListener(touchListener)
        listView!!.setOnScrollListener(touchListener.makeScrollListener() as AbsListView.OnScrollListener)
        listView!!.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                if (touchListener.existPendingDismisses()) {
                    touchListener.undoPendingDismiss()
                } else {
                    Toast.makeText(this@ViewMyClasses, "Position $position", LENGTH_SHORT).show()
                }
            }

    }
}


//        val firebaseUser = FirebaseAuth.getInstance().currentUser
//        var uid = firebaseUser?.getUid().toString()

//        var ref = FirebaseDatabase.getInstance().reference.child("Users").child(uid)



//        ref.addValueEventListener(object: ValueEventListener{
//            override fun onDataChange(dataSnapshot: DataSnapshot){
//                val post = dataSnapshot.getValue(String::class.java)
//            }
//        })
        //database.setValue(course1)
        //database.setValue(course4)
//        database.setValue(course3)
//        database.setValue(course4)

        // Read from db
//        database.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                var value = dataSnapshot!!.value as HashMap<String, Any>
//                Log.d("Value", value.get("name").toString())
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("Error", error!!.message)
//            }
//        })




