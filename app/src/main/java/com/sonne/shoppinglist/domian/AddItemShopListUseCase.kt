package com.sonne.shoppinglist.domian

class AddItemShopListUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addItemShopList(shopItem: ShopItem) {
        shopListRepository.addItemShopList(shopItem)
    }
}