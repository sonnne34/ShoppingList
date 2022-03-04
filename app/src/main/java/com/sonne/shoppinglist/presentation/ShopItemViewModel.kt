package com.sonne.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sonne.shoppinglist.data.ShopListRepositoryImpl
import com.sonne.shoppinglist.domian.AddItemShopListUseCase
import com.sonne.shoppinglist.domian.EditItemShopListUseCase
import com.sonne.shoppinglist.domian.GetItemShopListUseCase
import com.sonne.shoppinglist.domian.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getItemShopListUseCase = GetItemShopListUseCase(repository)
    private val addItemShopListUseCase = AddItemShopListUseCase(repository)
    private val editItemShopListUseCase = EditItemShopListUseCase(repository)

//    больше не нужен, т.к. мы используем viewModelScope а не CoroutineScope
//    private val scope = CoroutineScope(Dispatchers.Main)

    private var _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private var _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    fun getItem(shopItemId: Int) {
        viewModelScope.launch {
            val item = getItemShopListUseCase.getItemShopList(shopItemId)
            _shopItem.value = item
        }
    }

    private var _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun addItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if (fieldValid) {
            viewModelScope.launch {
                val shopItem = ShopItem(name, count, true)
                addItemShopListUseCase.addItemShopList(shopItem)
                finishWork()
            }
        }
    }

    fun editItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if (fieldValid) {
            _shopItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = name, count = count)
                    editItemShopListUseCase.editItemShopList(item)
                    finishWork()
                }
            }
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
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

//    больше не нужен, т.к. мы используем viewModelScope а не CoroutineScope:
//    override fun onCleared() {
//        super.onCleared()
//        scope.cancel()
//    }
}