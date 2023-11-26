package cl.lisgc.shoplist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.lisgc.shoplist.database.dao.dao
import cl.lisgc.shoplist.database.entity.product

@Database(entities = [product::class], version = 1)
abstract class dataBase:RoomDatabase() {
    abstract fun getProductDao():dao
}