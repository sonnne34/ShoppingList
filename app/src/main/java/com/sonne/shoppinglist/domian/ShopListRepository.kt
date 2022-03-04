package com.sonne.shoppinglist.domian

import androidx.lifecycle.LiveData

interface ShopListRepository {

    suspend fun addItemShopList(shopItem: ShopItem)

    suspend fun delItemShopList(shopItem: ShopItem)

    suspend fun editItemShopList(shopItem: ShopItem)

    suspend fun getItemShopList(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}