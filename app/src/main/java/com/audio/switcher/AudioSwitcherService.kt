package com.audio.switcher
import android.content.Intent
import android.service.quicksettings.TileService

class AudioSwitcherService : TileService() {
    override fun onClick() {
        super.onClick()
        val intent = Intent(this, Empty::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivityAndCollapse(intent)
    }
}

