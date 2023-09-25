package com.example.openfile

import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import getExternalMemoryUsage
import getExternalStorageStats
import getInternalStorageStats
import getSystemMemoryUsage
import getSystemStorageSize
import getTotalStorageSpace
import getUsedStorageSpace
import getbonho
import getdatacash
import tong
import kotlin.math.roundToLong



class MainActivity : AppCompatActivity() {

    lateinit var mopenfile : TextView
    lateinit var btn : Button
    var used : Int? = null
    var notused : Int? = null
    var usedlong : Float? = null
    var bonhohethong: Float? = null
    var bonhohethongfile: Float? = null

    private lateinit var mStorageManager: StorageManager
    private val mStorageVolumesByExtDir = mutableListOf<VolumeStats>()
    private lateinit var mVolumeStats: TextView
    private lateinit var mUnitsToggle: ToggleButton
    private var mKbToggleValue = true
    private var kbToUse = KB
    private var mbToUse = MB
    private var gbToUse = GB

    companion object{
        // These values seems to work for "Files by Google"...
        const val KB = 1_000L
        const val MB = KB * KB
        const val GB = KB * KB * KB

        // ... and these values seems to work for other file manager apps.
        const val KiB = 1_024L
        const val MiB = KiB * KiB
        const val GiB = KiB * KiB * KiB

        const val PRIMARY_STORAGE_LABEL = "Internal Storage"

        const val TAG = "MainActivity"

        data class VolumeStats(
            val mStorageVolume: StorageVolume,
            var mTotalSpace: Long = 0,
            var mUsedSpace: Long = 0
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStorageManager = getSystemService(Context.STORAGE_SERVICE) as StorageManager
        getVolumeStats()

        val internalStats = getInternalStorageStats()
        val externalStats = getExternalStorageStats()

        mopenfile = findViewById(R.id.openfile)
        btn = findViewById(R.id.btn_openfile)

        used = ((internalStats.availableSize.toFloat() / internalStats.totalSize.toFloat())*100).toInt()
        notused = (100 - used!!)
        var test = getbonho()
        usedlong = (((getUsedStorageSpace()/(1024*1024)).toFloat() / (getTotalStorageSpace()/(1024*1024)).toFloat())*100)

        bonhohethongfile = getSystemMemoryUsage(this).toFloat()
        bonhohethong = getExternalMemoryUsage().toFloat()
        btn.setOnClickListener(){

        }
        mopenfile.setOnClickListener(){
            Log.d("checkk","Internal Storage:")
            Log.d("checkk","Total byte: ${internalStats.totalbye} bytes")
            Log.d("checkk","Total Size: ${internalStats.totalSize} bytes")
            Log.d("checkk","Available Size: ${internalStats.availableSize} bytes")
            Log.d("checkk","test Size: $test GB")
            Log.d("checkk", "phần trăm đã sử dụng: $used")
            Log.d("checkk", "phần trăm đã sử dụng: $notused")
            Log.d("checkk", "phần trăm còn sử dụng: $usedlong")
            Log.d("checkk", "bộ nhớ hệ thống file: $bonhohethongfile")
            Log.d("checkk", "bộ nhớ hệ thống: $bonhohethong")

            if (externalStats != null) {
                Log.d("checkk","External Storage:")
                Log.d("checkk","Total Size: ${externalStats.totalSize} bytes")
                Log.d("checkk","Available Size: ${externalStats.availableSize} bytes")
            } else {
                Log.d("checkk","External Storage not available.")
            }
        }




    }
    private fun getVolumeStats() {
        val extDirs = getExternalFilesDirs(null)
        mStorageVolumesByExtDir.clear()
        extDirs.forEach { file ->
            val storageVolume: StorageVolume? = mStorageManager.getStorageVolume(file)
            if (storageVolume == null) {
                Log.d(TAG, "Could not determinate StorageVolume for ${file.path}")
            } else {
                val totalSpace: Long
                val usedSpace: Long
                if (storageVolume.isPrimary) {
                    val uuid = StorageManager.UUID_DEFAULT
                    val storageStatsManager =
                        getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
                    totalSpace = (storageStatsManager.getTotalBytes(uuid) / 1_000_000_000) * gbToUse
                    usedSpace = totalSpace - storageStatsManager.getFreeBytes(uuid)
                    Log.d("checkk", totalSpace.toString())
                    Log.d("checkk", usedSpace.toString())
                } else {
                    totalSpace = file.totalSpace
                    usedSpace = totalSpace - file.freeSpace
                }
                mStorageVolumesByExtDir.add(
                    VolumeStats(storageVolume, totalSpace, usedSpace)
                )
            }
        }
    }
}