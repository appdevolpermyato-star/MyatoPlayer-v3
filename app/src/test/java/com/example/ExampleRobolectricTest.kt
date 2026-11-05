package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Myato Player", appName)
  }

  @Test
  fun `floating player prefs defaults`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    assertEquals(100, com.example.service.FloatingPlayerPrefs.getPanelSizePercent(context))
    assertEquals(100, com.example.service.FloatingPlayerPrefs.getPanelOpacityPercent(context))
  }

  @Test
  fun `floating player free positioning save and restore`() {
    val context = ApplicationProvider.getApplicationContext<Context>()

    // Top-Left
    com.example.service.FloatingPlayerPrefs.savePosition(context, 16, 80)
    assertEquals(Pair(16, 80), com.example.service.FloatingPlayerPrefs.getSavedPosition(context))

    // Top-Right
    com.example.service.FloatingPlayerPrefs.savePosition(context, 320, 80)
    assertEquals(Pair(320, 80), com.example.service.FloatingPlayerPrefs.getSavedPosition(context))

    // Center
    com.example.service.FloatingPlayerPrefs.savePosition(context, 180, 400)
    assertEquals(Pair(180, 400), com.example.service.FloatingPlayerPrefs.getSavedPosition(context))

    // Diagonal
    com.example.service.FloatingPlayerPrefs.savePosition(context, 120, 250)
    assertEquals(Pair(120, 250), com.example.service.FloatingPlayerPrefs.getSavedPosition(context))

    // Bottom-Left
    com.example.service.FloatingPlayerPrefs.savePosition(context, 16, 720)
    assertEquals(Pair(16, 720), com.example.service.FloatingPlayerPrefs.getSavedPosition(context))

    // Bottom-Right
    com.example.service.FloatingPlayerPrefs.savePosition(context, 320, 720)
    assertEquals(Pair(320, 720), com.example.service.FloatingPlayerPrefs.getSavedPosition(context))

    // Reset
    com.example.service.FloatingPlayerPrefs.resetPosition(context)
    assertEquals(Pair(24, 200), com.example.service.FloatingPlayerPrefs.getSavedPosition(context))
  }

  @Test
  fun `playback prefs crossfade duration save and restore`() {
    val context = ApplicationProvider.getApplicationContext<Context>()

    // Default should be 3000ms
    assertEquals(3000L, com.example.service.PlaybackPrefs.getCrossfadeDuration(context))

    // Set 5000ms
    com.example.service.PlaybackPrefs.setCrossfadeDuration(context, 5000L)
    assertEquals(5000L, com.example.service.PlaybackPrefs.getCrossfadeDuration(context))

    // Set 0ms (disabled)
    com.example.service.PlaybackPrefs.setCrossfadeDuration(context, 0L)
    assertEquals(0L, com.example.service.PlaybackPrefs.getCrossfadeDuration(context))

    // Clamping max
    com.example.service.PlaybackPrefs.setCrossfadeDuration(context, 20000L)
    assertEquals(12000L, com.example.service.PlaybackPrefs.getCrossfadeDuration(context))
  }
}
