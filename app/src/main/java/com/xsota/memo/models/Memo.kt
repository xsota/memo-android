package com.xsota.memo.models

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey

/**
 * Created by sota on 16/09/20.
 */
open class Memo : RealmObject() {

    @PrimaryKey var id: String? = null
    var title: String? = null
    var body: String? = null
    var latitude: Double? = null
    var longitude: Double? = null

    companion object {
        fun getMemoList(): RealmResults<Memo> {
            val realm = Realm.getDefaultInstance()
            val result = realm.where(Memo::class.java).findAll()

            return result
        }
    }
}
