package com.sonne.shoppinglist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopItemDBModel::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun shopListDao(): ShopListDAO

    companion object {
        private var INSTANCE: AppDB? = null
        private val LOCK = Any()
        private const val DB_NAME = "shop_item.db"

        fun getInstance(application: Application): AppDB {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDB::class.java,
                    DB_NAME
                )
//                  сообщаем, что можно запускать в главном потоке, пока нет корутин
//                  (только для теста):
//                  .allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}