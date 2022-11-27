package com.codeiu.sci_app.utils

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codeiu.sci_app.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

fun readOnly(text: TextInputEditText, readOnly: Boolean){
    text.isFocusable = !readOnly
    text.isFocusableInTouchMode = !readOnly
    text.isClickable = !readOnly
    text.isLongClickable = !readOnly
    text.isCursorVisible = !readOnly
}