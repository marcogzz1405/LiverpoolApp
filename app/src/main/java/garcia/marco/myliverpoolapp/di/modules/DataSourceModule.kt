package garcia.marco.myliverpoolapp.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import garcia.marco.myliverpoolapp.data.GetProductsRemoteDataSourceImpl
import garcia.marco.myliverpoolapp.data.repository.GetProductsRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsGetProductsRemoteDataSource(getProductsRemoteDataSourceImpl: GetProductsRemoteDataSourceImpl) : GetProductsRemoteDataSource

}