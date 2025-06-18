package moe.lyniko.hiderecent

import android.graphics.BitmapFactory
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

class MainHook : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (lpparam.packageName == "com.android.systemui") {
            hookSystemUIRecents(lpparam)
        }
    }

    private fun hookSystemUIRecents(lpparam: LoadPackageParam) {
        try {
            val clazz = XposedHelpers.findClass(
                "com.android.systemui.recents.views.TaskView",
                lpparam.classLoader
            )

            XposedBridge.hookAllMethods(clazz, "bindThumbnail", object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    try {
                        val bmp = BitmapFactory.decodeFile("/sdcard/coverRecent.png")
                        if (bmp != null) {
                            param.args[0] = bmp
                            XposedBridge.log("coverRecent: Replaced thumbnail with custom image.")
                        } else {
                            XposedBridge.log("coverRecent: Failed to load /sdcard/coverRecent.png")
                        }
                    } catch (e: Throwable) {
                        XposedBridge.log("coverRecent: Error replacing thumbnail: ${e.message}")
                    }
                }
            })
        } catch (e: Throwable) {
            XposedBridge.log("coverRecent: Failed to hook TaskView.bindThumbnail: ${e.message}")
        }
    }
}
