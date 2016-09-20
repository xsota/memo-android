package com.xsota.memo

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.xsota.memo.databinding.ActivityMainBinding

class ActivityMemoList : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        setSupportActionBar(mBinding!!.toolbar)
        mBinding!!.includedContent.listview.emptyView = mBinding!!.includedContent.emptyView

        mBinding!!.fab.setOnClickListener {
            val i = Intent(this@ActivityMemoList, ActivityEditMemo::class.java)
            startActivity(i)
        }
    }

}
