package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import cl.lisgc.shoplist.database.entity.product

class EditProduct : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextCategory: EditText
    private lateinit var editTextQuantity: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var buttonSaveB: Button

    private lateinit var item: product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        editTextName = findViewById(R.id.textInputName)
        editTextCategory = findViewById(R.id.textInputCategory)
        editTextQuantity = findViewById(R.id.textInputQuantity)
        editTextPrice = findViewById(R.id.textInputPrice)
        buttonSaveB = findViewById(R.id.buttonSave)

        item = intent.getParcelableExtra("item")!!

        editTextName.setText(item.name)
        editTextCategory.setText(item.category)
        editTextQuantity.setText(item.quantity.toString())
        editTextPrice.setText(item.price.toString())

        buttonSaveB.setOnClickListener {
            // Retrieve and update user input
            val updateName = editTextName.text.toString()
            val updatedCategory = editTextCategory.text.toString()
            val updatedQuantity = editTextQuantity.text.toString().toIntOrNull() ?: 0
            val updatedPrice = editTextPrice.text.toString().toIntOrNull() ?: 0 // Default to 0 if parsing fails

            // Update the patient object with the edited information
            item = item.copy(
                name = updateName,
                category = updatedCategory,
                quantity = updatedQuantity,
                price = updatedPrice
            )

            // Pass the updated patient object back to the previous activity
            val resultIntent = Intent()
            resultIntent.putExtra("position",intent.getIntExtra("position",0))
            setResult(RESULT_OK, resultIntent)
            finish() // Close the editing activity and return to the previous activity
        }
    }
}