package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import cl.lisgc.shoplist.entity.Product

class AddBuyItem : AppCompatActivity() {
    private lateinit var Name: EditText
    private lateinit var Details: EditText
    private lateinit var Quantity: EditText
    private lateinit var Price: EditText
    private lateinit var AddButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_buy_item)

        Name = findViewById(R.id.editName)
        Details = findViewById(R.id.editDetail)
        Quantity = findViewById(R.id.editQuantity)
        Price = findViewById(R.id.editPrice)
        AddButton = findViewById(R.id.confirmButton)

        AddButton.setOnClickListener{

            val NameEdit = Name.text.toString()
            val DetailsEdit = Details.text.toString()
            val QuantityEdit = Quantity.text.toString().toIntOrNull() ?: 0
            val PriceEdit = Price.text.toString().toIntOrNull() ?: 0

            val product = Product(NameEdit, DetailsEdit, QuantityEdit, PriceEdit)

            val resultIntent = Intent()
            resultIntent.putExtra("new", product)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}