plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.karina0088.studenttasktracker2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.karina0088.studenttasktracker2"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {

    // =========================
    // CORE ANDROID
    // =========================
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // =========================
    // COMPOSE (FIXED BOM ONLY)
    // =========================
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)

    debugImplementation(libs.androidx.compose.ui.tooling)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.8")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.8")

    // =========================
    // NAVIGATION (KEEP ONE ONLY)
    // =========================
    implementation(libs.navigation.compose)
    implementation("androidx.navigation:navigation-compose:2.8.5")

    // =========================
    // VIEWMODEL
    // =========================
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    // =========================
    // ROOM (REMOVE DUPLICATE CLEANED)
    // =========================
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // =========================
    // DATASTORE
    // =========================
    implementation(libs.datastore.preferences)

    // =========================
    // COIL IMAGE
    // =========================
    implementation(libs.coil.compose)

    // =========================
    // SUPABASE
    // =========================
    implementation(libs.supabase.core)
    implementation(libs.supabase.auth)
    implementation(libs.supabase.postgrest)
    implementation(libs.supabase.storage)

    // =========================
    // KTOR
    // =========================
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.serialization)

    // =========================
    // GOOGLE LOGIN (Credential Manager)
    // =========================
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    // =========================
    // JSON
    // =========================
    implementation(libs.kotlinx.serialization.json)

    // =========================
    // TEST
    // =========================
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}