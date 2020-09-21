package com.shine56.blue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shine56.blue.R
import com.shine56.blue.base.MyApplication.Companion.SUCCESS
import com.shine56.blue.ui.request.RequestVm
import com.shine56.blue.util.logD
import com.shine56.blue.util.toast

class WorkActivity : AppCompatActivity() {
    private lateinit var vm: RequestVm
    private var exit = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
        val id = intent.extras?.getInt("requestId")


        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_request, R.id.navigation_result, R.id.navigation_arrange))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        vm = ViewModelProvider(this).get(RequestVm::class.java)
        id?.let { vm.id = it }
        vm.refreshBean()

        onObserve()
    }

    override fun onBackPressed() {
        if(exit)    super.onBackPressed()
        else saveRequest()
    }

    private fun saveRequest(){
        vm.save()
    }

    private fun onObserve(){
        vm.saveCode.observe(this, Observer {
            if(it == SUCCESS){
                "保存成功".toast()
                exit = true
            } else{
                "保存失败".toast()
                exit = true
            }
            onBackPressed()
        })
    }

}