package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ///Inventory
        val inventoryNavegation = findViewById<Button>(R.id.button_inventory)

        inventoryNavegation.setOnClickListener {
            val intentAbout = Intent(this,Inventory::class.java)
            startActivity(intentAbout)
        }

        ///Buy
        val buyNavegation = findViewById<Button>(R.id.button_buy)

        buyNavegation.setOnClickListener {
            val intentAbout = Intent(this,Buy::class.java)
            startActivity(intentAbout)
        }

        ///Stores
        val storesNavegation = findViewById<Button>(R.id.button_stores)

        storesNavegation.setOnClickListener {
            val intentAbout = Intent(this,Stores::class.java)
            startActivity(intentAbout)
        }
    }


}