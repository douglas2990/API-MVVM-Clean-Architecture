package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.di

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository.ResultRepositoryImpl
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.ResultRepository
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetResultUseCase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.util.Constantes.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn( ViewModelComponent::class )
object AppModulo {

    @Provides
    fun proverRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
    }

    @Provides
    fun proverDummyApi(
        retrofit: Retrofit
    ) : DummyAPI {
        return retrofit.create( DummyAPI::class.java )
    }

    @Provides
    fun proverUsuarioRespository(
        dummyAPI: DummyAPI
    ) : ResultRepository {
        return ResultRepositoryImpl( dummyAPI )
    }

    @Provides
    fun proverResultUseCase( resultRepository: ResultRepository
    ) : GetResultUseCase {
        return GetResultUseCase( resultRepository )
    }
}