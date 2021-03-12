package com.example.testdemo.ui.main

import android.util.Log
import com.example.testdemo.databinding.ActivityMainBinding
import com.example.testdemo.logic.dao.AppDatabase
import com.example.testdemo.logic.model.Person
import com.example.testdemo.ui.base.BaseActivity
import kotlin.concurrent.thread
import androidx.lifecycle.Observer
class MainActivity:BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun initData() {

    }

    override fun initListener() {
        val personDao=AppDatabase.getDatabase(this).personDao()
        val person1= Person("Tom",1560,52)
        val person2=Person("Jack",5000,63)
        v.addDataBtn.setOnClickListener {
            thread {
                person1.id=personDao.insertPerson(person1)
                person2.id=personDao.insertPerson(person2)
            }
        }
        v.getPersonBtn.setOnClickListener {
            thread {
                for (person in personDao.loadAllPerson()){
                    Log.d("Main",person.name)
                }
            }
        }
    }

    override fun initView() {

    }

    override fun initVM() {

    }
}