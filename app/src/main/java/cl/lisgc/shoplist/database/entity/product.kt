package cl.lisgc.shoplist.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_products")
data class product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "nameP")val name: String,
    @ColumnInfo(name = "category")val category: String,
    @ColumnInfo(name = "quantity")val quantity: Int,
    @ColumnInfo(name = "price")val price: Int
){}
