package garcia.marco.myliverpoolapp.di.modules

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ResourcesModule {

    @Provides
    fun provideResources(context: Context) : Resources {
        return context.resources
    }

}