package com.examen3.ui.Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.examen3.data.model.User
import com.examen3.data.repository.local.RoomLocalRepository
import com.examen3.data.repository.local.UserDatabaseFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), ProfilePresenter.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.examen3.R.layout.activity_profile)

        val localRepository = RoomLocalRepository(UserDatabaseFactory.get(this))
        val presenter = ProfilePresenter(this, localRepository)
        presenter.init()
    }

    override fun showUser(user: User) {
        loginUser.text = user.Username
    }

    override fun showError(message: String) {
    }
}
