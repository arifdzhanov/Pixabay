package com.marlen.pixabay.data

import android.content.Context
import android.content.res.Configuration
import com.marlen.pixabay.domain.DeviceRepository
import com.marlen.pixabay.domain.models.DeviceOrientation
import com.marlen.pixabay.domain.models.ScreenSize
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): DeviceRepository {

    override fun getScreenSize(): ScreenSize {
        val metrics = context.resources.displayMetrics
        return ScreenSize(
            width = metrics.widthPixels,
            height = metrics.heightPixels
        )
    }

    override fun getDeviceOrientation(): DeviceOrientation {
        val orientation: Int = context.getResources().getConfiguration().orientation
        return if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            DeviceOrientation.Landscape
        } else {
            DeviceOrientation.Portrait
        }
    }
}