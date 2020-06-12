package com.examen3.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.examen3.data.model.User
import com.examen3.data.repository.local.RoomLocalRepository
import com.examen3.data.repository.local.UserDatabaseFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), ProfilePresenter.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.examen3.R.layout.activity_profile)

        val localRepository = RoomLocalRepository(UserDatabaseFactory.get(this))
        val presenter = ProfilePresenter(this, localRepository)
        val username = intent.getStringExtra("username")
        presenter.init(username)
    }

    override fun showUser(user: User) {
        loginUser.text = user.Username
        Picasso.get().load(user.avatarUrl).into(loginAvatar)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
