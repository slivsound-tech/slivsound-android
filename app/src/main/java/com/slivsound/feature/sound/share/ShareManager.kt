package com.slivsound.feature.sound.share

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

class ShareManager(
    private val context: Context
) {

    fun shareAudio(
        file: File,
        title: String,
        link: String
    ) {
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "audio/mpeg"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(
                Intent.EXTRA_TEXT,
                """
🎵 $title

🔗 $link
""".trimIndent()
            )
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(
            Intent.createChooser(intent, "Поделиться")
        )
    }
}
