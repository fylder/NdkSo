package fylder.ndk.demo.jni

import android.content.Context
import android.util.Log
import com.blankj.utilcode.util.DeviceUtils
import fylder.ndk.demo.utils.SoPathUtils
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class JniTools {

    companion object {
        var loadLib = ""
    }

    /**
     * 加载上低音号
     */
    fun load(context: Context) {
        System.loadLibrary("native-lib")
    }

    /**
     * 加载紫罗兰
     */
    fun load2(context: Context) {
        try {
            copySo(context)
            System.load(SoPathUtils.getPath())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    //assets => filesDir
    fun copySo(context: Context) {
        var fis: InputStream? = null
        var fos: FileOutputStream? = null
        val buffSize = 1024

        val abi = DeviceUtils.getABIs()[0]
        val assetFileName = "violet/$abi/libnative-lib.so"
        val pathDest = "${context.filesDir!!.absolutePath}/violet/$abi"
        val dirFile = File(pathDest)
        if (!dirFile.exists()) {
            dirFile.mkdirs()
        }
        val fileDest = "$pathDest/libnative-lib.so"
//        if ((TextUtils.equals(fileDest, loadLib))) {
//            //如果当前已经System.load，不能再IO更换已加载so文件，否则会崩溃
//            Log.w("123", "已经加载so，不用复制文件")
//            return
//        }

        try {
            fis = context.assets.open(assetFileName)
            fos = FileOutputStream(fileDest)
            var byteCount = 0
            val buffer = ByteArray(buffSize)
            while (fis.read(buffer).also { byteCount = it } != -1) {
                fos.write(buffer, 0, byteCount)
            }
            fos.flush()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            try {
                fis!!.close()
                fos!!.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        SoPathUtils.setPath(fileDest)
        Log.w("123", "第二个so:$fileDest")
    }

}