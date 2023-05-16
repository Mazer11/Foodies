package ru.mazer.foodies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.mazer.foodies.domain.models.CartItem
import ru.mazer.foodies.domain.models.Dish
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _dishList: MutableLiveData<List<Dish>> by lazy {
        MutableLiveData<List<Dish>>(listOf())
    }
    val dishList: LiveData<List<Dish>> = _dishList

    private val _cartList: MutableLiveData<List<CartItem>> by lazy {
        MutableLiveData<List<CartItem>>(listOf())
    }
    val cartList: LiveData<List<CartItem>> = _cartList

    init {
        Log.e("MainViewModel", "INITIALISATION")
        _dishList.value = listOf(
            Dish(
                id = 9,
                category_id = 676168,
                name = "Митаки 8шт",
                description = "Традиционный ролл с огурцом и нежным сливочным сыром  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
                image = "1.jpg",
                price_current = 23000,
                price_old = 43511,
                measure = 150,
                measure_unit = "г",
                energy_per_100_grams = 302.0,
                proteins_per_100_grams = 6.1,
                fats_per_100_grams = 3.7,
                carbohydrates_per_100_grams = 61.1,
                tag_ids = listOf()
            ),
            Dish(
                id = 21,
                category_id = 676154,
                name = "Спайси суши осьминог",
                description = "Острые суши с осьминогом  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
                image = "1.jpg",
                price_current = 16000,
                price_old = null,
                measure = 30,
                measure_unit = "г",
                energy_per_100_grams = 265.2,
                proteins_per_100_grams = 10.9,
                fats_per_100_grams = 7.4,
                carbohydrates_per_100_grams = 38.6,
                tag_ids = listOf(4)
            ),
        )
    }

    fun addToCart(dish: Dish) {
        Log.e("MainViewModel", "Inside addToCart")
        if (_cartList.value != null) {
            val oldCartItem = _cartList.value!!.firstOrNull { it.id == dish.id }
            Log.e("MainViewModel", "Old id = ${oldCartItem?.id} count = ${oldCartItem?.count}")
            if (oldCartItem != null) {
                Log.e("MainViewModel", "Inside not null")
                _cartList.value = buildList {
                    addAll(_cartList.value!!.filter { it != oldCartItem })
                    add(CartItem(id = dish.id, count = oldCartItem.count + 1))
                }
            } else {
                Log.e("MainViewModel", "Inside null")
                _cartList.value = buildList {
                    addAll(_cartList.value!!)
                    add(CartItem(dish.id, 1))
                }
            }
        }
    }

    fun removeFromCart(dish: Dish) {
        if (_cartList.value != null) {
            val oldCartItem = _cartList.value!!.firstOrNull { it.id == dish.id }
            if (oldCartItem != null) {
                if (oldCartItem.count > 1)
                    _cartList.value = buildList {
                        addAll(_cartList.value!!.filter { it != oldCartItem })
                        add(CartItem(id = dish.id, count = oldCartItem.count - 1))
                    }
                else{
                    _cartList.value = _cartList.value!!.filter { it != oldCartItem }
                }
            } else {
                return
            }
        }
    }

}