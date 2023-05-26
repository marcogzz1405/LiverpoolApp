package garcia.marco.myliverpoolapp.data.remote.mappers

import retrofit2.HttpException

abstract class HttpExceptionMapper(protected val callArguments: List<String>) {
    abstract fun map(httpException: HttpException) : Exception?
}
