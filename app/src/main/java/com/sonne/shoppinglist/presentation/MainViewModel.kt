package com.sonne.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sonne.shoppinglist.data.ShopListRepositoryImpl
import com.sonne.shoppinglist.domian.DelItemShopListUseCase
import com.sonne.shoppinglist.domian.EditItemShopListUseCase
import com.sonne.shoppinglist.domian.GetShopListUseCase
import com.sonne.shoppinglist.domian.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val delItemShopListUseCase = DelItemShopListUseCase(repository)
    private val editShopListUseCase = EditItemShopListUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun delItemShopList(shopItem: ShopItem) {
        viewModelScope.launch {
            delItemShopListUseCase.delItemShopList(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch {
            editShopListUseCase.editItemShopList(newItem)
        }
    }
}