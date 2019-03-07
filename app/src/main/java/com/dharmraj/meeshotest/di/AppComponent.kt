package com.dharmraj.meeshotest.di

import android.app.Application
import com.dharmraj.meeshotest.MeeshoTestApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        AndroidInjectionModule::class,
        ViewModelModule::class,
        ActivityBuilder::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    override fun inject(instance: DaggerApplication)

    fun inject(app: MeeshoTestApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}