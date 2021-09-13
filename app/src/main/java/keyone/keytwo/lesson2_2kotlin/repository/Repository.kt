package keyone.keytwo.lesson2_2kotlin.repository

import keyone.keytwo.lesson2_2kotlin.domain.Weather

interface Repository {
    fun getWeatherFromRemoteSource():Weather
    fun getWeatherFromLocalSource():Weather
}