# Personal Website

My personal profile and CV generator built using [Kotlin Mutliplatform](https://kotlinlang.org/docs/multiplatform.html)

* **JS Target**: Static website built with [`kotlinx-html`](https://kotlinlang.org/docs/typesafe-html-dsl.html)
* **JVM Target**: CV PDF generator CLI using [`OpenPDF`](https://github.com/LibrePDF/OpenPDF)


## Prerequisites

* JDK 21+
* Gradle 8.x
* pixi

## Actions

### Generate site

```bash
# Run the development server (with hot reload)
./gradlew jsBrowserDevelopmentRun --continuous

# Build production bundle
./gradlew jsBrowserProductionWebpack
```

### Generate CV

```bash
# Generate CV with default output (btmonier_cv.pdf)
./gradlew jvmRun

# Generate CV with custom output path
./gradlew jvmRun --args="-o output/my_cv.pdf"
```

### Update Scholar metrics

```bash
# Initialize virtual environment
pixi install

# Update existing `scholar.json` data
pixi run metrics
```

