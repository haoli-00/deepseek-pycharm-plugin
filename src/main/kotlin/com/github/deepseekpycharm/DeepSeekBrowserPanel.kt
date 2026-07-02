package com.github.deepseekpycharm

import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.BorderLayout
import javax.swing.JPanel

class DeepSeekBrowserPanel(project: Project) : JPanel(BorderLayout()), Disposable {

    private val browser: JBCefBrowser = JBCefBrowser()

    init {
        browser.loadURL("https://chat.deepseek.com")
        add(browser.component, BorderLayout.CENTER)
    }

    fun executeJavaScript(script: String) {
        browser.cefBrowser.executeJavaScript(script, browser.cefBrowser.url, 0)
    }

    override fun dispose() {
        browser.dispose()
    }
}
