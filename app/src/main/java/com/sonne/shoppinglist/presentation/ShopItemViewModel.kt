package com.sonne.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.sonne.shoppinglist.data.ShopListRepositoryImpl
import com.sonne.shoppinglist.domian.AddItemShopListUseCase
import com.sonne.shoppinglist.domian.EditItemShopListUseCase
import com.sonne.shoppinglist.domian.GetItemShopListUseCase
import com.sonne.shoppinglist.domian.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getItemShopListUseCase = GetItemShopListUseCase(repository)
    private val addItemShopListUseCase = AddItemShopListUseCase(repository)
    private val editItemShopListUseCase = EditItemShopListUseCase(repository)

    fun getItem(shopItemId: Int) {
        val item = getItemShopListUseCase.getItemShopList(shopItemId)
    }

    fun addItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if (fieldValid) {
            val shopItem = ShopItem(name, count, true)
            addItemShopListUseCase.addItemShopList(shopItem)
        }
    }

    fun editItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if (fieldValid) {
            val shopItem = ShopItem(name, count, true)
            editItemShopListUseCase.editItemShopList(shopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            //TODO: show error input name
            result = false
        }
        if (count <= 0) {
            //TODO: show error input count
            result = false
        }
        return result
    }

}