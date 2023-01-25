package lib.network.connection

import android.content.Context
import android.net.*
import androidx.lifecycle.MutableLiveData
import lib.network.connection.data.NetworkState
import lib.network.connection.data.isInternetAvailable

class NetworkConnection(private val context: Context) : ConnectivityManager.NetworkCallback() {

    private val connectivityManager by lazy {
        context.getSystemService(ConnectivityManager::class.java)
    }

    private val networkRequest by lazy {
        NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }

    private var _isNetworkAvailable = MutableLiveData(NetworkState())
    val networkState get() = _isNetworkAvailable

    init {
        connectivityManager.requestNetwork(networkRequest, this)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        _isNetworkAvailable.postValue(NetworkState(isNetworkAvailable = network.isInternetAvailable()))
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        _isNetworkAvailable.postValue(NetworkState(isLosingConnection = true))
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        _isNetworkAvailable.postValue(NetworkState(lostConnection = true))
    }

    override fun onUnavailable() {
        super.onUnavailable()
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
    }

    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
        super.onLinkPropertiesChanged(network, linkProperties)
    }

    override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
        super.onBlockedStatusChanged(network, blocked)
    }
}