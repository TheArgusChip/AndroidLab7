package com.bignerdranch.android.photogallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager

private const val TAG = "PhotoGalleryDatabaseFragment"
private const val POLL_WORK = "POLL_WORK"

class PhotoGalleryDatabaseFragment : Fragment() {

    private lateinit var photoGalleryViewModel: PhotoGalleryViewModel
    private lateinit var photoRecyclerView: RecyclerView
    private var adapter: PhotoAdapter? = PhotoAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        photoGalleryViewModel =
            ViewModelProviders.of(this).get(PhotoGalleryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_database_recycle, container, false)
        photoRecyclerView = view.findViewById(R.id.gallery_recycler_view)
        photoRecyclerView.layoutManager = GridLayoutManager(context, 1)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoGalleryViewModel.itemLiveData.observe(
            viewLifecycleOwner,
            Observer { items ->
                photoRecyclerView.adapter = PhotoAdapter(items)
                updateUI(items)
            })

    }
    private fun updateUI(items: List<Item>) {
        adapter = PhotoAdapter(items)
        photoRecyclerView.adapter = adapter
    }
    private inner class PhotoHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = itemView.findViewById(R.id.photo_title)
        val urlTextView: TextView = itemView.findViewById(R.id.photo_url)
    }

    private inner class PhotoAdapter(private val items: List<Item>)
        : RecyclerView.Adapter<PhotoHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): PhotoHolder {
            val view = layoutInflater.inflate(
                R.layout.list_item_database,
                parent,
                false
            ) as View
            return PhotoHolder(view)
        }
        override fun getItemCount(): Int = items.size
        override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
            val item = items[position]
            holder.apply {
                titleTextView.text = item.title
                urlTextView.text = item.url
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_gallery_database, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_delete_database_photos -> {
                photoGalleryViewModel.deletephotos()
                Toast.makeText(
                    context,
                    R.string.delete_photos_from_database_success,
                    Toast.LENGTH_SHORT
                ).show()
                true
            }

            else ->
                super.onOptionsItemSelected(item)
        }
    }
}