package garcia.marco.myliverpoolapp.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import garcia.marco.myliverpoolapp.data.remote.response.GetListProducts
import garcia.marco.myliverpoolapp.data.usescases.GetProductsBySort
import garcia.marco.myliverpoolapp.data.usescases.GetProductsFlow
import garcia.marco.myliverpoolapp.domain.OperationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getProductsFlow: GetProductsFlow, private val getProductsBySort: GetProductsBySort): ViewModel() {

    private val _result = MutableStateFlow<MainUiState>(MainUiState.Waiting)
    val result : StateFlow<MainUiState> = _result

    fun getProducts(pageNumber: Int, searchString: String? = null){
        viewModelScope.launch {
            val flow = getProductsFlow.invoke(pageNumber, searchString)
            _result.value = MainUiState.Loading
            flow.collect {
                when(it) {
                    is OperationResult.Success -> _result.value = MainUiState.Success(it.data!!)
                    is OperationResult.Error -> _result.value = MainUiState.Error(it.throwable)
                }
            }
        }
    }

    fun getProductsBySort(pageNumber: Int, sortPrice: String, searchString: String? = null){
        viewModelScope.launch {
            val flow = getProductsBySort.invoke(pageNumber, sortPrice, searchString)
            _result.value = MainUiState.Loading
            flow.collect {
                when(it) {
                    is OperationResult.Success -> _result.value = MainUiState.Success(it.data!!)
                    is OperationResult.Error -> _result.value = MainUiState.Error(it.throwable)
                }
            }
        }
    }

}

sealed class MainUiState {
    data class Success(val getListProducts: GetListProducts): MainUiState()
    data class Error(val throwable: Throwable): MainUiState()
    object Waiting : MainUiState()
    object Loading : MainUiState()
}