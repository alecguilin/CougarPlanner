package csusm.cs443.cougarplanner

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

//import com.squareup.picasso.Picasso
//import com.squareup.picasso:picasso:2.71828


class TaskAdapter(private val context: Context,
                    private val dataSource: MutableList<nTask>) : BaseAdapter() {

    //val picasso = Picasso.get()

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

//    companion object {
//        private val LABEL_COLORS = hashMapOf(
//            "Low-Carb" to R.color.colorLowCarb,
//            "Low-Fat" to R.color.colorLowFat,
//            "Low-Sodium" to R.color.colorLowSodium,
//            "Medium-Carb" to R.color.colorMediumCarb,
//            "Vegetarian" to R.color.colorVegetarian,
//            "Balanced" to R.color.colorBalanced
//        )
//    }

    fun remove(position: Int){
        dataSource.removeAt(position)
        notifyDataSetChanged()
    }
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder


        if (convertView == null) {

            view = inflater.inflate(R.layout.list_item, parent, false)


            holder = ViewHolder()
            holder.thumbnailImageView = view.findViewById(R.id.task_list_color) as ImageView
            holder.titleTextView = view.findViewById(R.id.task_list_title) as TextView
            holder.subtitleTextView = view.findViewById(R.id.task_list_subtitle) as TextView
            holder.detailTextView = view.findViewById(R.id.task_list_detail) as TextView

            view.tag = holder
        } else {

            view = convertView
            holder = convertView.tag as ViewHolder
        }


        val titleTextView = holder.titleTextView
        val subtitleTextView = holder.subtitleTextView
        val detailTextView = holder.detailTextView
        val thumbnailImageView = holder.thumbnailImageView



        val task = getItem(position) as nTask

        titleTextView.text = task.title
        subtitleTextView.text = task.notes
        detailTextView.text = task.due_date

        when(task.color){
            "pink" -> thumbnailImageView.setColorFilter(ContextCompat.getColor(context, R.color.pink))
            "blue" -> thumbnailImageView.setColorFilter(ContextCompat.getColor(context, R.color.blue))
            "red" -> thumbnailImageView.setColorFilter(ContextCompat.getColor(context, R.color.red))
            "green" -> thumbnailImageView.setColorFilter(ContextCompat.getColor(context, R.color.green))
            "yellow" -> thumbnailImageView.setColorFilter(ContextCompat.getColor(context, R.color.yellow))
            "purple" -> thumbnailImageView.setColorFilter(ContextCompat.getColor(context, R.color.purple))

        }

        //Picasso.with(context).load(task.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

//        val titleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_bold)
//        titleTextView.typeface = titleTypeFace
//
//        val subtitleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_semibolditalic)
//        subtitleTextView.typeface = subtitleTypeFace
//
//        val detailTypeFace = ResourcesCompat.getFont(context, R.font.quicksand_bold)
//        detailTextView.typeface = detailTypeFace
//
//        detailTextView.setTextColor(
//            ContextCompat.getColor(context, LABEL_COLORS[task.label] ?: R.color.colorPrimary))

        return view
    }

    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var subtitleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var thumbnailImageView: ImageView
    }
}
