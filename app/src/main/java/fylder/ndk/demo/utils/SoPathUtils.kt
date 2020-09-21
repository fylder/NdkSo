package fylder.ndk.demo.utils

import com.blankj.utilcode.util.SPUtils

object SoPathUtils {

    fun getPath(): String {
        return SPUtils.getInstance().getString("so_path", "")
    }

    fun setPath(path: String) {
        SPUtils.getInstance().put("so_path", path)
    }
}