package com.bignerdranch.android.photogallery

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.photogallery.database.GalleryDatabase
import java.util.UUID
import java.util.concurrent.Executors

private const val DATABASE_NAME = "gallery"
class GalleryRepository private constructor(context: Context) {
    private val database: GalleryDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            GalleryDatabase::class.java,
            DATABASE_NAME
        )
        .build()
    private val galleryDao = database.galleryDao()
    private val executor = Executors.newSingleThreadExecutor()
    fun getPhotos() = database.galleryDao().getphotos()
    fun getPhotoByUrl(photoUrl: String) =
        database.galleryDao().getPhotoByUrl(photoUrl)

    fun addPhoto(photo: GalleryItem) {
        database.galleryDao().addphoto(photo)
    }

    fun deleteAllPhotos() {
        database.galleryDao().deletephotos()
    }

    companion object {
        private var INSTANCE: GalleryRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = GalleryRepository(context)
            }
        }
        fun get(): GalleryRepository {
            return INSTANCE ?:
            throw
            IllegalStateException("CrimeRepository must be initialized")
        }
    }

}