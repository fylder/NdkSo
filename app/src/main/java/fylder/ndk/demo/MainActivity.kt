package fylder.ndk.demo

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import fylder.ndk.demo.service.AnimeService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var remoteService: IRemoteService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        unload_btn.setOnClickListener(this)
        ahh_btn.setOnClickListener(this)
        ahh2_btn.setOnClickListener(this)
        start_btn.setOnClickListener(this)

        RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe {
                if (!it) {
                    Log.w("123", "请赋予文件读写权限")
                }
            }
    }

    override fun onClick(v: View?) {
        when (v) {
            unload_btn -> {
                stopAhhService()
            }
            ahh_btn -> {
                startAhhService("euphonium")
            }
            ahh2_btn -> {
                startAhhService("violet")
            }
            start_btn -> {
                getData()
            }
        }
    }

    private fun startAhhService(type: String) {
        val serviceIntent = Intent(this, AnimeService::class.java)
        serviceIntent.putExtra("type", type)
        val flag = bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        if (flag) {
            Log.i("123", "service bind success")
        } else {
            Log.w("123", "service bind error")
        }
    }

    private fun stopAhhService() {
        if (remoteService == null) {
            anime_text.text = "服务不存在,请先打开服务"
            return
        }
        unbindService(serviceConnection)
        remoteService = null
    }

    private fun getData() {
        if (remoteService != null) {
            Log.i("123", "remoteService:${remoteService}")
            val data = remoteService!!.anime
            Log.w("123", "获取: ${data.name}")
            anime_text.text = data.name
        } else {
            anime_text.text = "请先打开服务"
        }
    }

    private var serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("123", "service onServiceConnected remoteService:${remoteService}")
            remoteService = IRemoteService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.w("123", "service onServiceDisconnected")
            remoteService = null
        }
    }

}
