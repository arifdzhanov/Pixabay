package com.marlen.pixabay.domain

import com.marlen.pixabay.domain.models.DeviceOrientation
import com.marlen.pixabay.domain.models.ScreenSize

interface DeviceRepository {

    fun getScreenSize(): ScreenSize

    fun getDeviceOrientation(): DeviceOrientation
}