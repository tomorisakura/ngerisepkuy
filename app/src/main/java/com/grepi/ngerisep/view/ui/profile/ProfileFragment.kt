package com.grepi.ngerisep.view.ui.profile

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.grepi.ngerisep.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.coroutines.*

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var profileViewModel: ProfileViewModel
    private var mKeyPref = "pref_key"
    private var mKeyName = "grepigantenk"
    private var mKeyPassion = "passion"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tvName : TextView
    private lateinit var tvPassion : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profil, container, false)
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = activity!!.getSharedPreferences(mKeyPref, Context.MODE_PRIVATE)
        tvName = view.findViewById(R.id.profile_name)
        tvPassion = view.findViewById(R.id.profile_passion)
        edit_button.setOnClickListener(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.search_icon)
        item?.isVisible = false
    }

    private fun customDioalog() {
        val mView = layoutInflater.inflate(R.layout.dialog_edit, null)
        val name = mView.findViewById<TextInputLayout>(R.id.edt_name)
        val passion = mView.findViewById<TextInputLayout>(R.id.edt_passion)
        val mName = name.editText?.text
        val mPassion = passion.editText?.text

        MaterialAlertDialogBuilder(context)
            .setTitle("Customize Profile")
            .setView(mView)
            .setNegativeButton("Close") { dialogInterface, i ->
                dialogInterface.cancel()
            }
            .setPositiveButton("Save"){ dialogInterface, i ->
                val editor = sharedPreferences.edit()
                if (mName.isNullOrEmpty() && mPassion.isNullOrEmpty()) {
                    editor.putString(mKeyName, "Hai Kosong \uD83D\uDE04")
                    editor.putString(mKeyPassion, "kosong ?")
                    editor.apply()
                    setSnackBar("masukin nama dan passion yang keren ya \uD83D\uDE04")
                    dialogInterface.run {
                        setShared()
                    }
                } else {
                    editor.putString(mKeyName, mName.toString())
                    editor.putString(mKeyPassion, mPassion.toString())
                    editor.apply()
                    setSnackBar("Saved ❤")
                    dialogInterface.run {
                        setShared()
                    }
                }
            }.create().show()
    }

    private fun setShared() {
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000L)
            tvName.text = sharedPreferences.getString(mKeyName, "")
            tvPassion.text = sharedPreferences.getString(mKeyPassion, "")
        }
    }

    private fun setSnackBar(message: String) {
        Snackbar.make(profile_fragment, message, Snackbar.LENGTH_SHORT).setTextColor(Color.WHITE).setAnchorView(nav_view).show()
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.edit_button -> {
                customDioalog()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setShared()
    }
}
