package com.github.verkhovin.nonlatinlayoutshortcuts.services

import com.github.verkhovin.nonlatinlayoutshortcuts.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
