package com.github.deepseekpycharm.actions

import com.github.deepseekpycharm.DeepSeekBrowserPanel
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.wm.ToolWindowManager

/**
 * Editor right-click action: sends the currently selected text
 * to the DeepSeek chat input via JavaScript injection.
 */
class SendToDeepSeekAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val selectedText = editor.selectionModel.selectedText ?: return

        val escaped = selectedText
            .replace("\\", "\\\\")
            .replace("'", "\\'")
            .replace("\n", "\\n")
            .replace("\r", "")

        val toolWindow = ToolWindowManager.getInstance(project)
            .getToolWindow(TOOL_WINDOW_ID) ?: return

        val contentManager = toolWindow.contentManager
        if (contentManager.contentCount == 0) return

        val panel = contentManager.getContent(0)?.component ?: return
        if (panel !is DeepSeekBrowserPanel) return

        toolWindow.show { }

        val js = buildString {
            append("(function(){")
            append("  var sel = document.querySelector('textarea');")
            append("  if(!sel){")
            append("    sel = document.querySelector('[contenteditable=\"true\"]');")
            append("  }")
            append("  if(sel && !sel.disabled){")
            append("    var nativeSetter = Object.getOwnPropertyDescriptor(")
            append("      window.HTMLTextAreaElement.prototype, 'value').set;")
            append("    nativeSetter.call(sel, '$escaped');")
            append("    sel.dispatchEvent(new Event('input', {bubbles:true}));")
            append("    sel.focus();")
            append("    sel.selectionStart = sel.value.length;")
            append("  }")
            append("})()")
        }

        panel.executeJavaScript(js)
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        val hasSelection = editor?.selectionModel?.hasSelection() == true
        e.presentation.isEnabledAndVisible = project != null && hasSelection
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.EDT
    }

    companion object {
        private const val TOOL_WINDOW_ID = "DeepSeek"
    }
}
