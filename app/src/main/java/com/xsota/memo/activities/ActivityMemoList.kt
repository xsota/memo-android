package com.xsota.memo.activities

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xsota.memo.MemoAdapter
import com.xsota.memo.R
import com.xsota.memo.databinding.ActivityMainBinding
import com.xsota.memo.models.Memo

class ActivityMemoList : AppCompatActivity() {

    private val mBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val adapter: MemoAdapter by lazy {
        MemoAdapter(Memo.getMemoList(), layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(mBinding.toolbar)
        mBinding.includedContent.listview.emptyView = mBinding.includedContent.emptyView

        mBinding.fab.setOnClickListener {
            val i = Intent(this@ActivityMemoList, ActivityEditMemo::class.java)
            startActivity(i)
        }

        mBinding.includedContent.listview.setAdapter(adapter)

        mBinding.includedContent.listview.setOnItemClickListener { adapterView, view, i, l ->
            val memo = adapter.getItem(i) as Memo
            val intent = Intent(this, ActivityEditMemo::class.java)
            intent.putExtra("id", memo.id)

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()

    }

}
