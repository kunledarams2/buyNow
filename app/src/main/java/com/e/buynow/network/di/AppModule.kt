package com.e.buynow.network.di

import android.app.Application
import android.content.Context
import androidx.room.Room
//import androidx.room.Room
import com.android_dr_app.network.NetworkResponseAdapterFactory
import com.e.buynow.network.api.URLS
import com.e.buynow.network.callback.EndPoint
import com.e.buynow.network.data.LocalDB
//import com.e.buynow.network.data.LocalDB
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(URLS.SERVER)
//        .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()


    @Provides
    @Singleton
    fun providerEndPointInterface(retrofit: Retrofit): EndPoint = retrofit.create(EndPoint::class.java)

    @Provides
    @Singleton
    fun providesApplicationContext(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesCartDataBase(app: Application): LocalDB = Room.databaseBuilder(app, LocalDB::class.java, "Cart_DB").build()
}