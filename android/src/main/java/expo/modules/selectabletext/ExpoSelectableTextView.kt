package expo.modules.selectabletext

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.viewevent.EventDispatcher
import expo.modules.kotlin.views.ExpoView

class ExpoSelectableTextView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {
  private val onSelectionEnd by EventDispatcher()

  internal val textView = object : TextView(context) {

    override fun onTouchEvent(event: MotionEvent): Boolean {
      val handled = super.onTouchEvent(event)

      if (event.action == MotionEvent.ACTION_UP) {
        val selStart = selectionStart
        val selEnd = selectionEnd

        if (selStart != -1 && selEnd != -1) {
          val selectedText: String = if (selStart < selEnd) {
            text.substring(selStart, selEnd)
          } else {
            text.substring(selEnd, selStart)
          }

          if (selectedText.isEmpty()) return true

          onSelectionEnd(mapOf("text" to selectedText, "selectionStart" to selStart, "selectionEnd" to selEnd))
        }

      }
      return handled
    }

  }.apply {
    layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    setTextIsSelectable(true)
    customSelectionActionModeCallback = createCustomSelectionActionModeCallback()
  }

  init {
    addView(textView)
  }

  private fun fontFileExists(path: String, fileName: String): Boolean {
    return context.assets.list(path)?.contains(fileName) == true
  }

  fun setFontFamily(fontName: String) {
    val assetManager = context.assets

    try {
      val fontFileName = when {
        fontFileExists("fonts", "$fontName.ttf") -> "fonts/$fontName.ttf"
        fontFileExists("fonts", "$fontName.otf") -> "fonts/$fontName.otf"
        else -> throw IllegalStateException("Font file not found for: $fontName")
      }
      textView.typeface = Typeface.createFromAsset(assetManager, fontFileName)
    } catch (e: Exception) {
      Log.e("ExpoSelectableTextView", e.toString())
      textView.typeface = Typeface.DEFAULT
    }
  }

  fun parseColor(color: String): Int {
    try {
      val parsedColor = Color.parseColor(color)

      return parsedColor
    } catch(e: IllegalArgumentException) {
      Log.e("ExpoSelectableTextView", "Invalid color format: $color")
      return Color.BLACK
    }
  }

  private fun createCustomSelectionActionModeCallback(): android.view.ActionMode.Callback {
    return object : android.view.ActionMode.Callback {
      override fun onCreateActionMode(mode: android.view.ActionMode?, menu: android.view.Menu?): Boolean = false
      override fun onPrepareActionMode(mode: android.view.ActionMode?, menu: android.view.Menu?): Boolean = false
      override fun onActionItemClicked(mode: android.view.ActionMode?, item: android.view.MenuItem?): Boolean = false
      override fun onDestroyActionMode(mode: android.view.ActionMode?) {}
    }
  }
}
