package com.xsota.memo.views

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xsota.memo.R
import com.xsota.memo.databinding.ActivityMemoListBinding
import com.xsota.memo.models.Memo
import com.xsota.memo.viewmodels.MemoListViewModel
import com.xsota.memo.views.adapter.MemoListAdapter

class ActivityMemoList : AppCompatActivity() {

    private val mBinding: ActivityMemoListBinding by lazy {
        DataBindingUtil.setContentView<ActivityMemoListBinding>(this, R.layout.activity_memo_list)
    }

    private val mListAdapter: MemoListAdapter by lazy {
        MemoListAdapter(Memo.getMemoList(), layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(mBinding.toolbar)

        mBinding.viewModel = MemoListViewModel()
        mBinding.includedContent.listview.emptyView = mBinding.includedContent.emptyView
        mBinding.includedContent.listview.adapter = mListAdapter

        mBinding.includedContent.listview.setOnItemClickListener { adapterView, view, i, l ->
            val memo = mListAdapter.getItem(i) as Memo
            val intent = Intent(this, ActivityEditMemo::class.java)
            intent.putExtra("id", memo.id)

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        mListAdapter.notifyDataSetChanged()

    }

}
