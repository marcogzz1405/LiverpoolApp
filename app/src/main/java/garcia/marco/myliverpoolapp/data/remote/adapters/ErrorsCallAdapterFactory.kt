package garcia.marco.myliverpoolapp.data.remote.adapters

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ErrorsCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java || returnType !is ParameterizedType || returnType.actualTypeArguments.size != 1) {
            return null
        }

        val delegate = retrofit.nextCallAdapter(this, returnType, annotations)
        @Suppress("UNCHECKED_CAST")
        return ErrorsCallAdapter(
            delegateAdapter = delegate as CallAdapter<Any, Call<*>>
        )
    }
}