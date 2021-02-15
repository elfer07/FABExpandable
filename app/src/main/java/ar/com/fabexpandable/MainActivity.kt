package ar.com.fabexpandable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<ExtendedFloatingActionButton>(R.id.fab)
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = ExampleAdapter()
        rv.adapter = adapter

        val listItems = ArrayList<String>()
        var i = 0
        while (i<30) {
            listItems.add("Example " + (i+1))
            i++
        }

        adapter.setData(listItems)

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy>0) {
                    fab.shrink()
                } else
                    fab.extend()
            }
        })
    }
}

class ExampleAdapter : RecyclerView.Adapter<ExampleAdapter.ViewHolder>() {
    private val itemList = ArrayList<String>()

    fun setData(items: List<String>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(itemList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tv = view.findViewById<TextView>(R.id.tv_item)

        fun binData(value: String) {
            tv.text = value
        }
    }
}