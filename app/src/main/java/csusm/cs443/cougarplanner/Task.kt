package csusm.cs443.cougarplanner

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class nTask(
    var title: String = "",
    var due_date: String = "",
    var due_time: String = "",
    var color: String = "",
    var notes: String = "",
    var user: String = "",
    var taskId: String = ""){

    companion object{

        fun getTask(): MutableList<nTask>{
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            var uid = firebaseUser?.getUid().toString()

            // Get Reference for Logged in user and push the new course
            val reference =
                FirebaseDatabase.getInstance().reference.
                    child("Tasks")
            val taskList = mutableListOf<nTask>()

            val taskListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Get Post object and use the values to update the UI
                    val task = dataSnapshot.getValue(nTask::class.java)
                    // ...
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    println("loadPost:onCancelled ${databaseError.toException()}")                    // ...
                }
            }
            reference.addValueEventListener(taskListener)


//            try{
//                val jsonString = loadJsonFromAsset("tasks.json", context)
//                val json = JSONObject(jsonString)
//                val tasks = json.getJSONArray("tasks")
//
//                (0 until tasks.length()).mapTo(taskList){
//                    nTask(tasks.getJSONObject(it).getString("title"),
//                        tasks.getJSONObject(it).getString("due_date"),
//                        tasks.getJSONObject(it).getString("due_time"),
//                        tasks.getJSONObject(it).getString("color"),
//                        tasks.getJSONObject(it).getString("notes"),
//                         tasks.getJSONObject(it).getString("user"))
//                }
//            } catch (e: JSONException){
//                e.printStackTrace()
//            }


            return taskList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            var json: String? = null

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }
    }
}
