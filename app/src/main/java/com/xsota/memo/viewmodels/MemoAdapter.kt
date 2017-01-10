package com.xsota.memo.viewmodels

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.xsota.memo.R
import com.xsota.memo.databinding.ViewMemoItemBinding
import com.xsota.memo.models.Memo
import io.realm.RealmResults

/**
 * Created by sota on 2016/09/22.
 */

class MemoAdapter(val memoList: RealmResults<Memo>, val inflater: LayoutInflater) : BaseAdapter() {

    override fun getCount(): Int {
        return memoList.size
    }

    override fun getItem(i: Int): Any? {
        return memoList.get(i)
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View? {
        val binding = DataBindingUtil.inflate<ViewMemoItemBinding>(inflater, R.layout.view_memo_item, viewGroup, false)
        binding.memo = memoList.get(i)

        return binding.root
    }
}
