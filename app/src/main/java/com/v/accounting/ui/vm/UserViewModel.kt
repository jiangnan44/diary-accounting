package com.v.accounting.ui.vm

import android.app.Application
import android.net.Uri
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.navigation.findNavController
import com.v.accounting.App
import com.v.accounting.BR
import com.v.accounting.R
import com.v.accounting.entity.User
import com.v.accounting.repository.UserRepository
import com.v.accounting.ui.base.BaseViewModel

/**
 * Author:v
 * Time:2021/6/3
 */
class UserViewModel(
    private val app: Application,
    private val userRepository: UserRepository
) : BaseViewModel() {
    private val TAG = "UserViewModel"

    private var avatarUri: Uri? = null
    val radioGender: MutableLiveData<Byte> by lazy { MutableLiveData(0) }


    var toast: String? = null
        @Bindable get
        private set(value) {
            field = value
            notifyPropertyChanged(BR.toast)
        }


    /**
     * you add an user in AddUserFragment and the added data will be
     * updated in MineFragment
     */
    val author: LiveData<User> = liveData {
        emitSource(userRepository.getLastUser().map { user ->
            user ?: User(app.getString(R.string.app_name))
        })
    }


    fun saveUserClick(etName: EditText, etPhone: EditText) {
        prepareUser(
            etName.text.toString(),
            etPhone.text.toString()
        )?.let { user ->
            launchOnViewModelScope {
                toast = if (userRepository.addUser(user)) {
                    etName.findNavController().navigateUp()
                    "Add User Success!"
                } else {
                    "Error when add user to database"
                }
            }
        }
    }

    private fun prepareUser(name: String?, phone: String?): User? {
        if (name.isNullOrBlank()) {
            toast = "null name!!"
            return null
        }

        if (phone.isNullOrBlank()) {
            toast = "Empty Phone!!"
        }

        return User(name).apply {
            this.phone = phone
            this.avatar = avatarUri?.toString()
            this.gender = radioGender.value ?: 0
        }
    }


    fun toAddUser(view: View) {
        view.findNavController()
            .navigate(R.id.nav_action_mine_to_add_user)
    }


    /**
     * this function have to handle result in Activity.onActivityResult
     * also requestCameraPermission if took photo,so better to use an
     * Activity instead of fragment for production app,maybe later I'll
     * rewrite it
     */
    fun chooseAvatar(iv: ImageView) {
        toast = "choose avatar"
        /*   launchOnViewModelScope {
               userRepository.clearTable()
           }*/
    }
}