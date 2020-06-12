package com.examen3.ui

import android.accounts.NetworkErrorException
import com.examen3.data.model.Face
import com.examen3.data.repository.remote.RemoteRepository
import com.examen3.data.repository.remote.RetrofitRemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(private val view: View) {
    private val remoteRepository: RemoteRepository = RetrofitRemoteRepository()

    fun init() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val avatarListResponse =
                    withContext(Dispatchers.IO) {
                        remoteRepository.getAvatarList()
                    }
                view.showAvatarList(avatarListResponse)
            } catch (e: NetworkErrorException) {
                view.showError(e.message!!)
            }

        }
    }


    interface View {
        fun showAvatarList(avatarData: Face)
        fun showError(message: String)
    }
}