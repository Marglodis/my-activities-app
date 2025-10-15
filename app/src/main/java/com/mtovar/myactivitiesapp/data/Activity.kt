package com.mtovar.myactivitiesapp.data

import java.util.UUID

data class Activity(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val date: String,
    val time: String
)