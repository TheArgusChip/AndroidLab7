package com.bignerdranch.android.photogallery

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.photogallery.database.GalleryDatabase
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
    private val executor = Executors.newSingleThreadExecutor()
    fun getPhotos(): LiveData<List<Item>> = database.galleryDao().getPhotos()
    private fun GalleryItem.toItem(): Item {
        return Item(title, id, url)
    }
    fun addPhoto(photo: GalleryItem) {
        val item = photo.toItem()
        executor.execute {
            if (database.galleryDao().getPhoto(item.url)!=item){
                database.galleryDao().addPhoto(item)
            }
        }
    }

    fun deleteAllPhotos() {
        executor.execute {
            database.galleryDao().deletePhotos()
        }
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