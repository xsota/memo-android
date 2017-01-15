package com.xsota.memo.models

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey

/**
 * Created by sota on 16/09/20.
 */
open class Memo() : RealmObject() {

    @PrimaryKey var id: String? = null
    var title: String? = null
    var body: String? = null
    var latitude: Double? = null
    var longitude: Double? = null

    constructor(id: String) : this() {
        this.id = id
    }

    fun save(){
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                realm.copyToRealmOrUpdate(this)
            }
        }
    }

    companion object {
        fun getMemoList(): RealmResults<Memo> {
            val realm = Realm.getDefaultInstance()
            val result = realm.where(Memo::class.java).findAll()

            return result
        }

        fun loadOrCreateIfNeeded(id:String) :Memo {
            Realm.getDefaultInstance().use { realm ->
                val result = realm.where(Memo::class.java).equalTo("id",id).findFirst()
                return result ?: realm.createObject(Memo::class.java, id)
            }
        }

        fun delete(id:String){
            Realm.getDefaultInstance().use { realm ->
                val result = realm.where(Memo::class.java).equalTo("id",id).findAll()

                if (realm.isInTransaction){
                    result.deleteAllFromRealm()
                    realm.commitTransaction()
                    return
                }

                realm.executeTransaction {
                    result.deleteAllFromRealm()
                }
            }
        }

    }
}
