package garcia.marco.myliverpoolapp.ui.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import garcia.marco.myliverpoolapp.R
import garcia.marco.myliverpoolapp.data.remote.response.Records
import garcia.marco.myliverpoolapp.databinding.ActivityMainBinding
import garcia.marco.myliverpoolapp.ui.bases.BaseActivity
import garcia.marco.myliverpoolapp.ui.custom.DialogUtils
import garcia.marco.myliverpoolapp.ui.utils.Constants
import garcia.marco.myliverpoolapp.ui.utils.onTextChangeEvents
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    var adapter: ProductAdapter? = null

    var searchText: String? = null

    override fun createView() {
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        viewModel.getProducts(Constants.INITIAL_PAGE)
    }

    override fun collectFlows() {
        with(lifecycleScope) {
            launchWhenCreated {
                viewModel.result.collect {
                    when(it) {
                        is MainUiState.Loading -> {
                            loader.show()
                        }
                        is MainUiState.Waiting -> { }
                        is MainUiState.Error -> {
                            loader.hide()
                            showError(it.throwable)
                        }
                        is MainUiState.Success -> {
                            loader.hide()
                            updateProduct(it.getListProducts.plpResults.records)
                        }
                        else -> {}
                    }
                }
            }

            launchWhenResumed {
                binding.edSearch.ivSearch.setOnClickListener {
                    if (searchText != null) {
                        viewModel.getProducts(Constants.INITIAL_PAGE, searchText)
                    }
                }
            }

            launchWhenResumed {
                binding.edSearch.etSearch.onTextChangeEvents.collect {
                    searchText = it.toString()
                }
            }

            launchWhenCreated {
                binding.ivFilter.setOnClickListener {
                    DialogUtils.showFilterDialog(this@MainActivity) { isSelected, dialog ->
                        when(isSelected) {

                        }
                    }
                }
            }
        }
    }

    private fun updateProduct(items: MutableList<Records>){
        adapter = ProductAdapter(items)
        binding.tvProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.tvProduct.adapter = adapter
    }

}