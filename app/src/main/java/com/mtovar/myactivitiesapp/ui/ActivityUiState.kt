package com.mtovar.myactivitiesapp.ui

import com.mtovar.myactivitiesapp.data.Activity

sealed class ActivityUiState {
    object Empty : ActivityUiState()
    object Loading : ActivityUiState()
    data class Success(val activities: List<Activity>) : ActivityUiState()
}