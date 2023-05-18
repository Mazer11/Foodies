package ru.mazer.foodies.utils

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.Tag

object JsonUtil {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun getAssetDish(context: Context): List<Dish>? {
        val listType = Types.newParameterizedType(List::class.java, Dish::class.java)
        val adapter: JsonAdapter<List<Dish>> = moshi.adapter(listType)
        val file = "products.json"
        val myjson = context.assets.open(file).bufferedReader().use { it.readText() }

        return adapter.fromJson(myjson)
    }

    fun getAssetCategory(context: Context): List<Category>? {
        val listType = Types.newParameterizedType(List::class.java, Category::class.java)
        val adapter: JsonAdapter<List<Category>> = moshi.adapter(listType)
        val file = "categories.json"
        val myjson = context.assets.open(file).bufferedReader().use { it.readText() }

        return adapter.fromJson(myjson)
    }

    fun getAssetTag(context: Context): List<Tag>? {
        val listType = Types.newParameterizedType(List::class.java, Tag::class.java)
        val adapter: JsonAdapter<List<Tag>> = moshi.adapter(listType)
        val file = "tags.json"
        val myjson = context.assets.open(file).bufferedReader().use { it.readText() }

        return adapter.fromJson(myjson)
    }

}