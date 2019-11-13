package csusm.cs443.cougarplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_my_classes.*

class ViewMyClasses : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_my_classes)

        addClassBtn.setOnClickListener {
            startActivity(Intent(this, CreateNewClass::class.java))
        }
    }
}
