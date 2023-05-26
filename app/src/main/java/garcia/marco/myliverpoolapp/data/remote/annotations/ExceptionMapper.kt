package garcia.marco.myliverpoolapp.data.remote.annotations

import garcia.marco.myliverpoolapp.data.remote.mappers.HttpExceptionMapper
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class ExceptionMapper(val value : KClass<out HttpExceptionMapper>)
