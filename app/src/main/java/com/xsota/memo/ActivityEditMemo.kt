package com.xsota.memo

import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.xsota.memo.databinding.ActivityEditMemoBinding
import com.xsota.memo.models.Memo
import io.realm.Realm
import java.util.*

class ActivityEditMemo : AppCompatActivity() {
    private val id : String by lazy {
        intent.getStringExtra("id") ?: UUID.randomUUID().toString()
    }

    private val mBinding: ActivityEditMemoBinding by lazy {
        DataBindingUtil.setContentView<ActivityEditMemoBinding>(this, R.layout.activity_edit_memo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        load()

        setSupportActionBar(mBinding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit_memo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> save()
            R.id.action_delete -> showDleteDialog()
        }


        return super.onOptionsItemSelected(item)
    }

    fun save(){
        Realm.getDefaultInstance().use { realm ->

            realm.executeTransaction {
                val memo = Memo()

                memo.id = id
                memo.title = mBinding.includedContent.titleEdittext.getText().toString()
                memo.body = mBinding.includedContent.bodyEdittext.getText().toString()

                realm.copyToRealmOrUpdate(memo)

                finish()
            }
        }
    }

    fun load() {
        Realm.getDefaultInstance().use {  realm ->
            val result = realm.where(Memo::class.java).equalTo("id",id).findFirst()
            result ?: return
            mBinding.includedContent.bodyEdittext.setText(result.body)
            mBinding.includedContent.titleEdittext.setText(result.title)
        }
    }

    fun delete(){
        Realm.getDefaultInstance().use {  realm ->
            val result = realm.where(Memo::class.java).equalTo("id",id).findAll()
            realm.beginTransaction()
            result.deleteAllFromRealm()
            realm.commitTransaction()
            finish()
        }
    }

    fun showDleteDialog(){
        AlertDialog.Builder(this)
        .setTitle(getString(R.string.dialog_delete))
        .setNegativeButton(getString(R.string.action_delete), DialogInterface.OnClickListener { dialogInterface, i ->
            delete()
        })
        .setPositiveButton(getString(R.string.action_cancel), null)
        .show()
    }
}
