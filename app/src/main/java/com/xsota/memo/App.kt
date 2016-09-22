package com.xsota.memo

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by sota on 2016/09/21.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()


        // Initialize Realm
        val realmConfig = RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(realmConfig)
    }
}
