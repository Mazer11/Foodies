package ru.mazer.foodies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.mazer.foodies.domain.models.CartItem
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.Tag
import ru.mazer.foodies.domain.usecases.complex.RemoteUseCases
import javax.inject.Inject

/**
 * The main ViewModel class for this application*/
@HiltViewModel
class MainViewModel @Inject constructor(
    remoteUseCases: RemoteUseCases,
) : ViewModel() {

    //Exist dishes
    private val _dishList: MutableLiveData<List<Dish>> by lazy {
        MutableLiveData<List<Dish>>(listOf())
    }
    val dishList: LiveData<List<Dish>> = _dishList

    //The result of searching in SearchScreen
    private val _searchList: MutableLiveData<List<Dish>> by lazy {
        MutableLiveData<List<Dish>>(listOf())
    }
    val searchList: LiveData<List<Dish>> = _searchList

    //The dishes for catalog screen. It could be filtered by tags in bottomSheet
    private val _filteredDishList: MutableLiveData<List<Dish>> by lazy {
        MutableLiveData<List<Dish>>(listOf())
    }
    val filteredDishList: LiveData<List<Dish>> = _filteredDishList

    //Current cart items
    private val _cartList: MutableLiveData<List<CartItem>> by lazy {
        MutableLiveData<List<CartItem>>(listOf())
    }
    val cartList: LiveData<List<CartItem>> = _cartList

    //Exist Tags(filters)
    private val _filtersList: MutableLiveData<List<Tag>> by lazy {
        MutableLiveData<List<Tag>>(listOf())
    }
    val filtersList: LiveData<List<Tag>> = _filtersList

    //Tags that tagged by user
    private val _checkedTagsList: MutableLiveData<List<Int>> by lazy {
        MutableLiveData<List<Int>>(listOf())
    }
    val checkedTagsList: LiveData<List<Int>> = _checkedTagsList

    //State of discount tag
    private val _discountOnly: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val discountOnly: LiveData<Boolean> = _discountOnly

    //Current search text
    private val _searchText: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val searchText: LiveData<String> = _searchText

    //Exist categories
    private val _categories: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>(listOf())
    }
    val categories: LiveData<List<Category>> = _categories

    //Current cart price
    private val _currentPrice: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }
    val currentPrice: LiveData<Int> = _currentPrice

    //Current Category
    private val _currentCategory: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }
    val currentCategory: LiveData<Int> = _currentCategory

    //All Dishes of current category
    private val _currentCategoryDishes: MutableLiveData<List<Dish>> by lazy {
        MutableLiveData<List<Dish>>(listOf())
    }

    //INITIALISATION
    init {
        _categories.value = remoteUseCases.getCategoriesUseCase.invoke()
        _filtersList.value = remoteUseCases.getTagsUseCase.invoke()
        _dishList.value = remoteUseCases.getProductsUseCase.invoke()
        _filteredDishList.value = _dishList.value
        _filteredDishList.value = _dishList.value
        onCategoryClick(_categories.value!!.first().id)
    }

    /**
     * Adds provided dish to the cart. Also increase currentPrice*/
    fun addToCart(dish: Dish) {
        Log.e("MainViewModel", "Inside addToCart")
        if (_cartList.value != null) {
            val oldCartItem = _cartList.value!!.firstOrNull { it.id == dish.id }
            Log.e("MainViewModel", "Old id = ${oldCartItem?.id} count = ${oldCartItem?.count}")
            if (oldCartItem != null) {
                val oldCartItemIndex = _cartList.value!!.indexOf(oldCartItem)
                Log.e("MainViewModel", "Inside not null")
                _currentPrice.value = _currentPrice.value!! + dish.price_current
                _cartList.value = buildList {
                    addAll(_cartList.value!!.subList(0, oldCartItemIndex))
                    add(oldCartItem.copy(count = oldCartItem.count + 1))
                    addAll(_cartList.value!!.subList(oldCartItemIndex+1, _cartList.value!!.size))
                }
            } else {
                Log.e("MainViewModel", "Inside null")
                _currentPrice.value = _currentPrice.value!! + dish.price_current
                _cartList.value = buildList {
                    addAll(_cartList.value!!)
                    add(CartItem(dish.id, 1))
                }
            }
        }
    }

    /**
     * Removes provided dish from the cart. Also decrease currentPrice*/
    fun removeFromCart(dish: Dish) {
        if (_cartList.value != null) {
            val oldCartItem = _cartList.value!!.firstOrNull { it.id == dish.id }
            if (oldCartItem != null) {
                _currentPrice.value = _currentPrice.value!! - dish.price_current
                if (oldCartItem.count > 1) {
                    val oldCartItemIndex = _cartList.value!!.indexOf(oldCartItem)
                    _cartList.value = buildList {
                        addAll(_cartList.value!!.subList(0, oldCartItemIndex))
                        add(oldCartItem.copy(count = oldCartItem.count - 1))
                        addAll(_cartList.value!!.subList(oldCartItemIndex+1, _cartList.value!!.size))
                    }
                }
                else {
                    _cartList.value = _cartList.value!!.filter { it != oldCartItem }
                }
            } else {
                return
            }
        }
    }

    /**
     * Checks/Unchecks tag with provided tagId*/
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

    /**
     * [checkTag] for discount tag*/
    fun checkDiscountTag(
        newValue: Boolean
    ) {
        _discountOnly.value = newValue
    }

    /**
     * Used as callback for onSearchTextChanged in SearchScreen*/
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

    /**
     * Used as callback for click event in catalog screen for categories*/
    fun onCategoryClick(tagId: Int) {
        if (_currentCategory.value == tagId)
            return
        _currentCategory.value = tagId
        _currentCategoryDishes.value = _dishList.value!!.filter { it.category_id == tagId }
        _filteredDishList.value = _currentCategoryDishes.value
        if (!_checkedTagsList.value.isNullOrEmpty()) {
            onFiltersChanged()
        }
    }

    /**
     * Used as callback for click event in catalog screen for tags(filters)*/
    fun onFiltersChanged() {
        if (_checkedTagsList.value.isNullOrEmpty()) {
            if (_discountOnly.value == true)
                _filteredDishList.value =
                    _currentCategoryDishes.value!!.filter { it.price_old != null }
            else
                _filteredDishList.value = _currentCategoryDishes.value
            return
        }
        val filteredDish = _currentCategoryDishes.value!!.filter {
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