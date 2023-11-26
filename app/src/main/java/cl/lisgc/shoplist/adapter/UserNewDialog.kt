package cl.lisgc.shoplist.adapter

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import cl.lisgc.shoplist.MainActivity
import cl.lisgc.shoplist.R
import cl.lisgc.shoplist.database.dataBase
import cl.lisgc.shoplist.database.entity.product

class UserNewDialog (
    context: Context,
    idItem: Int,
    act: MainActivity
): Dialog(context) {

    private lateinit var name: EditText
    private lateinit var category: EditText
    private lateinit var quantity: EditText
    private lateinit var price: EditText
    private lateinit var db: dataBase
    private var id: Int = idItem
    private var act: MainActivity = act

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.action_user_new_dialog)

        name = findViewById(R.id.textInputName)
        category = findViewById(R.id.textInputCategory)
        quantity = findViewById(R.id.textInputQuantity)
        price = findViewById(R.id.textInputPrice)

        db = Room.databaseBuilder(
            context,
            dataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        val buttonAddAndGoBack: Button = findViewById(R.id.buttonAddAndGoBack)
        buttonAddAndGoBack.setOnClickListener {

            db.getProductDao().insertAll(
                product(
                    id,
                    name.text.toString(),
                    category.text.toString(),
                    quantity.text.toString().toInt(),
                    price.text.toString().toInt()
                )
            )
            act.refreshFromDatabase()
            dismiss()
        }

    }
}