package com.xsota.memo

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.xsota.memo.databinding.ActivityEditMemoBinding

class ActivityEditMemo : AppCompatActivity() {

    private var mBinding: ActivityEditMemoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityEditMemoBinding>(this, R.layout.activity_edit_memo)

        setSupportActionBar(mBinding!!.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit_memo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            // TODO save memo
        }

        return super.onOptionsItemSelected(item)
    }
}
