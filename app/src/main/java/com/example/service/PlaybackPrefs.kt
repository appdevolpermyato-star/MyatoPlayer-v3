package com.example.service

import android.content.Context

/**
 * Preferences manager for audio playback behaviors, including crossfade transitions.
 */
object PlaybackPrefs {
    private const val PREFS_NAME = "myato_playback_prefs"
    private const val KEY_CROSSFADE_DURATION_MS = "key_crossfade_duration_ms"

    const val DEFAULT_CROSSFADE_MS = 3000L // 3 seconds default

    fun getCrossfadeDuration(context: Context): Long {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getLong(KEY_CROSSFADE_DURATION_MS, DEFAULT_CROSSFADE_MS)
    }

    fun setCrossfadeDuration(context: Context, durationMs: Long) {
        val clamped = durationMs.coerceIn(0L, 12000L)
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putLong(KEY_CROSSFADE_DURATION_MS, clamped).apply()
    }
}
