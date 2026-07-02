plugins {
    id("org.jetbrains.intellij.platform") version("2.2.1")
    kotlin("jvm") version("2.0.21")
}

val pycharmVersion: String by project
val pluginVersion: String by project
val pluginSinceBuild: String by project
val pluginUntilBuild: String by project
val javaVersion: String by project

group = "com.github.deepseekpycharm"
version = pluginVersion

kotlin {
    jvmToolchain(javaVersion.toInt())
}

repositories {
    mavenCentral()
    intellijPlatform { defaultRepositories() }
}

dependencies {
    intellijPlatform { create("IC", pycharmVersion) }
}

intellijPlatform {
    pluginConfiguration {
        version = pluginVersion
        ideaVersion {
            sinceBuild = pluginSinceBuild
            untilBuild = pluginUntilBuild
        }
    }
}

tasks {
    buildSearchableOptions { enabled = false }
    named<org.jetbrains.intellij.platform.gradle.tasks.BuildPluginTask>("buildPlugin") {
        archiveBaseName.set("DeepSeek-for-PyCharm")
    }
    named<org.jetbrains.intellij.platform.gradle.tasks.PublishPluginTask>("publishPlugin") {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
