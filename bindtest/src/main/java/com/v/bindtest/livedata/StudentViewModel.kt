package com.v.bindtest.livedata

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Author:v
 * Time:2021/2/22
 */
class StudentViewModel : ViewModel() {
    private val TAG = "LiveRecycler"
    val data = MutableLiveData<MutableList<StudentBean>>()


    fun loadData() {
        data.value = StudentModel.getDatas()
    }

    fun updateData(list: MutableList<StudentBean>) {
//        StudentModel.updateData() maybe use coroutine
        data.value = list
    }

    fun removeData(student: StudentBean) {
        StudentModel.removeData()
        data.value!!.let {
            for (s in it) {
                if (s.id == student.id) {
                    it.remove(s)
                    break
                }
            }
            data.value = it
        }
    }

    fun addData(student: StudentBean) {
        data.value!!.let {
            it.add(student)
            data.value = it
        }
        Log.d(TAG, "add data")
//        data.value!!.add(student) this can't do
    }

    fun clearData() {
        data.value!!.let {
            it.clear()
            data.value = it
        }
    }
}


class LiveStudentViewModel : ViewModel() {
    val data = ObservableArrayList<StudentBean>()

    fun loadData() {
        data.addAll(StudentModel.getDatas())
    }

    fun loadDataOnStop() {
        val l = StudentModel.loadDataBackground()
        Log.d("liveViewModel", "load after pause ${l.size}")
        data.addAll(l)
    }

    fun removeData(student: StudentBean) {
        StudentModel.removeData()
        for (s in data) {
            if (s.id == student.id) {
                data.remove(s)
                break
            }
        }
    }

    fun addData(student: StudentBean) {
        Log.d("liveViewModel", "addData,before size=${data.size}")
        data.add(student)
        Log.d("liveViewModel", "addData,now size=${data.size}")
    }

    fun clearData() {
        data.clear()
    }

}


object StudentModel {
    fun getDatas() = arrayListOf<StudentBean>(
        StudentBean(1, "Bob", 25, null),
        StudentBean(2, "Jim", 23, null),
        StudentBean(3, "Park", 45, null),
        StudentBean(12, "Tom", 15, null),
        StudentBean(7, "Lee", 24, null),
        StudentBean(13, "John", 27, null),
        StudentBean(15, "Jack", 20, null),
    )

    fun loadDataBackground(): ArrayList<StudentBean> {
        Thread.sleep(3000L)
        return getDatas()
    }

    fun addData(): Boolean {
        //something here ,maybe net,database,file...
        return true
    }


    fun removeData(): Boolean {
        //something here ,maybe net,database,file...
        return true
    }

    suspend fun updateData(): Boolean {
        //something here ,maybe net,database,file...
        return true
    }

}