package com.xsota.memo.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by sota on 16/09/20.
 */
open class Memo : RealmObject() {

    @PrimaryKey var id: String? = null
    var title: String? = null
    var body: String? = null
}
