package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import cl.lisgc.shoplist.entity.Product

class AddInventoryItem : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var detail: EditText
    private lateinit var quantity: EditText
    private lateinit var addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inventory_item)

        name = findViewById(R.id.editName)
        detail = findViewById(R.id.editDetail)
        quantity = findViewById(R.id.editQuantity)
        addButton = findViewById(R.id.confirmButton)

        addButton.setOnClickListener {

            val name = name.text.toString()
            val detail = detail.text.toString()
            val quantity = quantity.text.toString().toIntOrNull() ?: 0

            val product = Product(name, detail, quantity, 0)

            val resultIntent = Intent()
            resultIntent.putExtra("new", product)
            setResult(AppCompatActivity.RESULT_OK, resultIntent)
            finish()
        }
    }
}