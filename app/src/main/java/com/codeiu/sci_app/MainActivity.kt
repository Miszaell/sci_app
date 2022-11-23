package com.codeiu.sci_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeiu.sci_app.adapters.UserAdapter
import com.codeiu.sci_app.dataClases.UserProvider
import com.codeiu.sci_app.databinding.ActivityMainBinding
import com.codeiu.sci_app.databinding.FragmentUsersBinding
import com.codeiu.sci_app.fragments.*
import com.codeiu.sci_app.utils.replaceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var usersBindig: FragmentUsersBinding

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        usersBindig = FragmentUsersBinding.inflate(layoutInflater)

        setContentView(binding.root)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.background = null
        loadFragment(homeFragment())

        initRecyclerView()
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(homeFragment(),this)
                    true
                }
                R.id.account -> {
                    replaceFragment(AccountFragment(),this)
                    true
                }
                R.id.help -> {
                    replaceFragment(HelpFragment(),this)
                    true
                }
                R.id.exit -> {
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

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun sendToLogin() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showDialogOne() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.fragment_bottom_sheet_dialog)
        val btnUsers = dialog.findViewById<RelativeLayout>(R.id.fr_users)
        val btnCustomers = dialog.findViewById<RelativeLayout>(R.id.fr_customers)
        val btnBranchOffices = dialog.findViewById<RelativeLayout>(R.id.fr_branch_offices)

        btnUsers?.setOnClickListener {
            dialog.dismiss()
            replaceFragment(UsersFragment(), this)
        }
        btnCustomers?.setOnClickListener {
            dialog.dismiss()
            replaceFragment(CustomersFragment(), this)
        }
        btnBranchOffices?.setOnClickListener {
            dialog.dismiss()
            replaceFragment(MapsFragment(), this)
        }
        dialog.show()
    }

    private fun initRecyclerView(){
        usersBindig.recyclerUsers.layoutManager = LinearLayoutManager(this)
        usersBindig.recyclerUsers.adapter = UserAdapter(UserProvider.userList,UserProvider.userList.toTypedArray())
    }




}

