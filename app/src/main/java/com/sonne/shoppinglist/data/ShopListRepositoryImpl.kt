package com.sonne.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sonne.shoppinglist.domian.ShopItem
import com.sonne.shoppinglist.domian.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {

    private val shopListDAO = AppDB.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override suspend fun addItemShopList(shopItem: ShopItem) {
        shopListDAO.addShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override suspend fun delItemShopList(shopItem: ShopItem) {
        shopListDAO.deleteShopItem(shopItem.id)
    }

    override suspend fun editItemShopList(shopItem: ShopItem) {
        shopListDAO.addShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override suspend fun getItemShopList(shopItemId: Int): ShopItem {
        val dbModel = shopListDAO.getShopItem(shopItemId)
        return mapper.mapDBModelToEntity(dbModel)
    }

    //    override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
//         addSource(shopListDAO.getShopList()) {
//             value = mapper.mapListDBModelToListEntity(it)
//         }
//    }
//    MediatorLiveData нужен для перехвата LiveData и работы с ним.
//    Так как мы лишь преобразовывваем объект из исходной LiveData в какой то другой тип,
//    то можем просто использовать Transformations (import androidx.lifecycle.Transformations)
    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        shopListDAO.getShopList()
    ) {
        mapper.mapListDBModelToListEntity(it)
    }

}