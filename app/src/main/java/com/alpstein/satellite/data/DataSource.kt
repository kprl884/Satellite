package com.alpstein.satellite.data

import android.content.Context
import com.alpstein.satellite.R
import com.alpstein.satellite.domain.entity.Satellite
import com.alpstein.satellite.domain.repository.local.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import java.io.IOException

import javax.inject.Inject

class DataSource @Inject constructor(private val context: Context) :  LocalRepository {

    override suspend fun getAllItems(): Flow<List<Satellite>> {
        return flow {
            try {
                val json = loadJSONFromResources(context, R.raw.satellite)
                val listData = arrayListOf<Satellite>()
                if (json != null) {
                    val jsonObject = JSONObject(json)
                    val jsonArray = jsonObject.getJSONArray("your_array_name")
                    for (i in 0 until jsonArray.length()) {
                        val item = jsonArray.getJSONObject(i)
                        val id = item.getInt("id")
                        val active = item.getBoolean("active")
                        val name = item.getString("name")
                        val costPerLaunch = item.getInt("cost_per_launch")
                        val firstFlight = item.getString("first_flight")
                        val height = item.getInt("height")
                        val position = item.getJSONArray("position")
                        val mass = item.optInt("mass", -1)
                       listData.add(
                           Satellite(
                               id, active, name, costPerLaunch, firstFlight, height, mass
                           )
                       )
                    }
                    emit(listData)
                }
            }catch (e: Exception){
                println(e.message)
            }
        }.flowOn(Dispatchers.IO)
    }
    override suspend fun getItemByItem(id: Int): Flow<Satellite> {
        return flow {
            try {
                val json = loadJSONFromResources(context, R.raw.satellite)
                if (json != null) {
                    val jsonObject = JSONObject(json)
                    val jsonArray = jsonObject.getJSONArray("your_array_name")
                    for (i in 0 until jsonArray.length()) {
                        val item = jsonArray.getJSONObject(i)
                        val id = item.getInt("id")
                        val active = item.getBoolean("active")
                        val name = item.getString("name")
                        val costPerLaunch = item.getInt("cost_per_launch")
                        val firstFlight = item.getString("first_flight")
                        val height = item.getInt("height")
                        val position = item.getJSONArray("position")
                        val mass = item.optInt("mass", -1)
                    }
                    emit(Satellite(id = id))
                }
            }catch (e: Exception){
                println(e.message)
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun loadJSONFromResources(context: Context, resourceId: Int): String? {
        var json: String? = null
        try {
            val inputStream = context.resources.openRawResource(resourceId)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }
}