package ru.mazer.foodies.api_tests

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.Tag
import ru.mazer.foodies.domain.remote.RemoteApi
import javax.inject.Inject

@HiltAndroidTest
class ApiTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var api: RemoteApi

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun test_api_get_tags_correct() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(
                "[{ \"id\": 1,\"name\": \"Новинка\" }]"
            )
        )

        val tags = api.getTags().execute().body()

        assertEquals(
            listOf(Tag(id = 1, name = "Новинка")),
            tags
        )
    }

    @Test
    fun test_api_get_categories_correct() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(
                "[{ \"id\": 676153,\"name\": \"Горячие блюда\" }]"
            )
        )

        val categories = api.getCategories().execute().body()

        assertEquals(
            listOf(Category(id = 676153, name = "Горячие блюда")),
            categories
        )
    }

    @Test
    fun test_api_get_dishes_correct() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(
                "[{\"id\": 1,\"category_id\": 676168,\"name\": \"Авокадо Кранч Маки 8шт\",\"description\": \"Ролл с нежным мясом камчатского краба, копченой курицей и авокадо. Украшается соусом \\\"Унаги\\\" и легким майонезом  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе\",\"image\": \"1.jpg\",\"price_current\": 47000,\"price_old\": null,\"measure\": 250,\"measure_unit\": \"г\",\"energy_per_100_grams\": 307.8,\"proteins_per_100_grams\": 6.1,\"fats_per_100_grams\": 11.4,\"carbohydrates_per_100_grams\": 45.1,\"tag_ids\": [3]}]"
            )
        )

        val dishes = api.getProducts().execute().body()

        assertEquals(
            listOf(
                Dish(
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
            ),
            dishes
        )
    }

}