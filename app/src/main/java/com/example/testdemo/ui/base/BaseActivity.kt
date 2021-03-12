package com.example.testdemo.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB:ViewBinding,VM:BaseViewModel>:AppCompatActivity(){
    lateinit var mContext: FragmentActivity
    lateinit var vm: VM
    lateinit var v: VB
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz1 = type.actualTypeArguments[1] as Class<VM>
        vm = ViewModelProvider(this).get(clazz1)
        val clazz2 = type.actualTypeArguments[0] as Class<VB>
        val method = clazz2.getMethod("inflate", LayoutInflater::class.java)
        v = method.invoke(null, layoutInflater) as VB
        setContentView(v.root)
        mContext = this
        initView()
        initData()
        initListener()
        initVM()
    }
    abstract fun initVM()
    abstract fun initListener()
    abstract fun initData()
    abstract fun initView()

}