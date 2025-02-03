package com.example.videojocapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.videojocapp.data.database.VideojocDatabase
import com.example.videojocapp.data.model.Videojoc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideojocViewModel(application: Application) : AndroidViewModel(application) {

    private val videojocDao = VideojocDatabase.getDatabase(application).videojocDao()

    private val _allVideojocs = MutableLiveData<List<Videojoc>>()
    val allVideojocs: LiveData<List<Videojoc>> = _allVideojocs

    private val _videojoc = MutableLiveData<Videojoc?>()
    val videojoc: LiveData<Videojoc?> = _videojoc

    init {
        loadAllVideojocs()
    }

    private fun loadAllVideojocs() {
        viewModelScope.launch {
            videojocDao.allVideojocs?.let { flow ->
                flow.collect { videojocs ->
                    if (videojocs != null) {
                        _allVideojocs.postValue(videojocs.filterNotNull())
                    }
                }
            }
        }
    }

    fun addVideojoc(videojoc: Videojoc) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                videojocDao.insertVideojoc(videojoc)
            }
            loadAllVideojocs() // Refresh the list after adding
        }
    }

    fun updateVideojoc(videojoc: Videojoc) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                videojocDao.updateVideojoc(videojoc)
            }
            loadAllVideojocs() // Refresh the list after updating
        }
    }

    fun deleteVideojoc(videojoc: Videojoc) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                videojocDao.deleteVideojoc(videojoc)
            }
            loadAllVideojocs() // Refresh the list after deleting
        }
    }

    fun getVideojocById(id: Int) {
        viewModelScope.launch {
            videojocDao.getVideojocById(id)?.collect { videojoc ->
                _videojoc.postValue(videojoc)
            }
        }
    }
}