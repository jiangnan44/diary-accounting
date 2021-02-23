package com.v.bindtest.livedata

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Author:v
 * Time:2021/2/22
 */
class NetworkLiveData : LiveData<Network> {
    companion object {
        private const val TAG = "NetworkLiveData"
        private var mNetworkLiveData: NetworkLiveData? = null
        fun getInstance(context: Context): NetworkLiveData? {
            if (null == mNetworkLiveData) {
                mNetworkLiveData = NetworkLiveData(context)
            }
            return mNetworkLiveData
        }
    }


    val mContext: Context
    val mManager: ConnectivityManager
    val request: NetworkRequest
    val networkCallback: NetworkCallbackImpl


    constructor(context: Context) {
        this.mContext = context
        mManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        request = NetworkRequest.Builder().build()
        networkCallback = NetworkCallbackImpl()
    }

    override fun onActive() {
        super.onActive()
        Log.d(TAG, "onActive()")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            mManager.registerNetworkCallback(request, networkCallback)
        }
    }

    override fun onInactive() {
        super.onInactive()
        Log.d(TAG, "onInactive()")
        mManager.unregisterNetworkCallback(networkCallback)
    }

    //have to remove the observer manually
    override fun observeForever(observer: Observer<in Network>) {
        super.observeForever(observer)
    }


    class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            Log.d(TAG, "network onAvailable")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    mNetworkLiveData?.value = network
                }
            }
        }

        override fun onLost(network: Network) {
            Log.d(TAG, "network onLost")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    mNetworkLiveData?.value = network
                }
            }
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> {
                        Log.d(TAG, "using WiFi")
                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        Log.d(TAG, "using mobil")
                    }
                    else -> {
                        Log.d(TAG, "using unknown...such as bluetooth vpn and Lowpan")
                    }
                }
            }
        }
    }

}