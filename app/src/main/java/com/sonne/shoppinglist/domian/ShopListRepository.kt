package com.sonne.shoppinglist.domian

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addItemShopList(shopItem: ShopItem)

    fun delItemShopList(shopItem: ShopItem)

    fun editItemShopList(shopItem: ShopItem)

    fun getItemShopList(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}