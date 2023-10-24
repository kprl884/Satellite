package com.alpstein.satellite.app.list

import com.alpstein.satellite.domain.entity.Satellite

data class ListScreenUIState(
    val isListLoading: Boolean = true,
    val satelliteList: List<Satellite>? = null
)