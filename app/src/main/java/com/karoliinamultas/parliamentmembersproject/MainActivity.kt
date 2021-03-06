package com.karoliinamultas.parliamentmembersproject

//date: 6.3.2022
//name: Karoliina Multas
//student id: 2101425
//MainActivity hosts navcontroller and adds politicians from the API to the database immediately when the app is opened.

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.karoliinamultas.parliamentmembersproject.data.PoliticianApi
import com.karoliinamultas.parliamentmembersproject.data.PoliticianDB
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addPoliticians()
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        supportActionBar?.title = "Members of Parliament"
    }

    override fun onSupportNavigateUp(): Boolean {
            val navController = this.findNavController(R.id.myNavHostFragment)
            return navController.navigateUp()
        }

    private fun addPoliticians() {
            GlobalScope.launch(Dispatchers.IO,
                CoroutineStart.DEFAULT) {
                try {
                    val listResult = PoliticianApi.retrofitService.getPoliticianList()
                    listResult.forEach {
                        PoliticianDB.getDatabase(applicationContext).politicianDao().addPolitician(it)
                    }
                } catch (e: Exception) {
                    println("Failure: ${e.message}")
                }
            }
        }
    }


