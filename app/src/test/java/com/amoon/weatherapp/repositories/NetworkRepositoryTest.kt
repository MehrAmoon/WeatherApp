package com.amoon.weatherapp.repositories

import com.amoon.weatherapp.MockUtils.apiResponse
import com.amoon.weatherapp.MockUtils.domainWeather
import com.amoon.weatherapp.data.mappers.DataMapper
import com.amoon.weatherapp.data.network.ApiService
import com.amoon.weatherapp.data.repositories.NetworkRepositoryImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

class NetworkRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var dataMapper: DataMapper
    private lateinit var networkRepository: NetworkRepositoryImpl

    @Before
    fun setup() {
        apiService = mock()
        dataMapper = mock()
        networkRepository = NetworkRepositoryImpl(apiService, dataMapper)
    }

    @Test
    fun `getWeather should return DomainWeather`() = runBlocking {
        // Given
        val city = "Berlin"


        whenever(apiService.getWeather(query = city)).thenReturn(apiResponse)
        whenever(dataMapper.mapDataToDomain(apiResponse)).thenReturn(domainWeather)

        // When
        val result = networkRepository.getWeather(city)

        // Then
        verify(apiService).getWeather(query = city)
        verify(dataMapper).mapDataToDomain(apiResponse)
        assertEquals(domainWeather, result)
    }
}