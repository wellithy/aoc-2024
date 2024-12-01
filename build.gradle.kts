plugins {
    kotlin("jvm") version "2.1+"
}
repositories {
    mavenCentral()
}
sourceSets {
    main {
        kotlin.srcDir("src")
    }
}
tasks {
    wrapper {
        gradleVersion = "8.11+"
    }
}
