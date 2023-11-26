package cl.lisgc.shoplist.adapter

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import cl.lisgc.shoplist.MainActivity
import cl.lisgc.shoplist.database.dataBase
import cl.lisgc.shoplist.database.entity.product

class UserNewDialog (
    context: Context,
    idItem: Int,
    act: MainActivity
): Dialog(context){

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
        setContentView(R.layout.dialog_user_new)

        name = findViewById(R.id.textInputNombre)
        category = findViewById(R.id.textInputTipo)
        quantity = findViewById(R.id.textInputCantidad)
        price = findViewById(R.id.textInputPrecio)

        db = Room.databaseBuilder(
            context,
            dataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        val buttonAddAndGoBack: Button = findViewById(R.id.buttonAddAndGoBack)
        buttonAddAndGoBack.setOnClickListener{

            db.getProductDao().insertAll(
                product(id, name.text.toString(), category.text.toString(), quantity.text.toString().toInt(), price.text.toString().toInt())
            )
            act.refreshFromDatabase()
            dismiss()
        }

    }