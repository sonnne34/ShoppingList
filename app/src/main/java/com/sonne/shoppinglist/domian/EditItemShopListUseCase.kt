package com.sonne.shoppinglist.domian

class EditItemShopListUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun editItemShopList(shopItem: ShopItem) {
        shopListRepository.editItemShopList(shopItem)
    }
}