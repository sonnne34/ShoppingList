package com.sonne.shoppinglist.domian

data class ShopItem(
//    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean,
    //  пока нет реализации БД, использую данный id
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}


