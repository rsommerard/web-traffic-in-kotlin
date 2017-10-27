package fr.inria.spirals.webtrafficinkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.PrintWriter
import java.net.*


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.simpleName

    private val okHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val httpRequestButton: Button = findViewById(R.id.http_request_button)
        httpRequestButton.setOnClickListener {
            doAsync {
                try {
                    val request = Request.Builder().url("http://httpbin.org/html").build()

                    val response = okHttpClient.newCall(request).execute()

                    if (response.isSuccessful) {
                        uiThread { toast("HTTP OK") }
                        Log.d(TAG, "result=${response.body()?.string()}")
                    } else {
                        uiThread { toast("HTTP Failed") }
                        Log.w(TAG, "result=${response.message()}")
                    }
                } catch (e: Exception) {
                    uiThread { toast("HTTP Failed") }
                    Log.e(TAG, e.message)
                }
            }
        }

        val httpRequestBisButton: Button = findViewById(R.id.http_request_bis_button)
        httpRequestBisButton.setOnClickListener {
            doAsync {
                try {
                    val request = Request.Builder().url("http://flickr.com").build()

                    val response = okHttpClient.newCall(request).execute()

                    if (response.isSuccessful) {
                        uiThread { toast("HTTP Bis OK") }
                        Log.d(TAG, "result=${response.body()?.string()}")
                    } else {
                        uiThread { toast("HTTP Bis Failed") }
                        Log.w(TAG, "result=${response.message()}")
                    }
                } catch (e: Exception) {
                    uiThread { toast("HTTP Bis Failed") }
                    Log.e(TAG, e.message)
                }
            }
        }

        val http2RequestButton: Button = findViewById(R.id.http2_request_button)
        http2RequestButton.setOnClickListener {
            doAsync {
                try {
                    val request = Request.Builder().url("http://flickr.com").addHeader("Upgrade", "h2c").addHeader("HTTP2-Settings", "VGhpcyBpcyBhIHRlc3Qh").build()

                    val response = okHttpClient.newCall(request).execute()

                    if (response.isSuccessful) {
                        uiThread { toast("HTTP2 OK") }
                        Log.d(TAG, "result=${response.body()?.string()}")
                    } else {
                        uiThread { toast("HTTP2 Failed") }
                        Log.w(TAG, "result=${response.message()}")
                    }
                } catch (e: Exception) {
                    uiThread { toast("HTTP2 Failed") }
                    Log.e(TAG, e.message)
                }
            }
        }

        val httpsRequestButton : Button = findViewById(R.id.https_request_button)
        httpsRequestButton.setOnClickListener {
            doAsync {
                try {
                    val request = Request.Builder().url("https://httpbin.org/html").build()

                    val response = okHttpClient.newCall(request).execute()

                    if (response.isSuccessful) {
                        uiThread { toast("HTTPS OK") }
                        Log.d(TAG, "result=${response.body()?.string()}")
                    } else {
                        uiThread { toast("HTTPS Failed") }
                        Log.w(TAG, "result=${response.message()}")
                    }
                } catch (e: Exception) {
                    uiThread { toast("HTTPS Failed") }
                    Log.e(TAG, e.message)
                }
            }
        }

        val httpsRequestBisButton : Button = findViewById(R.id.https_request_bis_button)
        httpsRequestBisButton.setOnClickListener {
            doAsync {
                try {
                    val request = Request.Builder().url("https://flickr.com").build()

                    val response = okHttpClient.newCall(request).execute()

                    if (response.isSuccessful) {
                        uiThread { toast("HTTPS Bis OK") }
                        Log.d(TAG, "result=${response.body()?.string()}")
                    } else {
                        uiThread { toast("HTTPS Bis Failed") }
                        Log.w(TAG, "result=${response.message()}")
                    }
                } catch (e: Exception) {
                    uiThread { toast("HTTPS Bis Failed") }
                    Log.e(TAG, e.message)
                }
            }
        }

        val otherTcpRequestButton : Button = findViewById(R.id.other_tcp_request_button)
        otherTcpRequestButton.setOnClickListener {
            doAsync {
                val socket = Socket("193.51.236.75", 1234)
                val out = PrintWriter(socket.getOutputStream(), true)
                out.println("This is a TCP request test.")
                out.close()
                socket.close()
                uiThread { toast("Other TCP OK") }
            }
        }

        val otherUdpRequestButton : Button = findViewById(R.id.other_udp_request_button)
        otherUdpRequestButton.setOnClickListener {
            doAsync {
                val datagramSocket = DatagramSocket()

                val message = "This is a UDP request test."
                val buffer = message.toByteArray()
                val packet = DatagramPacket(buffer, buffer.size, InetAddress.getByName("193.51.236.75"), 4321)
                packet.data = buffer
                datagramSocket.send(packet)
                uiThread { toast("Other UDP OK") }
            }
        }
    }
}
