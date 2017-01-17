package com.xsota.memo.viewmodels

import android.content.Intent
import android.view.View
import com.xsota.memo.models.Memo
import com.xsota.memo.views.ActivityEditMemo

/**
 * Created by sota on 2017/01/17.
 */

class MemoListAdapterViewModel {

    fun onClickItem(view: View, memo:Memo){
        val context = view.context
        val intent = Intent(context, ActivityEditMemo::class.java)

        intent.putExtra("id", memo.id)
        context.startActivity(intent)
    }
}
