package expo.modules.selectabletext

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ExpoSelectableTextModule : Module() {

  override fun definition() = ModuleDefinition {

    Name("ExpoSelectableText")

    // Enables the module to be used as a native view. Definition components that are accepted as part of
    // the view definition: Prop, Events.
    View(ExpoSelectableTextView::class) {
      Prop("text") {view: ExpoSelectableTextView, text: String ->
        view.textView.text = text.toString()
      }

      Prop("fontSize") {view: ExpoSelectableTextView, fontSize: Float ->
        view.textView.textSize = fontSize
      }

      Prop("fontFamily") {view: ExpoSelectableTextView, fontFamily: String ->
       view.setFontFamily(fontFamily)
      }

      Prop("selectionColor") {view: ExpoSelectableTextView, selectionColor: String ->
        view.textView.highlightColor = view.parseColor(selectionColor)
      }

      Prop("color") {view: ExpoSelectableTextView, color: String ->
        view.textView.setTextColor(view.parseColor(color))
      }

      Events("onSelectionEnd")
    }
  }
}
