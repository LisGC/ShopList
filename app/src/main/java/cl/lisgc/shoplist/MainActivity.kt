package cl.lisgc.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        ///Buy
        val buyNavegation = findViewById<Button>(R.id.button_buy)

        buyNavegation.setOnClickListener {
            val intentAbout = Intent(this, Buy::class.java)
            startActivity(intentAbout)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_stores -> {
                val intentStores = Intent(this, Stores::class.java)
                startActivity(intentStores)
                return true

            }

            R.id.action_inventory -> {
                val intentInventory = Intent(this, Inventory::class.java)
                startActivity(intentInventory)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }


    }
}