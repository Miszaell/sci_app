package com.codeiu.sci_app.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codeiu.sci_app.R

class Utils {}
fun replaceFragment(fragment: Fragment, activity: AppCompatActivity, bundle: Bundle? = null) {
    if(bundle != null){
        fragment.arguments = bundle
    }
    val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.frame_layout, fragment)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()
}

