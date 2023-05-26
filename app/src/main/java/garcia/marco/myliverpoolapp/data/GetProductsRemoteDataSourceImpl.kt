package garcia.marco.myliverpoolapp.data

import garcia.marco.myliverpoolapp.data.api.ApiService
import garcia.marco.myliverpoolapp.data.remote.response.GetListProducts
import garcia.marco.myliverpoolapp.data.repository.GetProductsRemoteDataSource
import javax.inject.Inject

class GetProductsRemoteDataSourceImpl @Inject constructor(val api: ApiService): GetProductsRemoteDataSource {
    override suspend fun getProducts(pageNumber: Int, searchString: String?): GetListProducts {
        return api.getList(pageNumber, searchString)
    }

    override suspend fun getProductsBySort(
        pageNumber: Int,
        searchString: String?,
        sortPrice: String,
    ): GetListProducts {
        return api.getListBySort(pageNumber, searchString, "")
    }

}