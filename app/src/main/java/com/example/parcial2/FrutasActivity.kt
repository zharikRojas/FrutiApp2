package com.example.parcial2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.parcial2.databinding.ActivityFrutasBinding

class FrutasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFrutasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrutasBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}