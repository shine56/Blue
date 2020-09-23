package com.shine56.blue

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shine56.blue.base.AppAdapter
import com.shine56.blue.base.BaseActivity
import com.shine56.blue.model.bean.RequestBean
import com.shine56.blue.ui.HomeVm
import com.shine56.blue.ui.WorkActivity
import com.shine56.blue.util.RecycleItemTouchHelper
import com.shine56.blue.util.logD
import com.shine56.blue.util.toast

class MainActivity : BaseActivity() {

    private lateinit var taskAdapter: AppAdapter<RequestBean>
    private lateinit var vm : HomeVm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this)[HomeVm::class.java]

        initView()
        onObserve()
        vm.refreshTask()
    }

    override fun onRestart() {
        super.onRestart()
        vm.refreshTask()
    }

    private fun initView(){
        initRecyclerView()
        val addBt = findViewById<FloatingActionButton>(R.id.add_request)
        addBt.setOnClickListener {
            startActivity(WorkActivity::class.java)
        }
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.task_recy)
        val manager = LinearLayoutManager(this)
        taskAdapter = AppAdapter<RequestBean>(R.layout.list_task)
        taskAdapter.setOnBindListener { list, holder, position ->
            holder.itemView.scrollTo(0, 0)
            val nameTv = holder.itemView.findViewById<TextView>(R.id.task_name)
            val deleteBt = holder.itemView.findViewById<Button>(R.id.delete_task)
            nameTv.text = list[position].baseUrl

            nameTv.setOnClickListener {
                val bundle = Bundle()
                "传送的id ${list[position].id}".logD()
                bundle.putInt("requestId", list[position].id)
                startActivity(WorkActivity::class.java, bundle)
            }
            deleteBt.setOnClickListener {
                vm.deleteTask(list[position])
            }
        }



        val callback = RecycleItemTouchHelper()
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        recyclerView.layoutManager = manager
        recyclerView.adapter = taskAdapter
    }

    private fun onObserve(){
        vm.taskList.observe(this, Observer {
            taskAdapter.replaceAll(it)
        })

        vm.deleteState.observe(this, Observer {
            if (it) "删除成功".toast()
            else "删除失败".toast()

            vm.refreshTask()
        })
    }



}