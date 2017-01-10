package com.xsota.memo.views

import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.xsota.memo.R
import com.xsota.memo.databinding.ActivityEditMemoBinding
import com.xsota.memo.models.Memo
import com.xsota.memo.viewmodels.EditMemoViewModel
import java.util.*

class ActivityEditMemo : AppCompatActivity() {
    private val id : String by lazy {
        intent.getStringExtra("id") ?: UUID.randomUUID().toString()
    }

    private val mBinding: ActivityEditMemoBinding by lazy {
        DataBindingUtil.setContentView<ActivityEditMemoBinding>(this, R.layout.activity_edit_memo)
    }

    private val memo by lazy {
        Memo.load(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.viewModel = EditMemoViewModel(this)
        mBinding.memo = memo

        setSupportActionBar(mBinding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit_memo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> clickSaveMenu()
            R.id.action_delete -> clickDeleteMenu()
        }

        return super.onOptionsItemSelected(item)
    }

    fun clickSaveMenu(){
        memo.id = id
        memo.title = mBinding.includedContent.titleEdittext.getText().toString()
        memo.body = mBinding.includedContent.bodyEdittext.getText().toString()

        memo.save()
        finish()
    }

    fun clickDeleteMenu(){
        AlertDialog.Builder(this)
        .setTitle(getString(R.string.dialog_delete))
        .setNegativeButton(getString(R.string.action_delete), DialogInterface.OnClickListener { dialogInterface, i ->
            Memo.delete(id)

            finish()
        })
        .setPositiveButton(getString(R.string.action_cancel), null)
        .show()
    }
}
