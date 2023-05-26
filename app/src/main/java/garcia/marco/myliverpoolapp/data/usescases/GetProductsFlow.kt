package garcia.marco.myliverpoolapp.data.usescases

import garcia.marco.myliverpoolapp.data.remote.response.GetListProducts
import garcia.marco.myliverpoolapp.data.repository.GetProductsRepository
import garcia.marco.myliverpoolapp.domain.OperationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsFlow @Inject constructor(private val repository: GetProductsRepository) {

    suspend operator fun invoke(pageNumber: Int, searchString: String? = null): Flow<OperationResult<GetListProducts>> = flow {
        try {
            val response = repository.getProducts(pageNumber, searchString)
            emit(OperationResult.Success(response))
        } catch (ex: Exception){
            emit(OperationResult.Error(ex))
        }
    }.catch {
        emit(OperationResult.Error(it))
    }

}