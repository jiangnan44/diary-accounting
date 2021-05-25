package com.v.accounting.utils

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build

/**
 * Author:v
 * Time:2021/2/22
 */
object NetUtil {

    fun isWifiOr5G(context: Context): Boolean {
        val wifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val info = wifiManager.connectionInfo ?: return false

        var frequency = 0
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            frequency = info.frequency
        } else {
            val ssid = info.ssid ?: return false
            if (ssid.length < 2) return false
            val sid = ssid.substring(1, ssid.length - 1)
            for (scan in wifiManager.scanResults) {
                if (scan.SSID == sid) {
                    frequency = scan.frequency
                    break
                }
            }
        }

        return frequency in 4900..5900
    }

    fun getConnectedWifiSSID(context: Context): String {
        val wifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo ?: return ""
        return wifiInfo.ssid.substring(1, wifiInfo.ssid.length - 1)
    }
}