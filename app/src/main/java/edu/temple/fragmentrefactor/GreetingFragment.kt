package edu.temple.fragmentrefactor

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class GreetingFragment : Fragment(R.layout.fragment_greeting) {

    companion object {
        fun newInstance() = GreetingFragment()
        private const val KEY_DISPLAY_TEXT = "key_display_text"
    }

    private var displayText: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displayTextView = view.findViewById<TextView>(R.id.displayTextView)
        val nameEditText = view.findViewById<EditText>(R.id.nameEditText)
        val changeButton = view.findViewById<Button>(R.id.changeButton)

        // Restore saved display text if any
        displayText = savedInstanceState?.getString(KEY_DISPLAY_TEXT)
        displayText?.let { displayTextView.text = it }

        changeButton.setOnClickListener {
            val name = nameEditText.text
            val msg = if (name.isNotBlank()) "Hello, $name!" else "Please enter your name"
            displayText = msg
            displayTextView.text = msg
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Preserve the greeting across configuration changes
        outState.putString(KEY_DISPLAY_TEXT, displayText)
    }
}