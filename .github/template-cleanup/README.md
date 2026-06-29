# %NAME%

![Build](https://github.com/%REPOSITORY%/workflows/Build/badge.svg)

## Template ToDo list
- [x] Create a new [Gradle Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Adjust the plugin group, version, plugin id, display name, description, and tags in [plugin/build.gradle.kts](./plugin/build.gradle.kts).
- [ ] Adjust the package name and implementation class in [plugin](./plugin).
- [ ] Adjust the plugin description in `README`.
- [ ] Publish the Gradle plugin manually for the first time.
- [ ] Click the <kbd>Watch</kbd> button on the top of the [Gradle Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Gradle plugin description -->
This Gradle plugin is going to be your implementation of the brilliant ideas that you have.
<!-- Gradle plugin description end -->

## Installation

You can add the Gradle plugin to your top-level build script using the following configuration:

### `plugins` block:

```diff
plugins {
+  id("%PLUGIN_ID%") version "<version>" apply false
}

subprojects {
  apply(plugin = "%PLUGIN_ID%")
}
```
or via the

### `buildscript` block:
```groovy
buildscript {
  dependencies {
    classpath "%GROUP%:%NAME%:<version>"
  }
}

apply plugin: "%PLUGIN_ID%"
```

---
based on the [Gradle Plugin Template][template].

[template]: https://github.com/5peak2me/gradle-plugin-template
