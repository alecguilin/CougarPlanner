package csusm.cs443.cougarplanner

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.color_spinner_row.view.*

class customSpinnerAdapter(context: Context, image: String) : BaseAdapter(){
    override fun getItem(p0: Int): Any{
        return color.length
    }

    override fun getItemId(p0: Int): Long{
        return 0
    }

    override fun getCount(): Int{
        return 0
    }

    var color : String = ""
    val inflater : LayoutInflater


    fun customeSpinnerAdapter(context: Context, image:String){
        this.color = image
        inflater = LayoutInflater.from(context)
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = inflater.inflate(R.layout.color_spinner_row, p2, false)
        view.image_view_color.setImageResource(color)
        return view

    }
}