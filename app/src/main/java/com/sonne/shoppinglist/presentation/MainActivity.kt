package com.sonne.shoppinglist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sonne.shoppinglist.R
import com.sonne.shoppinglist.domian.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llShopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llShopList = findViewById(R.id.ll_shop)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            showList(it)
        }
    }

    private fun showList(list: List<ShopItem>) {
        llShopList.removeAllViews()
        for (item in list) {
            val linearlayout =
                if (item.enabled) {
                    R.layout.item_shop_enabled
                } else {
                    R.layout.item_shop_disabled
                }

            val view = LayoutInflater.from(this).inflate(linearlayout, llShopList, false)
            val txtName = findViewById<TextView>(R.id.tv_name)
            val txtCount = findViewById<TextView>(R.id.tv_count)

//            txtName.text = item.name
//            txtCount.text = item.count.toString()

            view.setOnLongClickListener {
                viewModel.changeEnableState(item)
                true
            }

            llShopList.addView(view)
        }
    }
}


