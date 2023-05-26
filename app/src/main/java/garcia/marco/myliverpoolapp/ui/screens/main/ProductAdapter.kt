package garcia.marco.myliverpoolapp.ui.screens.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import garcia.marco.myliverpoolapp.data.remote.response.GetListProducts
import garcia.marco.myliverpoolapp.data.remote.response.Records
import garcia.marco.myliverpoolapp.databinding.ItemProductBinding

class ProductAdapter(private val items: MutableList<Records>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemProductBinding, val context: Context): RecyclerView.ViewHolder(binding.root) {
        var adapter: VariantsColorAdapter? = null
        fun bind(item: Records) {
            with(binding) {
                Glide.with(context)
                    .load(item.lgImage).into(ivProduct)
                tvNameProduct.text = item.productDisplayName
                tvPriceList.text = "$${item.listPrice}"
                if (item.promoPrice.toString().isEmpty()) {
                    tvPricePromo.visibility = View.INVISIBLE
                } else {
                    tvPricePromo.visibility = View.VISIBLE
                    tvPricePromo.text = "$${item.promoPrice}"
                }
                adapter = VariantsColorAdapter(item.variantsColor)
                rvVariantsColors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                rvVariantsColors.adapter = adapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

}