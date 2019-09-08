package com.android.freelance.wonders.ui.activities.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.freelance.wonders.R
import com.android.freelance.wonders.data.db.entity.Wonders
import com.android.freelance.wonders.ui.activities.detail.WondersDetailActivity
import com.android.freelance.wonders.ui.adapter.WondersAdapter
import com.android.freelance.wonders.ui.viewmodel.WondersViewModel
import com.android.freelance.wonders.ui.viewmodel.WondersViewModelFactory
import kotlinx.android.synthetic.main.activity_wonderslist.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class WondersListActivity : AppCompatActivity(), WondersAdapter.ListItemClickListener, KodeinAware {

    private val LOG_TAG = WondersListActivity::class.java.name

    override val kodein by kodein()
    private val factory: WondersViewModelFactory by instance()
    private lateinit var viewModel: WondersViewModel
    //var hasInternet = false
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(LOG_TAG, "TEST: onCreate() is called...")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wonderslist)

        viewModel = ViewModelProviders.of(this, factory).get(WondersViewModel::class.java)

        // ProgressBar
        pbLoadingIndicator.visibility = View.VISIBLE

        bindUI()

        offlineData()
    }

    private fun bindUI() {
        Log.i(LOG_TAG, "TEST: bindUI() is called...")

        pbLoadingIndicator.visibility = View.GONE
        viewModel.callWebService
    }

    private fun offlineData() {
        Log.i(LOG_TAG, "TEST: offlineData() is called...")

        pbLoadingIndicator.visibility = View.GONE
        viewModel.getAllWonders.observe(this, Observer { wonders ->
            wonders?.let { refreshUIWith(wonders) }
        })
    }

    private fun refreshUIWith(wonders: List<Wonders>) {
        Log.i(LOG_TAG, "TEST: refreshUIWith() is called...")

        // try to touch View of UI thread
        this@WondersListActivity.runOnUiThread(java.lang.Runnable {
            val wondersList = rvWondersList
            val layoutManager = LinearLayoutManager(this)
            wondersList.layoutManager = layoutManager
            wondersList.hasFixedSize()
            wondersList.addItemDecoration(
                DividerItemDecoration(
                    this,
                    DividerItemDecoration.VERTICAL
                )
            )
            val adapter =
                WondersAdapter(this@WondersListActivity, applicationContext, wonders)
            wondersList.adapter = adapter
        })
    }

    override fun onListItemClick(position: Int, wondersEntity: List<Wonders>) {
        Log.i(LOG_TAG, "TEST: onListItemClick() called...")

        /*val intent = Intent(applicationContext, WondersDetailActivity::class.java)*/
        val intent = Intent(applicationContext, WondersDetailActivity::class.java)
        intent.putExtra("image", wondersEntity.get(position).image)
        intent.putExtra("title", wondersEntity.get(position).location)
        intent.putExtra("desp", wondersEntity.get(position).description)
        startActivity(intent)
    }
}
