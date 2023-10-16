package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import cl.lisgc.shoplist.adapter.InventoryAdapterList
import cl.lisgc.shoplist.entity.Product

class Inventory : AppCompatActivity() {

    private lateinit var listInventory: ListView
    private  var listOption: Boolean = true
    private lateinit var products: MutableList<Product>
    private lateinit var productsAdapter: InventoryAdapterList

    companion object{
        const val REQUEST_REGISTER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        listInventory = findViewById(R.id.inventoryList)

        products = mutableListOf(
            Product("Cheetos", "evercrysp", 6, 0),
            Product("Monster", "Mango Loco", 1, 0)

        )

        productsAdapter = InventoryAdapterList(this, R.layout.inventory_item, products)

        listInventory.adapter = productsAdapter

    }

    fun createProduct(view: View){
        val intent = Intent(this, AddInventoryItem::class.java)
        startActivityForResult(intent, REQUEST_REGISTER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            val nuevoProducto = data?.getParcelableExtra<Product>("new")
            if (nuevoProducto != null) {
                products.add(nuevoProducto)
                if (listOption) {

                }
                productsAdapter.notifyDataSetChanged()
            }
        }
    }
}