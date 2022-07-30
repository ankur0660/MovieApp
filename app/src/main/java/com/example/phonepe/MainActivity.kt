package com.example.phonepe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.phonepe.fragments.FeedFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.root, FeedFragment.newInstance(), FeedFragment.TAG)
        transaction.commit()
    }
}