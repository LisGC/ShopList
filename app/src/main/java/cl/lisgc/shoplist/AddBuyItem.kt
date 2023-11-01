package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import cl.lisgc.shoplist.entity.Product

class AddBuyItem : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var details: EditText
    private lateinit var quantity: EditText
    private lateinit var price: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_buy_item)

        name = findViewById(R.id.editName)
        details = findViewById(R.id.editDetail)
        quantity = findViewById(R.id.editQuantity)
        price = findViewById(R.id.editPrice)
        addButton = findViewById(R.id.confirmButton)

        addButton.setOnClickListener{

            val nameEdit = name.text.toString()
            val detailsEdit = details.text.toString()
            val quantityEdit = quantity.text.toString().toIntOrNull() ?: 0
            val priceEdit = price.text.toString().toIntOrNull() ?: 0

            val product = Product(nameEdit, detailsEdit, quantityEdit, priceEdit)

            val resultIntent = Intent()
            resultIntent.putExtra("new", product)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}