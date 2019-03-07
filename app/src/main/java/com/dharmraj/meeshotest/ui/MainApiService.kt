package com.dharmraj.meeshotest.ui

import android.app.Application
import com.dharmraj.meeshotest.network.ApiService
import com.dharmraj.meeshotest.network.ServiceAbstract
import javax.inject.Inject

class MainApiService @Inject constructor(context: Application, val apiService: ApiService) : ServiceAbstract(context) {

}