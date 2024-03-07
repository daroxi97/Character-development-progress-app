import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}


android {
    signingConfigs {
        create("release") {
            try {
                val keystorePropertiesFile = rootProject.file("keystore.properties")
                val keystoreProperties = Properties()
                keystoreProperties.load(keystorePropertiesFile.inputStream())

                storeFile = File(keystoreProperties.getProperty("storeFile"))
                keyAlias = keystoreProperties.getProperty("keyAlias")
                storePassword = keystoreProperties.getProperty("storePassword")
                keyPassword = keystoreProperties.getProperty("keyPassword")
            }
            catch (e: java.io.FileNotFoundException) {
                // Handle the case where keystore.properties file is not found
                println("keystore.properties file not found. Please create the file.")
            }
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
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.room:room-ktx:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.navigation:navigation-compose:2.8.0-alpha03")
    implementation ("androidx.compose.material3:material3:1.3.0-alpha01")
    implementation ("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.runtime:runtime-livedata")


    /*implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
     */

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