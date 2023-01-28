package com.examen3.ui

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.examen3.data.model.Face
import com.examen3.data.repository.local.RoomLocalRepository
import com.examen3.data.repository.local.UserDatabaseFactory
import com.examen3.ui.profile.ProfileActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainPresenter.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.examen3.R.layout.activity_main)
        val localRepository = RoomLocalRepository(UserDatabaseFactory.get(this))
        val presenter = MainPresenter(this, localRepository)

        presenter.init()

        eyeList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter.loadImage(
                    parent?.getItemAtPosition(p2).toString(),
                    noseList.selectedItem.toString(),
                    mouthList.selectedItem.toString()
                )
            }

        }

        noseList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter.loadImage(
                    eyeList.selectedItem.toString(),
                    parent?.getItemAtPosition(p2).toString(),
                    mouthList.selectedItem.toString()
                )
            }
        }

        mouthList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter.loadImage(
                    eyeList.selectedItem.toString(),
                    noseList.selectedItem.toString(),
                    parent?.getItemAtPosition(p2).toString()
                )
            }
        }

        createButton.setOnClickListener {
            if (usernameText.text.isNotEmpty() && nameText.text.isNotEmpty()) {
                presenter.addUser(
                    username = usernameText.text.toString(),
                    name = nameText.text.toString(),
                    eyes = eyeList.selectedItem.toString(),
                    nose = noseList.selectedItem.toString(),
                    mouth = mouthList.selectedItem.toString()
                )
                val intent = Intent(this, ProfileActivity::class.java).apply {}
                intent.putExtra("username", usernameText.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Introduce username and name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showAvatarList(avatarData: Face) {
        val eyeSpinnerArrayAdapter = ArrayAdapter<String>(
            this, R.layout.simple_spinner_item, avatarData.eyes
        )
        eyeList.adapter = eyeSpinnerArrayAdapter

        val noseSpinnerArrayAdapter = ArrayAdapter<String>(
            this, R.layout.simple_spinner_item, avatarData.nose
        )
        noseList.adapter = noseSpinnerArrayAdapter

        val mouthSpinnerArrayAdapter = ArrayAdapter<String>(
            this, R.layout.simple_spinner_item, avatarData.mouth
        )
        mouthList.adapter = mouthSpinnerArrayAdapter
    }

    override fun showImage(url: String) {
        Picasso.get().load(url).into(profileImg)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
