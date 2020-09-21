package com.shine56.blue.ui.result

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shine56.blue.R
import com.shine56.blue.base.MyApplication.Companion.FAIL
import com.shine56.blue.base.MyApplication.Companion.NOT_URL
import com.shine56.blue.base.MyApplication.Companion.SUCCESS
import com.shine56.blue.databinding.ResultFragmentBinding
import com.shine56.blue.ui.WorkActivity
import com.shine56.blue.ui.request.RequestVm
import com.shine56.blue.util.toast

class ResultFragment : Fragment() {

    private lateinit var binding: ResultFragmentBinding
    private lateinit var workActivity: WorkActivity

    companion object {
        fun newInstance() = ResultFragment()
    }

    private lateinit var viewModel: RequestVm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.result_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        workActivity = activity as WorkActivity
        viewModel = ViewModelProvider(workActivity).get(RequestVm::class.java)
        binding.lifecycleOwner = workActivity
        binding.fragment = this
        binding.vm = viewModel


       // onObserve()
    }

//    private fun onObserve(){
//        viewModel.resultCode.observe(workActivity, Observer {
//            when(it){
//                SUCCESS -> "请求成功".toast()
//                FAIL -> "请求失败".toast()
//                NOT_URL -> "缺少url".toast()
//            }
//        })
//    }
}