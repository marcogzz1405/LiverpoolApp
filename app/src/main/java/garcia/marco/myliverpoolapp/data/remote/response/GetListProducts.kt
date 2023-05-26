package garcia.marco.myliverpoolapp.data.remote.response

data class GetListProducts(
    val status: Status,
    val plpResults: PlpResults
)

data class Status(
    val status: String,
    val statusCode: Int
)

data class PlpResults(
    val label: String,
    val plpState: PlpState,
    val records: MutableList<Records>
)

data class PlpState(
    val categoryId: String,
    val currentSortOption: String,
    val currentFilters: String,
    val firstRecNum: Int,
    val lastRecNum: Int,
    val recsPerPage: Int,
    val totalNumRecs: Int,
    val plpSellerName: String
)

data class Records(
    val productId: String,
    val productDisplayName: String,
    val listPrice: Double,
    val promoPrice: Double,
    val smImage: String,
    val lgImage: String,
    val xlImage: String,
    val variantsColor: MutableList<VariantsColor>
)

data class VariantsColor(
    val colorName: String,
    val colorHex: String,
    val colorImageURL: String
)
