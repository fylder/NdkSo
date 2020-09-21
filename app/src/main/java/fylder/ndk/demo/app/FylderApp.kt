package fylder.ndk.demo.app

import android.app.Application
import android.util.Log

class FylderApp:Application() {

    override fun onCreate() {
        super.onCreate()
        Log.i("123", "app create()")
    }
}