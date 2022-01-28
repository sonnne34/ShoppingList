package com.sonne.shoppinglist.domian

class DelItemShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun delItemShopList(shopItem: ShopItem) {
        shopListRepository.delItemShopList(shopItem)
    }
}