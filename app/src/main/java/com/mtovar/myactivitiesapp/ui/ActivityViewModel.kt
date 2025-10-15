package com.mtovar.myactivitiesapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtovar.myactivitiesapp.data.Activity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ActivityViewModel : ViewModel() {
    // _uiState es privado y mutable. Solo el ViewModel puede modificarlo.
    private val _uiState = MutableStateFlow<ActivityUiState>(ActivityUiState.Empty)

    //uiState es público e inmutable. La UI lo observa para reaccionar a los cambios.
    val uiState: StateFlow<ActivityUiState> = _uiState.asStateFlow()

    fun addActivity(name: String, description: String, date: String, time: String) {
        // Usamos una corutina para no bloquea el hilo principal
        viewModelScope.launch {
            // Simulamos carga
            _uiState.update { currentState ->
                // Si hay datos, los mantenemos para nomostrar un loader a pantalla completa
                currentState as? ActivityUiState.Success ?: ActivityUiState.Loading
            }
            delay(1000) // Simula una espera
            val newActivity =
                Activity(name = name, description = description, date = date, time = time)

            _uiState.update { currentState ->
                val currentActivities = if (currentState is ActivityUiState.Success) {
                    currentState.activities
                } else {
                    emptyList()
                }
                ActivityUiState.Success(currentActivities + newActivity)
            }


        }
    }


}