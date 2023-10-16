package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import cl.lisgc.shoplist.adapter.StoreAdapterList
import cl.lisgc.shoplist.entity.StoreT

class Stores : AppCompatActivity() {

    private lateinit var storesList:ListView
    private  var listOption: Boolean = true
    private lateinit var stores: MutableList<StoreT>
    private lateinit var storeAdapter: StoreAdapterList

    companion object{
        const val REQUEST_REGISTER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)

        storesList = findViewById(R.id.storesList)

        stores = mutableListOf(
            StoreT("aCuenta", "Av. 1 Oriente 323", "Lunes a sábado 8:00 – 21:30"),
            StoreT("Jumbo", "El Arenal 441, Talca", "Lunes a sábado 8:00 – 21:00"),
            StoreT("Tottus", "Calle Cuatro Norte 1530, Talca", "Lunes a Domingo 08:30 a 21:30 hrs")
        )

        storeAdapter = StoreAdapterList(this, R.layout.store_item, stores)

        storesList.adapter = storeAdapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newStore = data?.getParcelableExtra<StoreT>("new")
            if (newStore != null) {
                stores.add(newStore)

                if (listOption) {

                }
                storeAdapter.notifyDataSetChanged()
            }
        }
    }
}