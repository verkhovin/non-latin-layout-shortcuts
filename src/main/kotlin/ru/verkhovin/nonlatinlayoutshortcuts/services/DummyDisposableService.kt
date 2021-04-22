package ru.verkhovin.nonlatinlayoutshortcuts.services

import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project

class DummyDisposableService(project: Project) : Disposable {

    override fun dispose() {
        // nothing to do here
    }
}
