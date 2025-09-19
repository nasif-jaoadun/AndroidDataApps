plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.jnasif.androiddataapps"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jnasif.androiddataapps"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment:2.8.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.squareup.moshi:moshi-kotlin:1.8.0")

    val retrofit2_version = "2.6.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit2_version")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofit2_version")

    val coroutines_version = "1.2.1"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    val glide_version = "4.16.0"
    implementation ("com.github.bumptech.glide:glide:$glide_version")

    val room_version = "2.8.0"
    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.room:room-ktx:$room_version") // For Kotlin extensions and coroutine support
    // If you're using Kotlin Annotation Processing (kapt) instead of KSP
    kapt ("androidx.room:room-compiler:$room_version")
}