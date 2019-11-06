package ru.spbstu.icst.categorymessages.di.module

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ru.spbstu.icst.categorymessages.di.AppGraphManager
import ru.spbstu.icst.categorymessages.di.IAppGraphManager
import ru.spbstu.icst.categorymessages.di.qualifier.GraphInternal
import ru.spbstu.icst.categorymessages.di.scope.BootScope
import ru.spbstu.icst.categorymessages.model.data.GsonDataMapper
import ru.spbstu.icst.categorymessages.model.data.IDataMapper

@Module
class BootstrapModule(private val context: Context) {

    @Provides
    @BootScope
    fun provideAppGraphManager(): IAppGraphManager {
        return AppGraphManager.instance()
    }

//    @Provides
//    @BootScope
//    fun provideConfigProvider(): IConfigProvider {
//        return ConfigProvider()
//    }

    @Provides
    @BootScope
    @GraphInternal
    fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//            .registerTypeAdapter(Instant::class.java, InstantTypeAdapter())
            .create()
    }

//    @Provides
//    @BootScope
//    fun provideBootstrapInteractor(
//        loginStateRepository: ILoginStateRepository,
//        graphManager: IAppGraphManager
//    ): IBootstrapInteractor {
//        return BootstrapInteractor(loginStateRepository, graphManager)
//    }


    @Provides
    @BootScope
    fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("app_shared_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @BootScope
    fun provideDataMapper(@GraphInternal gson: Gson): IDataMapper {
        return GsonDataMapper(gson)
    }

//    @Provides
//    @BootScope
//    fun provideAccessTokenStorage(prefs: SharedPreferences, mapper: IDataMapper): IAccessTokenStorage {
//        return AccessTokenStorage.newInstance(prefs, mapper)
//    }
//
//    @Provides
//    @BootScope
//    fun provideILoginRestService(): ILoginRestService {
//        return Retrofit.Builder()
//            .baseUrl(Route.BASE_AUTH_URL)
//            .client(OkHttpClientFactory.createClient(null))
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ILoginRestService::class.java)
//    }

//    @Provides
//    @BootScope
//    fun provideGithubDb(): GithubDb {
//        return Room.databaseBuilder(context, GithubDb::class.java, "database.db").build()
//    }
}