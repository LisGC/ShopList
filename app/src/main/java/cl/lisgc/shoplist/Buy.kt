package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import cl.lisgc.shoplist.adapter.BuyAdapterList
import cl.lisgc.shoplist.adapter.BuyDetail
import cl.lisgc.shoplist.entity.Product

class Buy : AppCompatActivity() {
    private lateinit var listBuy: ListView
    private  var listOption: Boolean = true
    private var detailOption: Boolean = false
    private lateinit var products: MutableList<Product>
    private lateinit var productAdapter: BuyAdapterList

    companion object{
        const val REQUEST_REGISTER = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        listBuy = findViewById(R.id.buyList)

        products = mutableListOf(
            Product("Pan de Molde", "bimbo", 6, 1200),
            Product("Fanta", "1.5lt", 1, 1500),
            Product("Jamon Acaramelado", "Pavo", 6, 500),
            Product("Queso", "Gauda", 6, 250)

        )

        productAdapter = BuyAdapterList(this, R.layout.buy_item, products)

        listBuy.adapter = productAdapter

        listBuy.setOnItemClickListener{ _, _, posicion, _ ->
            val selectedProduct = products[posicion]
            listOption = !listOption
            if (detailOption){
                showProductDetail(selectedProduct)
            }
            else{
                val intent = Intent(this, ProductDetails::class.java)
                intent.putExtra("Product", selectedProduct)
                startActivity(intent)
            }
        }
    }

    fun createProduct(view: View){
        val intent = Intent(this, AddBuyItem::class.java)
        startActivityForResult(intent, REQUEST_REGISTER)
    }

    private fun showProductDetail(product: Product){
        val dialog = BuyDetail(this, product)
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newProduct = data?.getParcelableExtra<Product>("new")
            if (newProduct != null) {
                products.add(newProduct)
                if (listOption) {

                }
                productAdapter.notifyDataSetChanged()
            }
        }
    }
}