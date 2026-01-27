package io.github.speak2me.gradle.plugin.plugin

import com.android.build.api.artifact.ScopedArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ScopedArtifacts
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.internal.component.ApkCreationConfig
import com.android.build.gradle.internal.component.ComponentCreationConfig
import com.android.build.gradle.internal.tasks.factory.registerTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import kotlin.jvm.java

/**
 * https://slack-chats.kotlinlang.org/t/501345/i-can-call-internal-symbols-from-other-modules-using-suppres
 */
@Suppress("unused", "INVISIBLE_REFERENCE")
internal class TemplatePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.withType(AppPlugin::class.java) {
                val androidComponents =
                    extensions.findByType(AndroidComponentsExtension::class.java)

                androidComponents?.onVariants { variant ->
                    val creationConfig = variant as? ApkCreationConfig ?: return@onVariants
//                    val creationConfig = variant.creationConfig ?: return@onVariants
                    toTransform(project, creationConfig)
                }
            }
        }
    }

    private fun toTransform(
        project: Project,
        creationConfig: ComponentCreationConfig
    ) {
        val task =
            project.tasks.registerTask(TransformClassesWithAsmTask.CreationAction(creationConfig))
        creationConfig.artifacts.forScope(ScopedArtifacts.Scope.ALL)
            .use(task)
            .toTransform(
                ScopedArtifact.CLASSES,
                { a -> a.inputJarsWithIdentity.inputJars },
                TransformClassesWithAsmTask::inputClassesDir,
                TransformClassesWithAsmTask::jarsOutputDir,
                TransformClassesWithAsmTask::classesOutputDir,
            )
    }
}