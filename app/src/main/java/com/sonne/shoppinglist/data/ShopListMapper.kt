package com.sonne.shoppinglist.data

import com.sonne.shoppinglist.domian.ShopItem

class ShopListMapper {

    fun mapEntityToDBModel(shopItem: ShopItem) = ShopItemDBModel(
        shopItem.id,
        shopItem.name,
        shopItem.count,
        shopItem.enabled
    )

    fun mapDBModelToEntity(shopItemDBModel: ShopItemDBModel) = ShopItem(
        shopItemDBModel.name,
        shopItemDBModel.count,
        shopItemDBModel.enabled,
        shopItemDBModel.id
    )

    fun mapListDBModelToListEntity(list: List<ShopItemDBModel>) = list.map {
        mapDBModelToEntity(it)
    }
}