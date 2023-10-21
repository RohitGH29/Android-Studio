plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.ritech.fruitsandvegetables"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ritech.fruitsandvegetables"
        minSdk = 22
        targetSdk = 33
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // firebase analytical
   // implementation("com.google.firebase:firebase-analytics:21.3.0")
    //admob ad
   implementation ("com.google.android.gms:play-services-ads:22.2.0")
    //crash analysis
   // implementation ("com.google.firebase:firebase-crashlytics:18.4.0")
    // for message and notification send
    implementation ("com.google.firebase:firebase-messaging:23.2.0")
    // App Update dependency
    implementation ("com.google.android.play:core:1.10.3")
    // lottie files animation
    implementation ("com.airbnb.android:lottie:6.0.1")


}