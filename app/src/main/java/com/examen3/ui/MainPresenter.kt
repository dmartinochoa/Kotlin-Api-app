package com.examen3.ui

import android.accounts.NetworkErrorException
import com.examen3.data.model.Face
import com.examen3.data.repository.local.LocalRepository
import com.examen3.data.repository.remote.RemoteRepository
import com.examen3.data.repository.remote.RetrofitRemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(private val view: View, private val localRepository: LocalRepository) {
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

    fun addUser(username: String, name: String, eyes: String, nose: String, mouth: String) {
        val eyesValue = eyes.substring(3)
        val noseValue = nose.substring(3)
        val mouthValue = mouth.substring(4)
        val url =
            "https://api.adorable.io/avatars/face/" + eyesValue + "/" + noseValue + "/" + mouthValue + "/EDEDFF/200"
        CoroutineScope(Dispatchers.Main).launch {
            val user = withContext(Dispatchers.IO) {
                localRepository.insertUser(username, name, url)
            }
        }
    }

    fun loadImage(eyes: String, nose: String, mouth: String) {
        val eyesValue = eyes.substring(3)
        val noseValue = nose.substring(3)
        val mouthValue = mouth.substring(4)
        val url =
            "https://api.adorable.io/avatars/face/" + eyesValue + "/" + noseValue + "/" + mouthValue + "/EDEDFF/200"
        view.showImage(url)
    }

    interface View {
        fun showAvatarList(avatarData: Face)
        fun showError(message: String)
        fun showImage(url: String)
    }
}