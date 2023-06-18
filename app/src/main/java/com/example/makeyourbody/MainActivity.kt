package com.example.makeyourbody
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil
import com.example.makeyourbody.databinding.ActivityMainBinding
import com.example.makeyourbody.ui.theme.MakeYourBodyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
