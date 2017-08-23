package fr.inria.spirals.webtrafficinkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val httpRequestButton: Button = findViewById(R.id.http_request_button)
        httpRequestButton.setOnClickListener {
            doAsync {
                try {
                    val result = URL("http://httpbin.org/html").readText()
                    uiThread { toast("HTTP OK") }
                    Log.d(TAG, "result=$result")
                } catch (e: Exception) {
                    uiThread { toast("HTTP Failed") }
                    Log.e(TAG, e.message)
                }
            }
        }

        val httpsRequestButton : Button = findViewById(R.id.https_request_button)
        httpsRequestButton.setOnClickListener {
            doAsync {
                try {
                    val result = URL("https://httpbin.org/html").readText()
                    uiThread { toast("HTTPS OK") }
                    Log.d(TAG, "result=$result")
                } catch (e: Exception) {
                    uiThread { toast("HTTPS Failed") }
                    Log.e(TAG, e.message)
                }
            }
        }
    }
}
