package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import cl.lisgc.shoplist.adapter.BuyAdapterList
import cl.lisgc.shoplist.adapter.BuyDetail
import cl.lisgc.shoplist.adapter.UserNewDialog
import cl.lisgc.shoplist.database.dataBase
import cl.lisgc.shoplist.database.entity.product

class MainActivity : AppCompatActivity() {

    private lateinit var listBuy: ListView
    private lateinit  var db: dataBase
    private lateinit var products: MutableList<product>
    private lateinit var productAdapter: ArrayAdapter<String>

    private lateinit var search: EditText
    private lateinit var buttonSearch: Button
    private lateinit var changeOr: TextView

    private var order: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        search = findViewById(R.id.searchBar)
        buttonSearch = findViewById(R.id.button_search)
        listBuy = findViewById(R.id.buyList)
        changeOr = findViewById(R.id.show_order)

        db = Room.databaseBuilder(
            applicationContext, dataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()


        products = mutableListOf(
           product(0,"Monster","Bebida",1, 1700)

        )

        val list: List<product> = db.getProductDao().orderProducts()
        products = list.toMutableList()

        productAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products.map {it.name})
        Toast.makeText(this, "Hi: "+ products.size.toString(), Toast.LENGTH_LONG).show()

        listBuy.adapter = productAdapter

        list.size

        registerForContextMenu(listBuy)

        buttonSearch.setOnClickListener {
            val list: List<product> = db.getProductDao().getProductName(search.text.toString())
            products = list.toMutableList()
            listBuy.invalidate()

            productAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products.map{it.name})
            listBuy.adapter = productAdapter

            if(search.text.toString() == ""){

                changeOr.setText(R.string.orderName)
                order = 1
                val list: List<product> = db.getProductDao().orderProducts()

                products = list.toMutableList()

                listBuy.invalidate()
                productAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products.map{it.name})
                listBuy.adapter = productAdapter
            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        menuInflater.inflate(R.menu.floating_menu, menu)
        return true
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_order -> {
                if(changeOr.text == "Nombre A-Z" || changeOr.text == "Name A-Z"){
                    changeOr.setText(R.string.orderQuantity)
                    order = 3
                    val list: List<product> = db.getProductDao().orderQuantityProducts()

                    products = list.toMutableList()

                    listBuy.invalidate()
                    productAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products.map{it.name})
                    listBuy.adapter = productAdapter

                }
                else if(changeOr.text == "Cantidad" || changeOr.text == "Quantity"){
                    changeOr.setText(R.string.orderCategory)
                    order = 2
                    val list: List<product> = db.getProductDao().orderCategoryProducts()

                    products = list.toMutableList()

                    listBuy.invalidate()
                    productAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products.map{it.name})
                    listBuy.adapter = productAdapter

                }
                else{
                    changeOr.setText(R.string.orderName)
                    order = 1
                    val list: List<product> = db.getProductDao().orderProducts()

                    products = list.toMutableList()

                    listBuy.invalidate()
                    productAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products.map{it.name})
                    listBuy.adapter = productAdapter
                }
                return true
            }
            R.id.action_add -> {
                val list: List<product> = db.getProductDao().orderProducts()
                val dialog = UserNewDialog(this,list.size+1,this)
                dialog.show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }


    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_show -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                showItemDetailDialog(products.get(position))
                true
            }

            R.id.action_edit -> {
                val intent = Intent(this, EditProduct::class.java)
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                true
            }
            R.id.action_delete -> {
                // Handle the "Delete" option
                // Show the confirmation dialog when "Delete" is selected
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                showDeleteConfirmationDialog(position)
                true
            }
            // Add cases for other options as needed
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }
    private fun showDeleteConfirmationDialog(itemPosition: Int) {
        val builder = AlertDialog.Builder(this)

        builder.setMessage(R.string.delete_message)
        builder.setPositiveButton(R.string.delete) { dialog, _ ->
            // Handle the delete action here
            deleteItem(itemPosition)
        }
        builder.setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun deleteItem(itemPosition: Int) {

        deleteFromDatabase(products[itemPosition])
        refreshFromDatabase()
    }

    fun deleteFromDatabase(user : product) {
        db.getProductDao().delete(user)
    }

    private fun showItemDetailDialog(item: product) {
        val dialog = BuyDetail(this, item)
        dialog.show()
    }
    fun refreshFromDatabase() {
        val list: List<product>
        if (order == 1) {
            list = db.getProductDao().orderProducts()
            Toast.makeText(this, "Hi: " + list.size.toString(), Toast.LENGTH_LONG).show()

            products = list.toMutableList()
            Toast.makeText(this, "Hi: " + products.size.toString(), Toast.LENGTH_LONG).show()

            listBuy.invalidate()
            productAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,products.map { it.name })
            listBuy.adapter = productAdapter

        }
        else if (order == 2) {
            list = db.getProductDao().orderCategoryProducts()
            Toast.makeText(this, "Hi: " + list.size.toString(), Toast.LENGTH_LONG).show()

            products = list.toMutableList()
            Toast.makeText(this, "Hi: " + products.size.toString(), Toast.LENGTH_LONG).show()

            listBuy.invalidate()
            productAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,products.map { it.name })
            listBuy.adapter = productAdapter

        }
        else if (order == 3) {
            list = db.getProductDao().orderQuantityProducts()
            Toast.makeText(this, "Hi: " + list.size.toString(), Toast.LENGTH_LONG).show()

            products = list.toMutableList()
            Toast.makeText(this, "Hi: " + products.size.toString(), Toast.LENGTH_LONG).show()

            listBuy.invalidate()
            productAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,products.map { it.name })
            listBuy.adapter = productAdapter
        }

    }



}