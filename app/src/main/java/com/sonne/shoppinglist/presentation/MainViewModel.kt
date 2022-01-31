package com.sonne.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonne.shoppinglist.data.ShopListRepositoryImpl
import com.sonne.shoppinglist.domian.DelItemShopListUseCase
import com.sonne.shoppinglist.domian.EditItemShopListUseCase
import com.sonne.shoppinglist.domian.GetShopListUseCase
import com.sonne.shoppinglist.domian.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val delItemShopListUseCase = DelItemShopListUseCase(repository)
    private val editShopListUseCase = EditItemShopListUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun delItemShopList(shopItem: ShopItem){
        delItemShopListUseCase.delItemShopList(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopListUseCase.editItemShopList(newItem)
    }
}