import android.app.ActivityManager
import android.content.Context
import android.os.Environment
import android.os.StatFs
import android.util.Log
import java.io.File


// Phân tích bộ nhớ trong
fun getInternalStorageStats(): StorageStats {
    val internalPath = Environment.getDataDirectory().path
    val statFs = StatFs(internalPath)
    val mtotalbye = statFs.totalBytes
    val blockSize = statFs.blockSizeLong
    val totalBlocks = statFs.blockCountLong
    val availableBlocks = statFs.availableBlocksLong

    val totalSize = totalBlocks * blockSize
    val availableSize = availableBlocks * blockSize

    return StorageStats(totalSize, availableSize, mtotalbye)
}

// Phân tích bộ nhớ trên thẻ
fun getExternalStorageStats(): StorageStats? {
    if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
        val externalPath = Environment.getExternalStorageDirectory().path
        val statFs = StatFs(externalPath)
        val blockSize = statFs.blockSizeLong
        val mtotalbye = statFs.totalBytes
        val totalBlocks = statFs.blockCountLong
        val availableBlocks = statFs.availableBlocksLong

        val totalSize = totalBlocks * blockSize
        val availableSize = availableBlocks * blockSize

        return StorageStats(totalSize, availableSize, mtotalbye)
    }
    return null
}

fun getAvailableStorageSpace(): Long {
    val externalPath = Environment.getExternalStorageDirectory().path
    val statFs = StatFs(externalPath)
    val blockSize = statFs.blockSizeLong
    val availableBlocks = statFs.availableBlocksLong

    return (availableBlocks * blockSize)/(1024*1024*1024)
}

fun getdatacash() : Float{
    val cache = Environment.getExternalStorageDirectory().path
    val statFs = StatFs(cache)
    val blockSize = statFs.blockSizeLong
    val availableBlocks = statFs.totalBytes

    return (availableBlocks )/(1024*1024*1024).toFloat()
}

fun getSystemStorageSize(): Float {
    val path = File("/system")
    val statFs = StatFs(path.path)
    val blockSize = statFs.blockSizeLong
    val totalBlocks = statFs.blockCountLong

    return (blockSize * totalBlocks)/(1024*1024*1024).toFloat()
}

fun tong() :Float{
    return getdatacash() + getSystemStorageSize()
}

fun getTotalStorageSpace(): Long {
    val externalPath = Environment.getExternalStorageDirectory().path
    val statFs = StatFs(externalPath)
    val blockSize = statFs.blockSizeLong
    val totalBlocks = statFs.blockCountLong

    return (totalBlocks * blockSize)/(1024*1024*1024)
}

fun getUsedStorageSpace(): Long {
    return getTotalStorageSpace() - getAvailableStorageSpace()
}

data class StorageStats(val totalSize: Long, val availableSize: Long, val totalbye : Long)

fun getSystemMemoryUsage(context: Context): Long {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val memoryInfo = ActivityManager.MemoryInfo()
    activityManager.getMemoryInfo(memoryInfo)

    StorageStatsnew(memoryInfo.totalMem, memoryInfo.availMem)

    memoryInfo.totalMem = memoryInfo.totalMem /(1024*1024*1024)

    //memoryInfo.availMem
    return (memoryInfo.totalMem)
}

fun getExternalMemoryUsage(): Long {
    val externalPath = Environment.getRootDirectory().absolutePath
    val statFs = StatFs(externalPath)
    val blockSize = statFs.blockSizeLong
    val totalBlocks = statFs.blockCountLong
    val availableBlocks = statFs.availableBlocksLong

    val totalSize = totalBlocks * blockSize /(1024*1024*1024)
    val availableSize = availableBlocks * blockSize
    return totalSize
}



fun getbonho()  {

}


data class StorageStatsnew(val totalSize: Long, val availableSize: Long)



