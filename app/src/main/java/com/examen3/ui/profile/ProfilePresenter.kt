package com.examen3.ui.profile

import com.examen3.data.model.User
import com.examen3.data.repository.local.LocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfilePresenter(private val view: View, private val localRepository: LocalRepository) {
    fun init(username:String) {
        CoroutineScope(Dispatchers.Main).launch {
            val user = withContext(Dispatchers.IO) {
                localRepository.getUser(username)
            }
            view.showUser(user)
        }
    }


    interface View {
        fun showUser(user: User)
        fun showError(message: String)
    }
}