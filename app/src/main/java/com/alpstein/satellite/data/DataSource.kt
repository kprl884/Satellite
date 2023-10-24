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


class DataSource(

) : LocalRepository {
    private val dataSource = R.raw.satellite

    override suspend fun getAllItems(): Flow<List<Satellite>> {
        return flow {
            try {
                val response = dataSource.
                emit(response)
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

    private fun processJSONData(json: String) {
        try {
            val jsonObject = JSONObject(json)
            val jsonArray = jsonObject.getJSONArray("your_array_name")
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                // JSON verilerini alın ve kullanın
                val id = item.getInt("id")
                val costPerLaunch = item.getInt("cost_per_launch")
                val firstFlight = item.getString("first_flight")
                val height = item.getInt("height")
                val mass = item.optInt("mass", -1) // Eksik bir anahtar varsa varsayılan bir değer kullanabilirsiniz.

                // JSON verileriyle istediğiniz işlemi gerçekleştirin
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val json = loadJSONFromResources(this, R.raw.your_json_file)
    if (json != null) {
        processJSONData(json)
    }
}