// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("com.diffplug.spotless") version "6.7.2"
}

subprojects {
    pluginManager.apply("com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt", "bin/**/*.kt")
            ktlint("0.45.2").userData(
                mapOf("android" to "true")
            )
            trimTrailingWhitespace()
            endWithNewline()
        }
        format("misc") {
            target("**/*.kts", "**/*.gradle", "**/*.xml", "**/*.md", "**/.gitignore")
            targetExclude("**/build/**/*.kts", "**/build/**/*.xml")
            trimTrailingWhitespace()
            indentWithSpaces(4)
            endWithNewline()
        }
    }
}
