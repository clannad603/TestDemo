package com.example.testdemo.logic.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.testdemo.logic.model.Person

@Dao
interface PersonDao {
    @Insert
    fun insertPerson(person: Person):Long

    @Update
    fun updatePerson(newPerson: Person)

    @Query("select*from Person")
    fun loadAllPerson(): List<Person>

    @Query("select*from Person where age> :age")
    fun loadPersonOlderThan(age:Int): LiveData<List<Person>>

    @Delete
    fun deletePerson(person: Person)

    @Query("delete from Person where money>:money")
    fun deletePersonRich(money:Int):Int

}