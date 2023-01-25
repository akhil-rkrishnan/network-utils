package lib.network.connection.data

/**
* Network state class
*/
data class NetworkState(
    val isConnecting: Boolean = false,
    val isNetworkAvailable: Boolean = false,
    val isLosingConnection: Boolean = false,
    val lostConnection: Boolean = false,
    val errorMessage: String? = null
)