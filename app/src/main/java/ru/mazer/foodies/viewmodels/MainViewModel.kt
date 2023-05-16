package ru.mazer.foodies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.mazer.foodies.domain.models.Dish
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _dishList:MutableLiveData<List<Dish>> by lazy {
        MutableLiveData<List<Dish>>()
    }
    val dishList: LiveData<List<Dish>> = _dishList

    init {
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
                id = 14,
                category_id = 676168,
                name = "Радуга 8шт",
                description = "Классический урамаки ролл из нежного лосося, тунца, сливочного сыра и огурца  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
                image = "1.jpg",
                price_current = 51000,
                price_old = null,
                measure = 250,
                measure_unit = "г",
                energy_per_100_grams = 241.5,
                proteins_per_100_grams = 10.1,
                fats_per_100_grams = 3.4,
                carbohydrates_per_100_grams = 42.6,
                tag_ids = listOf(3)
            )
        )
    }

}