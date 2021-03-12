package com.example.testdemo.logic.repository

import com.example.testdemo.TestDemoApplication.Companion.context
import com.example.testdemo.logic.dao.AppDatabase

abstract class RoomRepository {
   val roomRepository by lazy {
    AppDatabase.getDatabase(context)
  }
}