package com.example.testdemo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseViewFragment<VB:ViewBinding>:Fragment() {
    lateinit var mContext: FragmentActivity
    var contentView: View? = null
    lateinit var v: VB
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz2 = type.actualTypeArguments[0] as Class<VB>
        val method = clazz2.getMethod("inflate", LayoutInflater::class.java)
        v = method.invoke(null, layoutInflater) as VB
        mContext = context as AppCompatActivity
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if (null == contentView) {
            contentView = v.root
            initView()
            initClick()
            initData()
        }
        return contentView
    }

    abstract fun initView()

    abstract fun initClick()

    abstract fun initData()

    override fun onDestroyView() {
        super.onDestroyView()
        contentView = null
    }

}