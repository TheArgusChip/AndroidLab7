package com.bignerdranch.android.photogallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

private const val TAG = "PhotoGalleryActivity"
class PhotoGalleryActivity : AppCompatActivity(), PhotoGalleryFragment.Callbacks {
    private lateinit var photoGalleryViewModel: PhotoGalleryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_gallery)
        photoGalleryViewModel = ViewModelProviders.of(this).get(PhotoGalleryViewModel::class.java)
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, PhotoGalleryFragment.newInstance()).commit()
        }

    }
    override fun onDatabaseSelected()
    {
        photoGalleryViewModel.showDatabaseGallery()
        val fragment = PhotoGalleryDatabaseFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onAddSelected(galleryItem: GalleryItem)
    {
        photoGalleryViewModel.addPhoto(galleryItem)
    }
    override fun onDeleteSelected()
    {
        photoGalleryViewModel.deletephotos()
    }
    companion object {
        fun newIntent(context: Context): Intent
        {
            return Intent(context,
                PhotoGalleryActivity::class.java)

        }
    }

}