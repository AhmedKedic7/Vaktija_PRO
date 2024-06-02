package com.example.vaktijapro.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.vaktijapro.model.models.City
import com.example.vaktijapro.model.models.User
import com.example.vaktijapro.model.repositories.CityRepository
import com.example.vaktijapro.model.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CityViewModel(
    private val userRepository: UserRepository,
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _selectedCity = MutableStateFlow<City?>(null)
    val selectedCity: StateFlow<City?> = _selectedCity.asStateFlow()

    val cities: Flow<List<City>> = cityRepository.getCitiesOrderedByName()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun selectCity(city: City) {
        viewModelScope.launch {
            _selectedCity.value = city
        }
    }

    /*private suspend fun updateSelectedCityInDatabase(userId: Int, cityId: Int) {
        // Call the repository function to update the selected city ID in the user entity
        userRepository.updateSelectedCity(userId, cityId)
    }*/


    fun loadSelectedCity(cityId: Int) {
        viewModelScope.launch {
            val city = cityRepository.getCityById(cityId)
            Log.d("CityViewModel", "Loaded city: $city")
            _selectedCity.value = city
        }
    }

    /*fun getSelectedCity(user_id:Int):Int{

        val cityId = userRepository.getSelectedCity(user_id)
        return cityId

    }*/

    private suspend fun getCityById(cityId: Int): City? {
        return cityRepository.getCityById(cityId)
    }

}