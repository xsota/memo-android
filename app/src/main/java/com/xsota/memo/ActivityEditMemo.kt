package com.xsota.memo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.xsota.memo.databinding.ActivityEditMemoBinding
import com.xsota.memo.models.Memo
import io.realm.Realm
import java.util.*

class ActivityEditMemo : AppCompatActivity() {
    private val id : String? by lazy {
        intent.getStringExtra("id")
    }

    private val mBinding: ActivityEditMemoBinding by lazy {
        DataBindingUtil.setContentView<ActivityEditMemoBinding>(this, R.layout.activity_edit_memo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (id != null){
            Realm.getDefaultInstance().use {  realm ->
                val result = realm.where(Memo::class.java).equalTo("id",id).findFirst()

                mBinding.includedContent.bodyEdittext.setText(result.body)
                mBinding.includedContent.titleEdittext.setText(result.title)
            }
        }


        setSupportActionBar(mBinding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit_memo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            Realm.getDefaultInstance().use { realm ->

                realm.executeTransaction {
                    val memo = Memo()//realm.createObject(Memo::class.java)

                    if( id == null){
                        memo.id = UUID.randomUUID().toString()
                    } else {
                        memo.id = id
                    }

                    mBinding.toolbar
                    memo.title = mBinding.includedContent.titleEdittext.getText().toString()
                    memo.body = mBinding.includedContent.bodyEdittext.getText().toString()

                    realm.copyToRealmOrUpdate(memo)

                    finish()
                }
            }

        }

        return super.onOptionsItemSelected(item)
    }
}
