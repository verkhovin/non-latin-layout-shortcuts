package ru.verkhovin.nonlatinlayoutshortcuts

import com.intellij.ide.IdeEventQueue
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.KeyboardShortcut
import com.intellij.openapi.actionSystem.Shortcut
import com.intellij.openapi.keymap.KeymapManager
import java.awt.AWTEvent
import java.awt.event.KeyEvent


class KeyPressedEventDispatcher(
    private val actionManager: ActionManager,
    private val keymapManager: KeymapManager
) : IdeEventQueue.EventDispatcher {
    override fun dispatch(event: AWTEvent): Boolean {
        if (event is KeyEvent && event.id == KeyEvent.KEY_PRESSED) {
            handleKeyPressedEvent(event)
        }
        return false
    }

    private fun handleKeyPressedEvent(keyEvent: KeyEvent) {
        val char = keyEvent.extendedKeyCode.toChar()
        if (isShortCut(keyEvent) || !isLatinLetter(char)) {
            LayoutMapper.getSameKeyFromLatinLayout(char)?.let { mappedChar ->
                val shortcut = buildKeyboardShortcut(keyEvent, mappedChar)
                tryEachActionUntilSuccess(keymapManager.activeKeymap.getActionIds(shortcut), keyEvent)
            }
        }
    }

    private fun buildKeyboardShortcut(keyEvent: KeyEvent, mappedChar: Char): Shortcut {
        val shortcutTextBuilder = StringBuilder()
        if (keyEvent.isControlDown) {
            shortcutTextBuilder.append("ctrl ")
        }
        if (keyEvent.isShiftDown) {
            shortcutTextBuilder.append("shift ")
        }
        if (keyEvent.isAltDown) {
            shortcutTextBuilder.append("alt ")
        }
        shortcutTextBuilder.append(mappedChar)
        val shortcutText = shortcutTextBuilder.toString()
        return KeyboardShortcut.fromString(shortcutText)
    }

    private fun tryEachActionUntilSuccess(actionIds: Array<String>, keyEvent: KeyEvent) {
        actionIds.forEach { actionId ->
            val action = actionManager.getAction(actionId)
            val callback = actionManager.tryToExecute(action, keyEvent, keyEvent.component, null, true)
            if (callback.isDone) {
                return
            }
        }
    }

    private fun isShortCut(keyEvent: KeyEvent) = keyEvent.isControlDown || keyEvent.isShiftDown || keyEvent.isAltDown

    private fun isLatinLetter(c: Char) = c in 'A'..'Z' || c in 'a'..'z'
}