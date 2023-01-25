package app.android.network_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lib.network.connection.NetworkConnection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val networkConnection = NetworkConnection(this)
        networkConnection.networkState.observe(this) {
            if (it.isNetworkAvailable) {
                println("Network available")
            } else {
                print("Network not available")
            }
        }

    }
}