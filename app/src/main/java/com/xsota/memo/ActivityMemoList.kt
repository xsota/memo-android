package com.xsota.memo

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xsota.memo.databinding.ActivityMainBinding
import com.xsota.memo.models.Memo

class ActivityMemoList : AppCompatActivity() {

    private val mBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(mBinding.toolbar)
        mBinding.includedContent.listview.emptyView = mBinding.includedContent.emptyView

        mBinding.fab.setOnClickListener {
            val i = Intent(this@ActivityMemoList, ActivityEditMemo::class.java)
            startActivity(i)
        }


        val adapter = MemoAdapter(Memo.getMemoList(), layoutInflater)
        mBinding.includedContent.listview.setAdapter(adapter)
    }

}
