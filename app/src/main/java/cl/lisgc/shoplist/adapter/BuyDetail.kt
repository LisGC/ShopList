package cl.lisgc.shoplist.adapter;

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import cl.lisgc.shoplist.R
import cl.lisgc.shoplist.entity.Product

class BuyDetail(
        context: Context,
        private val product: Product
) : Dialog(context) {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_product_details)

        val textName = findViewById<TextView>(R.id.textViewName)
        val textDetail = findViewById<TextView>(R.id.textViewDetail)
        val textQuantity = findViewById<TextView>(R.id.textViewQuantity)
        val textPrice = findViewById<TextView>(R.id.textViewPrice)

        // Set patient information in TextViews
        textName.text = product.name
        textDetail.text = product.detail
        textQuantity.text = product.quantity.toString()
        textPrice.text = product.price.toString()

    }
}
