package fylder.ndk.demo.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.text.TextUtils
import android.util.Log
import fylder.ndk.demo.IRemoteService
import fylder.ndk.demo.bean.AnimeBean
import fylder.ndk.demo.jni.JniTools

class AnimeService : Service() {

    lateinit var jniTools: JniTools

    override fun onBind(intent: Intent?): IBinder? {
        if (intent!!.hasExtra("type")) {
            val type = intent.getStringExtra("type")
            Log.w("123", "service onBind has type: $type")
            jniTools = JniTools()
            if (TextUtils.equals("violet", type)) {
                jniTools.load2(this)
            } else {
                jniTools.load(this)
            }
        }
        return myStub
    }

    private val myStub = object : IRemoteService.Stub() {

        override fun getAnime(): AnimeBean {
            val name = jniTools.stringFromJNI()
            return AnimeBean(name)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("123", "AnimeService onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("123", "AnimeService onDestroy()")
        android.os.Process.killProcess(android.os.Process.myPid())
    }

}