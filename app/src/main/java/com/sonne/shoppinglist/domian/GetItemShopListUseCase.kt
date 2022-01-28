package com.sonne.shoppinglist.domian

class GetItemShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getItemShopList(shopItemId: Int): ShopItem {
        return shopListRepository.getItemShopList(shopItemId)
    }
}