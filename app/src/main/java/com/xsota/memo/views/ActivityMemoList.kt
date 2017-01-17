package com.xsota.memo.views

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
    }

    override fun onResume() {
        super.onResume()
        mListAdapter.notifyDataSetChanged()

    }

}
