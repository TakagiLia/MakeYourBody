package com.takagi.makeyourbody

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogoutDialog : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {

            val builder = AlertDialog.Builder(it, R.style.LogoutDialogStyle)
            builder.setMessage(R.string.dialog_title_logout)
                .setPositiveButton(
                    R.string.dialog_yes
                ) { _, _ ->
                    Log.d("--success--", "ログアウトする")
                    Firebase.auth.signOut()
                    findNavController().navigate(R.id.action_login_screen)
                }
                .setNegativeButton(
                    R.string.dialog_no
                ) { _, _ ->
                    Log.d("--success--", "ログアウトしない")
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
