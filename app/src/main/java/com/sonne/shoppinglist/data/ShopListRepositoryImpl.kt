package com.sonne.shoppinglist.data

import com.sonne.shoppinglist.domian.ShopItem
import com.sonne.shoppinglist.domian.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0..10) {
            val item = ShopItem("Name $i", i, true)
            addItemShopList(item)
        }
    }

    override fun addItemShopList(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun delItemShopList(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editItemShopList(shopItem: ShopItem) {
        val oldElement = getItemShopList(shopItem.id)
        shopList.remove(oldElement)
        addItemShopList(shopItem)
    }

    //  чтобы не забыть, мне напоминание:
    //  в случае, если объект не найден, приложение упадёт с соответсвующим собщением.
    //  если ситуация, когда объект не найден станет для меня нормальной, можно будет убрать
    //  ?: throw RuntimeException("Element with id $shopItemId not found")
    //  и просто обозначить значение nonull
    override fun getItemShopList(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }


    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}