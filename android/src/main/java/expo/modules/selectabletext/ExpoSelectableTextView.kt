package expo.modules.selectabletext

import android.content.Context
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

    // TODO: Make it dynamic so the user can select different options in the context menu
    customSelectionActionModeCallback = object : android.view.ActionMode.Callback {
      override fun onCreateActionMode(mode: android.view.ActionMode?, menu: android.view.Menu?): Boolean {
        return false
      }
      override fun onPrepareActionMode(mode: android.view.ActionMode?, menu: android.view.Menu?): Boolean {
        return false
      }
      override fun onActionItemClicked(mode: android.view.ActionMode?, item: android.view.MenuItem?): Boolean {
        return false
      }
      override fun onDestroyActionMode(mode: android.view.ActionMode?) {
        // No cleanup required by default
      }
    }
  }

  init {
    addView(textView)
  }
}
