package com.shine56.blue.ui.request

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
import com.shine56.blue.base.BaseFragment
import com.shine56.blue.base.MyApplication
import com.shine56.blue.databinding.RequestFragmentBinding
import com.shine56.blue.ui.WorkActivity
import com.shine56.blue.util.logD
import com.shine56.blue.util.toast

class RequestFragment : BaseFragment() {

    private lateinit var binding : RequestFragmentBinding
    private lateinit var workActivity: WorkActivity

    companion object {
        fun newInstance() = RequestFragment()
    }

    private lateinit var viewModel: RequestVm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.request_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        workActivity = activity as WorkActivity
        viewModel = ViewModelProvider(workActivity).get(RequestVm::class.java)
        binding.lifecycleOwner = workActivity
        binding.fragment = this
        binding.vm = viewModel

        onObserve()
    }
    fun onClick(v: View){
        when(v.id){
            R.id.start_request -> viewModel.request()
        }
    }
    private fun onObserve(){
        viewModel.resultCode.observe(workActivity, Observer {
            "上来看".logD()
            when(it){
                MyApplication.SUCCESS -> "请求成功".toast()
                MyApplication.FAIL -> "请求失败".toast()
                MyApplication.NOT_URL -> "缺少url".toast()
            }
        })
    }

}