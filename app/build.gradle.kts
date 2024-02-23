plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}


android {
    signingConfigs {
        create("release") {
            storeFile =
                file("C:\\Users\\dan_r\\AndroidStudioProjects\\CharacterDevelopment\\characterdevelopmentkey.keystore")
            keyAlias = "characterdevelopmentalias"
            storePassword = "hoops0410"
            keyPassword = "hoops0410"
        }
    }
    namespace = "com.example.characterDevelopment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.characterDevelopment"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        resourceConfigurations += listOf("es", "ca", "en")



        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.activity:activity-ktx:1.8.1")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation ("com.google.android.material:material:1.10.0")

    implementation ("androidx.compose.runtime:runtime")
    implementation ("androidx.compose.foundation:foundation")
    implementation ("androidx.compose.material:material")
    implementation ("androidx.compose.material:material-icons-extended")
    implementation ("androidx.activity:activity-compose:1.8.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    kapt ("android.arch.lifecycle:compiler:1.1.0")
    implementation ("android.arch.persistence.room:runtime:1.0.0")
    kapt ("android.arch.persistence.room:compiler:1.0.0")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.navigation:navigation-compose:2.7.0-rc01")


    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("androidx.datastore:datastore:1.0.0")

    implementation ("androidx.navigation:navigation-compose:2.5.1")

    implementation ("androidx.compose.material3:material3:1.2.0-alpha02")


    implementation("androidx.compose.runtime:runtime-livedata")





    debugImplementation ("androidx.compose.ui:ui-tooling")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
kapt {
    correctErrorTypes = true
}