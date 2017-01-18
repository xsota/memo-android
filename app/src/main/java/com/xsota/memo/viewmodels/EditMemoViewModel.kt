package com.xsota.memo.viewmodels

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.xsota.memo.R
import com.xsota.memo.models.Memo
import io.realm.Realm

/**
 * Created by sota on 2017/01/10.
 */
class EditMemoViewModel(activity:Activity, memoId:String){
    private val mActivity = activity
    private val mMemoId = memoId

    fun onOptionItemSelected(item: MenuItem): Boolean{
        when (item.itemId) {
            R.id.action_save -> clickSaveMenu()
            R.id.action_delete -> clickDeleteMenu()
        }
        return true
    }

    fun clickSaveMenu(){
        Realm.getDefaultInstance().commitTransaction()
        mActivity.finish()
    }

    fun clickDeleteMenu(){
        AlertDialog.Builder(mActivity)
                .setTitle(mActivity.getString(R.string.dialog_delete))
                .setNegativeButton(mActivity.getString(R.string.action_delete), { dialogInterface, i ->
                    Memo.delete(mMemoId)
                    mActivity.finish()
                })
                .setPositiveButton(mActivity.getString(R.string.action_cancel), null)
                .show()
    }
}

