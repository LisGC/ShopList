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

        ///Stores

    }


}