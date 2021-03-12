package com.example.testdemo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseDataActivity<VD:ViewDataBinding,VM:BaseViewModel> :AppCompatActivity(){
    lateinit var mContext: FragmentActivity
    lateinit var vm: VM
    lateinit var v: VD
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz1 = type.actualTypeArguments[1] as Class<VM>
        vm = ViewModelProvider(this).get(clazz1)
        val v:VD=DataBindingUtil.setContentView(this,getLayout())
        v.lifecycleOwner=this
        setContentView(v.root)
        mContext = this
        initView()
        initData()
        initVM()
        initListener()
    }

    abstract fun getLayout(): Int
    abstract fun initVM()
    abstract fun initListener()
    abstract fun initData()
    abstract fun initView()

}