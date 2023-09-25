package com.example.openfile

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

data class MediaFile(val id: Int, val type: MediaType, val path: String)

enum class MediaType {
    IMAGE,
    VIDEO,
    AUDIO
}

private fun getAllMediaFiles(context: Context, mediaType: MediaType): ArrayList<MediaFile> {
    val cursor: Cursor?
    val listOfAllMediaFiles = ArrayList<MediaFile>()
    var absolutePathOfFile: String?
    val uri: Uri = when (mediaType) {
        MediaType.IMAGE -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        MediaType.VIDEO -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        MediaType.AUDIO -> MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    }
    val projection = arrayOf(
        MediaStore.MediaColumns._ID,
        MediaStore.MediaColumns.DATA
    )
    cursor = context.contentResolver.query(
        uri, projection, null,
        null, null
    )
    if (cursor == null) {
        return listOfAllMediaFiles
    }
    val columnIndexId: Int = cursor.getColumnIndex(MediaStore.MediaColumns._ID)
    val columnIndexData: Int = cursor.getColumnIndex(MediaStore.MediaColumns.DATA)

    var id = 0
    while (cursor.moveToNext()) {
        absolutePathOfFile = cursor.getString(columnIndexData)
        listOfAllMediaFiles.add(MediaFile(id++, mediaType, absolutePathOfFile))
    }
    cursor.close()
    return listOfAllMediaFiles
}