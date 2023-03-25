package com.audio.switcher

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Bundle


class Empty : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showDialogForAndroidSAndT(this)
        finish()
    }

    private fun showDialogForAndroidSAndT(context: Context): Boolean {
        val intent = Intent()
            .setAction("com.android.systemui.action.LAUNCH_MEDIA_OUTPUT_DIALOG")
            .setPackage("com.android.systemui")
            .putExtra(
                "package_name",
                context.packageName
            )

        val packageManager = context.packageManager
        val resolveInfos = packageManager.queryBroadcastReceivers(
            intent,
            0
        )
        for (resolveInfo in resolveInfos) {
            val activityInfo = resolveInfo.activityInfo
            if (activityInfo == null || activityInfo.applicationInfo == null) {
                continue
            }
            val appInfo = activityInfo.applicationInfo
            if ((ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP
                        and appInfo.flags) != 0
            ) {
                context.sendBroadcast(intent)
                return true
            }
        }
        return false
    }
}