package ru.mazer.foodies.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.mazer.foodies.domain.models.Dish

@RunWith(JUnit4::class)
internal class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var vm: TestMainViewModel

    private val dish1 = Dish(
        id = 1,
        category_id = 676168,
        name = "Авокадо Кранч Маки 8шт",
        description = "Ролл с нежным мясом камчатского краба, копченой курицей и авокадо. Украшается соусом \"Унаги\" и легким майонезом  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
        image = "1.jpg",
        price_current = 47000,
        price_old = null,
        measure = 250,
        measure_unit = "г",
        energy_per_100_grams = 307.8,
        proteins_per_100_grams = 6.1,
        fats_per_100_grams = 11.4,
        carbohydrates_per_100_grams = 45.1,
        tag_ids = listOf(3)
    )
    private val dish2 = Dish(
        id = 2,
        category_id = 676168,
        name = "Авокадо Кранч Маки 8шт",
        description = "Ролл с нежным мясом камчатского краба, копченой курицей и авокадо. Украшается соусом \"Унаги\" и легким майонезом  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
        image = "1.jpg",
        price_current = 47000,
        price_old = null,
        measure = 250,
        measure_unit = "г",
        energy_per_100_grams = 307.8,
        proteins_per_100_grams = 6.1,
        fats_per_100_grams = 11.4,
        carbohydrates_per_100_grams = 45.1,
        tag_ids = listOf(5)
    )

    @Before
    fun setUp(){
        vm = TestMainViewModel()
    }

    @Test
    fun `addToCart 2 dishes returns cart list with size 2`() {
        vm.addToCart(dish1)
        vm.addToCart(dish2)
        assert(vm.cartList.value?.size == 2)
    }

    @Test
    fun `Adds dish and then removes that dish, don't change the cart size`() {
        vm.addToCart(dish1)
        vm.removeFromCart(dish1)
        assert(vm.cartList.value?.isEmpty() == true)
    }

    @Test
    fun `checkTag with true and id = 1 adds to list`() {
        vm.checkTag(true, 1)
        assert(vm.checkedTagsList.value?.contains(1) == true)
    }

    @Test
    fun checkDiscountTag() {
        vm.checkDiscountTag(true)
        assert(vm.discountOnly.value == true)
    }

    @Test
    fun onSearchTextChanged() {
        val newText = "Test text"
        vm.onSearchTextChanged(newText)
        assert(vm.searchText.value == newText)
    }

    @Test
    fun onCategoryClick() {
        val newCategory = 56232
        vm.onCategoryClick(newCategory)
        assert(vm.currentCategory.value == newCategory)
    }
}