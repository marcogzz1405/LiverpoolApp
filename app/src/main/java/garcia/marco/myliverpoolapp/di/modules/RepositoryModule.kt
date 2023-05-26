package garcia.marco.myliverpoolapp.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import garcia.marco.myliverpoolapp.data.repository.GetProductsRemoteDataSource
import garcia.marco.myliverpoolapp.data.repository.GetProductsRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    fun provideGetListRepository(
        remoteDataSource: GetProductsRemoteDataSource
    ) : GetProductsRepository =
        GetProductsRepository(remoteDataSource)

}