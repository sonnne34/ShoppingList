package com.sonne.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sonne.shoppinglist.domian.ShopItem
import com.sonne.shoppinglist.domian.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addItemShopList(item)
        }
    }

    override fun addItemShopList(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun delItemShopList(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
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
    //  и просто обозначить значение nonnull
    override fun getItemShopList(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }


    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}