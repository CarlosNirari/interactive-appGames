plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.google.dagger.hilt)
}

android {
    namespace = "com.interactive.appgames"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.interactive.appgames"
        minSdk = 29
        targetSdk = 34
        versionCode = 4
        versionName = "1.4.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.navigation:navigation-common:2.7.6")
    implementation("androidx.navigation:navigation-common-ktx:2.7.6")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.6")
    //Constraint
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    //Material | UI
    implementation("androidx.compose.material:material:1.3.0")
    implementation("androidx.compose.ui:ui:1.3.0")
    //Coil
    implementation("io.coil-kt:coil-compose:2.0.0")
    //Glide
    implementation ("com.github.bumptech.glide:glide:4.15.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.0")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    /*Corrutinas*/
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //ROOM KTX
    implementation(libs.androidx.room)
    ksp(libs.androidx.room.compiler)
    //Dagger Hilt
    implementation(libs.google.dagger.hilt)
    kapt(libs.google.dagger.hilt.compiler)
}

kapt {
    correctErrorTypes = true
}