package com.shine56.blue.ui.arrange

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shine56.blue.R
import com.shine56.blue.base.AppAdapter
import com.shine56.blue.databinding.ArrangeFragmentBinding
import com.shine56.blue.ui.WorkActivity
import com.shine56.blue.ui.request.RequestVm

class ArrangeFragment : Fragment() {

    companion object {
        fun newInstance() = ArrangeFragment()
    }

    private lateinit var viewModel: RequestVm
    private lateinit var binding : ArrangeFragmentBinding
    private lateinit var workActivity: WorkActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.arrange_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        workActivity = activity as WorkActivity
        viewModel = ViewModelProvider(workActivity).get(RequestVm::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = workActivity
        initView()
    }

    private fun initView(){
        val manager = LinearLayoutManager(activity)
        manager.orientation = LinearLayoutManager.HORIZONTAL

        val adapter = AppAdapter<String>(R.layout.list_arrange_tool)
        adapter.setOnBindListener { list, holder, position ->
            val toolBt = holder.itemView.findViewById<Button>(R.id.tool)
            toolBt.text = list[position]
            toolBt.setOnClickListener {
                viewModel.selectTool = position
            }
        }

        binding.toolRecy.layoutManager = manager
        binding.toolRecy.adapter = adapter
        val tools = listOf("unicode转中文", "去掉字符","替换字符", "正则")
        adapter.replaceAll(tools)

        binding.arrange.setOnClickListener {
            viewModel.arrange(binding.sign.text.toString())
        }
    }


    fun onCLick(v: View){
        when(v.id){
            R.id.tool -> {

            }
        }
    }


}