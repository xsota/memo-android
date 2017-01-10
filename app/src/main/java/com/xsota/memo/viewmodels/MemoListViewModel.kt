package com.xsota.memo.viewmodels

import android.content.Intent
import android.view.View
import com.xsota.memo.views.ActivityEditMemo

/**
 * Created by sota on 2017/01/10.
 */
class MemoListViewModel{

    fun onClickCreateMemoButton(view: View){
        val i = Intent(view.context, ActivityEditMemo::class.java)
        view.context.startActivity(i)
    }
}

