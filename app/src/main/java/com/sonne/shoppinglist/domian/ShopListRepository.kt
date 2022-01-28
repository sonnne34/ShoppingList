package com.sonne.shoppinglist.domian

interface ShopListRepository {

    fun addItemShopList(shopItem: ShopItem)

    fun delItemShopList(shopItem: ShopItem)

    fun editItemShopList(shopItem: ShopItem)

    fun getItemShopList(shopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>
}