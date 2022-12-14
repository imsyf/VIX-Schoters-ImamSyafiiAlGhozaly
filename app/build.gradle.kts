plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "im.syf.nuice"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(Google.android.material)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.lifecycle.liveDataKtx)
    implementation(AndroidX.navigation.fragmentKtx)
    implementation(AndroidX.navigation.uiKtx)
    implementation(AndroidX.room.runtime)
    implementation(AndroidX.room.ktx)
    kapt(AndroidX.room.compiler)
    implementation("com.airbnb.android:epoxy:_")
    implementation("com.airbnb.android:epoxy-databinding:_")
    kapt("com.airbnb.android:epoxy-processor:_")
    implementation("io.coil-kt:coil:_")
    implementation(Square.moshi)
    implementation(Square.moshi.adapters)
    kapt(Square.moshi.kotlinCodegen)
    implementation(Square.okHttp3.okHttp)
    implementation(Square.retrofit2.converter.moshi)
    implementation(Square.retrofit2.retrofit)
    implementation("co.infinum:retromock:_")
    testImplementation(Testing.junit4)
    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
}
