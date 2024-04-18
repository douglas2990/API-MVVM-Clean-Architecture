// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    //id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
    kotlin("jvm") version "1.9.23"
    //id("com.google.dagger.hilt.android") version "2.45" apply false
    //id("com.google.dagger.hilt.android") version "2.42" apply false
    //id ("org.jetbrains.kotlin.android' version '1.5.30") apply false
}