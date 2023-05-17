package ru.mazer.foodies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.mazer.foodies.domain.models.CartItem
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.Tag
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _dishList: MutableLiveData<List<Dish>> by lazy {
        MutableLiveData<List<Dish>>(listOf())
    }
    val dishList: LiveData<List<Dish>> = _dishList

    private val _searchList: MutableLiveData<List<Dish>> by lazy {
        MutableLiveData<List<Dish>>(listOf())
    }
    val searchList: LiveData<List<Dish>> = _searchList

    private val _filteredDishList: MutableLiveData<List<Dish>> by lazy {
        MutableLiveData<List<Dish>>(listOf())
    }
    val filteredDishList: LiveData<List<Dish>> = _filteredDishList

    private val _cartList: MutableLiveData<List<CartItem>> by lazy {
        MutableLiveData<List<CartItem>>(listOf())
    }
    val cartList: LiveData<List<CartItem>> = _cartList

    private val _filtersList: MutableLiveData<List<Tag>> by lazy {
        MutableLiveData<List<Tag>>(listOf())
    }
    val filtersList: LiveData<List<Tag>> = _filtersList

    private val _checkedTagsList: MutableLiveData<List<Int>> by lazy {
        MutableLiveData<List<Int>>(listOf())
    }
    val checkedTagsList: LiveData<List<Int>> = _checkedTagsList

    private val _discountOnly: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val discountOnly: LiveData<Boolean> = _discountOnly

    private val _searchText: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val searchText: LiveData<String> = _searchText

    init {
        _filtersList.value = listOf(
            Tag(
                id = 1,
                name = "Новинка"
            ),
            Tag(
                id = 2,
                name = "Вегетарианское блюдо"
            ),
            Tag(
                id = 3,
                name = "Хит!"
            ),
            Tag(
                id = 4,
                name = "Острое"
            ),
            Tag(
                id = 5,
                name = "Экспресс-меню"
            )
        )
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
                tag_ids = listOf(5)
            ),
            Dish(
                id = 21,
                category_id = 676154,
                name = "Спайси суши осьминог",
                description = "Острые суши с осьминогом  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
                image = "1.jpg",
                price_current = 16000,
                price_old = 20000,
                measure = 30,
                measure_unit = "г",
                energy_per_100_grams = 265.2,
                proteins_per_100_grams = 10.9,
                fats_per_100_grams = 7.4,
                carbohydrates_per_100_grams = 38.6,
                tag_ids = listOf(4)
            ),
            Dish(
                id = 12,
                category_id = 676168,
                name = "Филадельфия Кунжут 8шт",
                description = "Урамаки ролл в семенах кунжута с нежным лососем, сливочным сыром, огурцом и авокадо  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
                image = "1.jpg",
                price_current = 46000,
                price_old = null,
                measure = 225,
                measure_unit = "г",
                energy_per_100_grams = 297.7,
                proteins_per_100_grams = 8.6,
                fats_per_100_grams = 8.4,
                carbohydrates_per_100_grams = 46.9,
                tag_ids = listOf(3)
            ),
            Dish(
                id = 27377,
                category_id = 676153,
                name = "Тофу с овощами",
                description = "Кубики обжаренного тофу с гарниром из капусты пак-чой, стрелок фасоли, помидоров черри и сладкого перца под устричным соусом.",
                image = "1.jpg",
                price_current = 33000,
                price_old = null,
                measure = 250,
                measure_unit = "г",
                energy_per_100_grams = 146.3,
                proteins_per_100_grams = 3.5,
                fats_per_100_grams = 11.1,
                carbohydrates_per_100_grams = 8.8,
                tag_ids = listOf(2)
            ),
            Dish(
                id = 27842,
                category_id = 676153,
                name = " Баклажаны с грибами шиитаке",
                description = " Баклажаны с грибами шиитаке, обжаренные в соусе унаги ",
                image = "1.jpg",
                price_current = 31000,
                price_old = null,
                measure = 150,
                measure_unit = " г ",
                energy_per_100_grams = 263.7,
                proteins_per_100_grams = 4.9,
                fats_per_100_grams = 21.6,
                carbohydrates_per_100_grams = 11.2,
                tag_ids = listOf(2, 3)
            )
        )
        _filteredDishList.value = _dishList.value
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
                else {
                    _cartList.value = _cartList.value!!.filter { it != oldCartItem }
                }
            } else {
                return
            }
        }
    }

    fun checkTag(
        newValue: Boolean,
        tagId: Int
    ) {
        if (newValue) {
            _checkedTagsList.value = buildList {
                addAll(_checkedTagsList.value!!)
                add(tagId)
            }
        } else {
            _checkedTagsList.value = _checkedTagsList.value!!.filter { it != tagId }
        }
    }

    fun checkDiscountTag(
        newValue: Boolean
    ) {
        _discountOnly.value = newValue
    }

    fun onSearchTextChanged(newText: String) {
        _searchText.value = newText
        if (newText.isEmpty()) {
            _searchList.value = listOf()
            return
        }
        val dishesFromSearch = _dishList.value!!.filter {
            it.name.contains(newText, true) ||
                    it.description.contains(newText, true)
        }
        _searchList.value = dishesFromSearch
    }

    fun onFiltersChanged() {
        if (_checkedTagsList.value.isNullOrEmpty()) {
            if (_discountOnly.value == true)
                _filteredDishList.value = _dishList.value!!.filter { it.price_old != null }
            else
                _filteredDishList.value = _dishList.value
            return
        }
        val filteredDish = _dishList.value!!.filter {
            it.tag_ids.isNotEmpty()
        }.filter {
            it.tag_ids.containsAll(_checkedTagsList.value!!)
        }

        if (_discountOnly.value == true)
            _filteredDishList.value = filteredDish.filter { it.price_old != null }
        else
            _filteredDishList.value = filteredDish
    }

}


























