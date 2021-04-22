package ru.verkhovin.nonlatinlayoutshortcuts.listeners

import com.intellij.ide.IdeEventQueue
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.components.service
import com.intellij.openapi.keymap.KeymapManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import ru.verkhovin.nonlatinlayoutshortcuts.KeyPressedEventDispatcher
import ru.verkhovin.nonlatinlayoutshortcuts.services.DummyDisposableService

internal class ProjectOpenedListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        val actionManager = ActionManager.getInstance()
        val keymapManager = KeymapManager.getInstance()
        IdeEventQueue.getInstance().addPostprocessor(
            KeyPressedEventDispatcher(actionManager, keymapManager),
            project.service<DummyDisposableService>()
        )
    }
}
