import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri

data class DownloadedFile(val id: Long, val title: String, val uri: Uri)

private fun getDownloadedFiles(context: Context): ArrayList<DownloadedFile> {
    val downloadedFiles = ArrayList<DownloadedFile>()

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val query = DownloadManager.Query().apply {
        setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL)
    }
    val cursor: Cursor = downloadManager.query(query)

    while (cursor.moveToNext()) {
        val idIndex: Int = cursor.getColumnIndex(DownloadManager.COLUMN_ID)
        val titleIndex: Int = cursor.getColumnIndex(DownloadManager.COLUMN_TITLE)
        val uriIndex: Int = cursor.getColumnIndex(DownloadManager.COLUMN_URI)

        val id: Long = cursor.getLong(idIndex)
        val title: String = cursor.getString(titleIndex)
        val uriString: String = cursor.getString(uriIndex)
        val uri: Uri = Uri.parse(uriString)

        downloadedFiles.add(DownloadedFile(id, title, uri))
    }

    cursor.close()
    return downloadedFiles
}