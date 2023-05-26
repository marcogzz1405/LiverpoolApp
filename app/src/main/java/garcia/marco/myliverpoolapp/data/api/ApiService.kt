package garcia.marco.myliverpoolapp.data.api

import garcia.marco.myliverpoolapp.data.remote.response.GetListProducts
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v3/plp?")
    suspend fun getList(
        @Query("page-number") pageNumber: Int,
        @Query("search-string") searchString: String? = null
    ): GetListProducts

    @GET("v3/plp?minSortPrice|0?")
    suspend fun getListBySort(
        @Query("page-number") pageNumber: Int,
        @Query("search-string") searchString: String? = null,
        @Path("minSortPrice|1") sortPrice : String
    ): GetListProducts

}