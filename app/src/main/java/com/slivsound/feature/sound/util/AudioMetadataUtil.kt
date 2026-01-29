package com.slivsound.feature.sound.util

import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey
import org.jaudiotagger.tag.images.ArtworkFactory
import java.io.File

object AudioMetadataUtil {

    fun embedCover(
        mp3File: File,
        coverFile: File,
        title: String
    ) {
        val audioFile = AudioFileIO.read(mp3File)
        val tag = audioFile.tagOrCreateAndSetDefault

        tag.setField(FieldKey.TITLE, title)
        tag.deleteArtworkField()

        val artwork = ArtworkFactory.createArtworkFromFile(coverFile)
        tag.setField(artwork)

        AudioFileIO.write(audioFile)
    }
}
