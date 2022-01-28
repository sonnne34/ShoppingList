package com.sonne.shoppinglist.domian

class EditItemShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun editItemShopList(shopItem: ShopItem) {
        shopListRepository.editItemShopList(shopItem)
    }
}