package ar.com.fabexpandable

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.fabexpandable.databinding.ActivityMainBinding
import ar.com.fabexpandable.databinding.ItemViewBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = ExampleAdapter()
        binding.rv.adapter = adapter

        val listItems = ArrayList<String>()
        var i = 0
        while (i<30) {
            listItems.add("Example " + (i+1))
            i++
        }

        adapter.setData(listItems)

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy>0) {
                    binding.fab.shrink()
                } else
                    binding.fab.extend()
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
        val itemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(itemViewBinding, parent.context)
        return holder
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(itemList[position])
    }

    inner class ViewHolder(val binding: ItemViewBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun binData(value: String) {
            binding.tvItem.text = value
        }
    }
}