import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    `java-gradle-plugin`
    alias(libs.plugins.plugin.publish)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    explicitApi()
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

fun Provider<PluginDependency>.toDependency(): Provider<ExternalModuleDependency> = map {
    project.dependencyFactory.create(
        it.pluginId,
        "${it.pluginId}.gradle.plugin",
        it.version.toString(),
    )
}

dependencies {
    implementation(libs.plugins.android.application.toDependency())
    implementation(libs.android.tools.common)
}

gradlePlugin {
    vcsUrl.set("https://github.com/5peak2me/gradle-plugin-template")

    plugins {
        register("gradle-plugin-template") {
            id = "io.github.speak2me.gradle.plugin"
            displayName = "pgyer-gradle-plugin"
            description = "the desc of plugin"
            tags.set(listOf("android gradle plugin"))
            implementationClass = "io.github.speak2me.gradle.plugin.plugin.TemplatePlugin"
        }
    }
}
