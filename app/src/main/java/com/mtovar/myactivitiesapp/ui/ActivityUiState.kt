package com.mtovar.myactivitiesapp.ui

sealed class ActivityUiState {
    object Empty : ActivityUiState()
    object Loading : ActivityUiState()
    data class Success(val data: String) : ActivityUiState()
}