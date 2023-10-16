package cl.lisgc.shoplist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cl.lisgc.shoplist.R
import cl.lisgc.shoplist.entity.StoreT

class StoreAdapterList(
    context: Context,
    resources: Int,
    store: List<StoreT>
) : ArrayAdapter<StoreT>(context, resources, store){

    override fun getView(posicion: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val storeTView = convertView ?: inflater.inflate(R.layout.store_item, null)

        // Get the patient data at the current position
        val storeT = getItem(posicion)

        // Bind patient data to TextViews in the custom layout
        val nameTextView = storeTView.findViewById<TextView>(R.id.store_name)
        val resourcesTextView = storeTView.findViewById<TextView>(R.id.store_location)
        val scheduleTextView = storeTView.findViewById<TextView>(R.id.store_schedule)

        // Set the patient data in the TextViews
        nameTextView.text = storeT?.name
        resourcesTextView.text = storeT?.location
        scheduleTextView.text = storeT?.schedule

        return storeTView
    }
}