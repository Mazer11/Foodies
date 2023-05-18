package ru.mazer.foodies.api_tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import junit.framework.Assert.assertEquals
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.Tag
import ru.mazer.foodies.domain.remote.RemoteApi
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class ApiTest {

    private lateinit var mockWebServer: MockWebServer
    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()
    private val moshi = Moshi.Builder().build()
    private lateinit var api: RemoteApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
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
        mockWebServer.start()
        val baseUrl = mockWebServer.url("/")

        api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RemoteApi::class.java)

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
        mockWebServer.start()
        val baseUrl = mockWebServer.url("/")

        api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RemoteApi::class.java)

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
        mockWebServer.start()
        val baseUrl = mockWebServer.url("/")

        api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RemoteApi::class.java)

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




























