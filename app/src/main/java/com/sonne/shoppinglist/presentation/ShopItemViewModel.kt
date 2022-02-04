package com.sonne.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.sonne.shoppinglist.data.ShopListRepositoryImpl
import com.sonne.shoppinglist.domian.AddItemShopListUseCase
import com.sonne.shoppinglist.domian.EditItemShopListUseCase
import com.sonne.shoppinglist.domian.GetItemShopListUseCase
import com.sonne.shoppinglist.domian.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getItemShopListUseCase = GetItemShopListUseCase(repository)
    private val addItemShopListUseCase = AddItemShopListUseCase(repository)
    private val editItemShopListUseCase = EditItemShopListUseCase(repository)

    fun getItem(shopItemId: Int){
        val item = getItemShopListUseCase.getItemShopList(shopItemId)
    }

    fun addItem(shopItem: ShopItem){
        addItemShopListUseCase.addItemShopList(shopItem)
    }

    fun editItem(shopItem: ShopItem){
        editItemShopListUseCase.editItemShopList(shopItem)
    }

}