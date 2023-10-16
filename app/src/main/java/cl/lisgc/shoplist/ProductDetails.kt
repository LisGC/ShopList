package cl.lisgc.shoplist

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import cl.lisgc.shoplist.entity.Product

class ProductDetails : AppCompatActivity() {
    private lateinit var textName: TextView
    private lateinit var textDetails: TextView
    private lateinit var textQuantity: TextView
    private lateinit var textPrice: TextView

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        textName = findViewById(R.id.textViewName)
        textDetails = findViewById(R.id.textViewDetail)
        textQuantity = findViewById(R.id.textViewQuantity)
        textPrice = findViewById(R.id.textViewPrice)

        if(Build.VERSION.SDK_INT >= 33){
            product = intent.getParcelableExtra("Producto", Product::class.java) ?: Product("", "", 0, 0)
        }else{
            product = intent.getParcelableExtra("Producto") ?: Product("", "", 0, 0)
        }

        if (product != null) {
            textName.text = product.name
            textDetails.text = product.detail
            textQuantity.setText(product.quantity.toString())
            textPrice.setText(product.price.toString())
        }
        textName.setOnClickListener(){
            Toast.makeText(this, product.name, Toast.LENGTH_LONG).show()
        }
    }
}