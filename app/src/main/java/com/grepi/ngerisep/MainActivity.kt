package com.grepi.ngerisep

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.grepi.ngerisep.view.HomeActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class MainActivity : AppCompatActivity() {

    companion object {
        const val MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 10
        const val MY_PERMISSIONS_NETWORK_ACCESS = 101
        const val PERMISSION = "permission"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        checkStoragePermission()
        setupTheme()
        Handler().postDelayed({
            val mIntent = Intent(this, HomeActivity::class.java)
            startActivity(mIntent)
            finish()
        }, 3000L)
    }

    private fun checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //is not granted
            Log.d("_permissionDeny", "permission deny")
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE
                )
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET),
                    MY_PERMISSIONS_NETWORK_ACCESS
                )
            }
        } else {
            Log.d(PERMISSION, "permission granted")
        }
    }

    private fun setToast(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}
