import org.jetbrains.kotlin.konan.properties.Properties
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.lampcontrolapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lampcontrolapplication"
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
        debug {
            isMinifyEnabled = false
            isDebuggable = true

            val properties = getLocalProperties()
            buildConfigField("String", "BASE_URL", "\"${properties.getProperty("base_url")}\"")
        }
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.majorVersion
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.dynamic.features.fragment)

    //View binding
    implementation(libs.viewbindingpropertydelegate.full)

    // dagger2 DI
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    // retrofit networking
    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.retrofit2.converter.gson)
}

fun getLocalProperties():Properties {
    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())
    return properties
}