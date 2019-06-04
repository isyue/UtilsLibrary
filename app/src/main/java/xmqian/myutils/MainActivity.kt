package xmqian.myutils

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import xmqian.myutils.simple.*

import java.util.ArrayList
import java.util.Arrays


class MainActivity : AppCompatActivity() {
    private var items: List<String>? = null
    //可变列表
    private lateinit var activitys: MutableList<Class<*>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val stringArray = resources.getStringArray(R.array.item_name)
        items = Arrays.asList(*stringArray)
        activitys = ArrayList()
        addActivity()
        val listView = findViewById<View>(R.id.lv_list) as ListView
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items!!)
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            print("MainList  position:" + items!![position])
            goActivity(activitys[position])
        }
    }

    private fun addActivity() {
        activitys.add(ToastActivity::class.java)
        activitys.add(DensitySimpleActivity::class.java)
        activitys.add(PhoneInfoActivity::class.java)
        activitys.add(TimeSimpleActivity::class.java)
        activitys.add(CheckInputActivity::class.java)
        activitys.add(FragmentActivity::class.java)
    }

    private fun goActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }
}
