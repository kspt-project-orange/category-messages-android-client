package ru.spbstu.icst.categorymessages.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.egoraganin.githubsample.model.data.server.Route
import ru.spbstu.icst.categorymessages.di.qualifier.GraphInternal
import ru.spbstu.icst.categorymessages.di.scope.UserScope
import ru.spbstu.icst.categorymessages.model.OkHttpClientFactory
import ru.spbstu.icst.categorymessages.model.data.server.IUserRestService

@Module
class UserModule(/*private val userIdentity: UserIdentity*/) {

    @Provides
    @UserScope
    fun provideUserRestService(@GraphInternal gson: Gson): IUserRestService {
        return Retrofit.Builder()
            .baseUrl(Route.BASE_API_URL)
            .client(OkHttpClientFactory.createClient(/*userIdentity*/))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IUserRestService::class.java)
    }

//    @Provides
//    @UserScope
//    fun provideUserReposDao(db: GithubDb): IUserReposDao {
//        return db.getUserReposDb()
//    }
}