package lib.network.connection.data

import android.net.Network

/**
 * Method to check whether internet is available
 * @return boolean
 */
fun Network.isInternetAvailable(): Boolean {
    return try {
        return getByName("www.google.com").address.isNotEmpty()
    } catch (ex: Exception) {
        false
    }
}