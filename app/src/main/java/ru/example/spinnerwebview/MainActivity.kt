package ru.example.spinnerwebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.databinding.DataBindingUtil
import ru.example.spinnerwebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding  = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        var languages = resources.getStringArray(R.array.languages)
        val adapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, languages)
        binding.Sp.adapter = adapter
        binding.Sp.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val  url = "https://ru.wikipedia.org/wiki/${languages[position]}"
              binding.Ww.loadUrl(url)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.Ww.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
    }
}