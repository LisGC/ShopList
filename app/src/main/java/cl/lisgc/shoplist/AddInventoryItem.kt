package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import cl.lisgc.shoplist.entity.Product

class AddInventoryItem : AppCompatActivity() {
    private lateinit var Name: EditText
    private lateinit var Detail: EditText
    private lateinit var Quantity: EditText
    private lateinit var AddButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inventory_item)

        Name = findViewById(R.id.editName)
        Detail = findViewById(R.id.editDetail)
        Quantity = findViewById(R.id.editQuantity)
        AddButton = findViewById(R.id.confirmButton)

        AddButton.setOnClickListener {

            val name = Name.text.toString()
            val detail = Detail.text.toString()
            val quantity = Quantity.text.toString().toIntOrNull() ?: 0

            val product = Product(name, detail, quantity, 0)

            val resultIntent = Intent()
            resultIntent.putExtra("new", product)
            setResult(AppCompatActivity.RESULT_OK, resultIntent)
            finish()
        }
    }
}