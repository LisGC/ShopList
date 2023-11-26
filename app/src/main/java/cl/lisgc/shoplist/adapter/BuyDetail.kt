package cl.lisgc.shoplist.adapter;

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import cl.lisgc.shoplist.R
import cl.lisgc.shoplist.database.entity.product

class BuyDetail(
        context: Context,
        private val item: product
): Dialog(context) {

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.activity_product_details)

                val textViewName = findViewById<TextView>(R.id.textViewName)
                val textViewCategory = findViewById<TextView>(R.id.textViewCategory)
                val textViewQuantity = findViewById<TextView>(R.id.textViewQuantity)
                val textViewPrice = findViewById<TextView>(R.id.textViewPrice)
                val buttonGoBack = findViewById<Button>(R.id.buttonGoBack)

                textViewName.text = item.name
                textViewCategory.text = item.category
                textViewQuantity.text = item.quantity.toString()
                textViewPrice.text = item.price.toString()

                buttonGoBack.setOnClickListener {
                        dismiss()
                }
        }
}
