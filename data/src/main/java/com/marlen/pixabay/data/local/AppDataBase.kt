package com.marlen.pixabay.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.marlen.pixabay.data.local.converters.Converters
import com.marlen.pixabay.data.local.entity.ImageEntity

private const val DATABASE_NAME = "images"

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun itemDao(): ImageDao
}

fun ImagesDatabase(applicationContext: Context): ImagesDatabase {
    return Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        ImagesDatabase::class.java,
        DATABASE_NAME
    ).build()
}