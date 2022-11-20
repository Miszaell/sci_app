package com.codeiu.sci_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codeiu.sci_app.databinding.ActivityMainBinding
import com.codeiu.sci_app.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setBackground(null)

        loadFragment(homeFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    replaceFragment(homeFragment())
                    true
                }
                R.id.account -> {
                    replaceFragment(AccountFragment())
                    true
                }
                R.id.help -> {
                    replaceFragment(HelpFragment())
                    true
                }
                R.id.exit ->  {
                    sendToLogin()
                    true
                }
             else -> false
            }
        }

        binding.apply {
            btnAdd.setOnClickListener {
                showDialogOne()
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun sendToLogin(){
        val intent = Intent(this@MainActivity,LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showDialogOne() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.fragment_bottom_sheet_dialog)
        val btnUsers= dialog.findViewById<RelativeLayout>(R.id.fr_users)
        val btnCustomers= dialog.findViewById<RelativeLayout>(R.id.fr_customers)
        val btnBranchOffices= dialog.findViewById<RelativeLayout>(R.id.fr_branch_offices)

        btnUsers?.setOnClickListener {
            dialog.dismiss()
            replaceFragment(UsersFragment())
        }
        btnCustomers?.setOnClickListener {
            dialog.dismiss()
            replaceFragment(CustomersFragment())
        }
        btnBranchOffices?.setOnClickListener {
            dialog.dismiss()
            replaceFragment(BranchOfficesFragment())
        }
        dialog.show()
    }


}
