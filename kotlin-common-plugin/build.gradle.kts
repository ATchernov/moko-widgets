/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_6
    targetCompatibility = JavaVersion.VERSION_1_6
}

dependencies {
    compileOnly(Deps.Libs.MultiPlatform.kotlinStdLib.android!!)

    compileOnly("org.jetbrains.kotlin:kotlin-script-runtime")
    compileOnly("org.jetbrains.kotlin:kotlin-reflect")
    compileOnly("org.jetbrains.intellij.deps:trove4j:1.0.20181211")
}
