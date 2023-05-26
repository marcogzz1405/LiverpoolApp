package garcia.marco.myliverpoolapp.data.repository

import garcia.marco.myliverpoolapp.data.remote.response.GetListProducts
import javax.inject.Inject

class GetProductsRepository @Inject constructor(private val getProductsRemoteDataSource: GetProductsRemoteDataSource) {
    suspend fun getProducts(pageNumber: Int, searchString: String? = null): GetListProducts {
        return getProductsRemoteDataSource.getProducts(pageNumber, searchString)
    }

    suspend fun getProductsBySort(pageNumber: Int, searchString: String? = null, sortPrice: String): GetListProducts {
        return getProductsRemoteDataSource.getProductsBySort(pageNumber, searchString, "")
    }
}

interface GetProductsRemoteDataSource {
    suspend fun getProducts(pageNumber: Int, searchString: String? = null): GetListProducts
    suspend fun getProductsBySort(pageNumber: Int, searchString: String? = null, sortPrice: String, ): GetListProducts
}