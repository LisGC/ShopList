package cl.lisgc.shoplist.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cl.lisgc.shoplist.database.entity.product

interface dao {
    @Query("SELECT * FROM table_products")
    fun getAllProducts():List<product>

    @Query("SELECT * FROM table_products ORDER BY nameP")
    fun orderProducts():List<product>

    @Query("SELECT * FROM table_products ORDER BY category")
    fun orderCategoryProducts():List<product>

    @Query("SELECT * FROM table_products ORDER BY quantity")
    fun orderQuantityProducts():List<product>

    @Query("SELECT * FROM table_products WHERE nameP LIKE:searchQuery")
    fun getProductName(searchQuery: String):List<product>

    @Insert
    fun insertAll(vararg products: product)

    @Delete
    fun delete(product: product)
}