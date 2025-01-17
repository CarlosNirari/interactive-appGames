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
        versionCode = 5
        versionName = "1.5.1"

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

    //Mock o Mokito
    testImplementation(libs.mockk)
    //CorrerTesting
    testImplementation(libs.androidx.core.testing)
    //Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.common)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.navigation.runtime.ktx.v276)
    //Constraint
    implementation(libs.androidx.constraintlayout.compose)
    //Material | UI
    implementation(libs.androidx.material)
    implementation(libs.ui)
    //Coil
    implementation(libs.coil.compose)
    //Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    /*Corrutinas*/
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    /*FoundFondation*/
    implementation (libs.androidx.foundation)
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