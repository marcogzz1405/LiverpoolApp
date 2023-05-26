package garcia.marco.myliverpoolapp.ui.screens.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import garcia.marco.myliverpoolapp.data.remote.response.Records
import garcia.marco.myliverpoolapp.data.remote.response.VariantsColor
import garcia.marco.myliverpoolapp.databinding.ItemVariantColorBinding

class VariantsColorAdapter(private val items: MutableList<VariantsColor>): RecyclerView.Adapter<VariantsColorAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemVariantColorBinding, val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VariantsColor){
            with(binding) {
                if (item.colorHex.equals("") || item.colorHex.isEmpty()) {
                    ivVariantColor.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                } else {
                    ivVariantColor.setBackgroundColor(Color.parseColor(item.colorHex))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVariantColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

}