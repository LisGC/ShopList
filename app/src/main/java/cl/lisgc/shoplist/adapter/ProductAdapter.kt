package cl.lisgc.shoplist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cl.lisgc.shoplist.R
import cl.lisgc.shoplist.entity.Product

class BuyAdapterList(
    context: Context,
    resources: Int,
    product: List<Product>
) : ArrayAdapter<Product>(context, resources, product){

    override fun getView(posicion: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val buyView = convertView ?: inflater.inflate(R.layout.buy_item, null)

        // Get the patient data at the current position
        val product = getItem(posicion)

        // Bind patient data to TextViews in the custom layout
        val nameTextView = buyView.findViewById<TextView>(R.id.item_name)
        val resourcesTextView = buyView.findViewById<TextView>(R.id.item_detail)
        val quantityTextView = buyView.findViewById<TextView>(R.id.item_quantity)
        val priceTextView = buyView.findViewById<TextView>(R.id.item_price)

        // Set the patient data in the TextViews
        nameTextView.text = product?.name
        resourcesTextView.text = product?.detail
        quantityTextView.text = product?.quantity.toString()
        priceTextView.text = product?.price.toString()

        return buyView
    }
}