package com.sonne.shoppinglist.domian

class AddItemShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun addItemShopList(shopItem: ShopItem) {
        shopListRepository.addItemShopList(shopItem)
    }
}