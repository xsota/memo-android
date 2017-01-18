package com.xsota.memo.views

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.xsota.memo.R
import com.xsota.memo.databinding.ActivityEditMemoBinding
import com.xsota.memo.models.Memo
import com.xsota.memo.viewmodels.EditMemoViewModel
import io.realm.Realm
import java.util.*

class ActivityEditMemo : AppCompatActivity() {

    private val mBinding: ActivityEditMemoBinding by lazy {
        DataBindingUtil.setContentView<ActivityEditMemoBinding>(this, R.layout.activity_edit_memo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getStringExtra("id") ?: UUID.randomUUID().toString()

        Realm.getDefaultInstance().beginTransaction()

        mBinding.viewModel = EditMemoViewModel(this, id)
        mBinding.memo =  Memo.loadOrCreateIfNeeded(id)

        setSupportActionBar(mBinding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit_memo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return mBinding.viewModel.onOptionItemSelected(item)
    }

    override fun onDestroy() {
        if(Realm.getDefaultInstance().isInTransaction){
            Realm.getDefaultInstance().cancelTransaction()
        }

        super.onDestroy()
    }

}
