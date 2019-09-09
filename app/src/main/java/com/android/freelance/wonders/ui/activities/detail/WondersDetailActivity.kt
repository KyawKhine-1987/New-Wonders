package com.android.freelance.wonders.ui.activities.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.android.freelance.wonders.R
import com.android.freelance.wonders.ui.viewmodel.WondersViewModel
import com.android.freelance.wonders.ui.viewmodel.WondersViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_wondersdetail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class WondersDetailActivity : AppCompatActivity() , KodeinAware {

    private val LOG_TAG = WondersDetailActivity::class.java.name
    override val kodein by kodein()
    private val factory: WondersViewModelFactory by instance()
    private lateinit var viewModel: WondersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wondersdetail)

        viewModel = ViewModelProviders.of(this, factory).get(WondersViewModel::class.java)

        relevantDataBindingInUI()
    }

    private fun relevantDataBindingInUI() {
        Log.i(LOG_TAG, "TEST: relevantDataBindingInUI() is called...")

        val wondersDetailImage: ImageView = this.findViewById(R.id.ivDetailWonders)
        val wondersDetailTitle: TextView = this.findViewById(R.id.tvDetailLocation)
        val wondersDetailDesp: TextView = this.findViewById(R.id.tvDetailDescription)

        val imageData = intent.getStringExtra("image")
        Glide.with(this).load(imageData).into(wondersDetailImage)

        wondersDetailTitle.text = intent.getStringExtra("title")
        wondersDetailDesp.text = intent.getStringExtra("desp")

         /*Glide.with(this).load(viewModel.detailImage).into(ivDetailWonders)
         tvDetailLocation.text = viewModel.detailLocation
         tvDetailDescription.text = viewModel.detailDescription*/
    }
}
