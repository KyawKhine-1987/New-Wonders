package com.android.freelance.wonders

import android.app.Application
import com.android.freelance.wonders.data.db.WondersDatabase
import com.android.freelance.wonders.data.network.ApiWonders
import com.android.freelance.wonders.data.network.NetworkConnectionInterceptor
import com.android.freelance.wonders.data.repository.WondersRepository
import com.android.freelance.wonders.ui.viewmodel.WondersViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiWonders(instance()) }
        bind() from singleton { WondersDatabase(instance()) }
        bind() from singleton { WondersRepository(instance(), instance(), instance()) }
        bind() from provider { WondersViewModelFactory(instance()) }
    }
}