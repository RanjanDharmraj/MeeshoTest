package com.dharmraj.meeshotest.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.dharmraj.meeshotest.R
import com.dharmraj.meeshotest.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        AndroidInjection.inject(this)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                this.lifecycleOwner = this@MainActivity
                this.viewmodel = mainViewModel
            }

        mainViewModel.navigator.observe(this, Observer {
            it?.run {

            }
        })

        mainViewModel.snackbarText.observe(this, Observer {
            it?.run {
                showSnackBar(it)
            }
        })
    }

    fun showSnackBar(message: String) {
        val rootView = window.decorView.findViewById<View>(android.R.id.content)
        Snackbar
            .make(rootView, message, Snackbar.LENGTH_SHORT)
            .show()
    }
}
