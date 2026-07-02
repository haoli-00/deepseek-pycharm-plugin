package com.github.deepseekpycharm

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

/**
 * Factory that creates the "DeepSeek" tool window in the IDE side bar.
 * Registered in META-INF/plugin.xml via <toolWindow> extension.
 */
class DeepSeekToolWindowFactory : ToolWindowFactory, DumbAware {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = DeepSeekBrowserPanel(project)
        val content = ContentFactory.getInstance()
            .createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
        Disposer.register(toolWindow.disposable, panel)
    }
}
