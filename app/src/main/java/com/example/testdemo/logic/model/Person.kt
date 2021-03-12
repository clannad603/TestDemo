package com.example.testdemo.logic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(var name:String,var money:Int,var age:Int){
    @PrimaryKey(autoGenerate = true)
    var id:Long=0
}
