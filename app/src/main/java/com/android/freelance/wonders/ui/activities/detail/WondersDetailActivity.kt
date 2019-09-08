package com.android.freelance.wonders.ui.activities.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.android.freelance.wonders.R
import com.bumptech.glide.Glide

class WondersDetailActivity : AppCompatActivity() {

    private val LOG_TAG = WondersDetailActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wondersdetail)

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
        /* Glide.with(this).load(viewModel.detailImage).into(ivDetailWonders)
         tvDetailLocation.text = viewModel.detailLocation
         tvDetailDescription.text = viewModel.detailDescription*/
    }
}
