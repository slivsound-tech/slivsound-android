package com.slivsound.feature.sound.share

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

object FileDownloader {

    suspend fun downloadToCache(
        context: Context,
        url: String,
        fileName: String
    ): File = withContext(Dispatchers.IO) {
        val file = File(context.cacheDir, fileName)

        if (file.exists()) return@withContext file

        URL(url).openStream().use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        file
    }
}
