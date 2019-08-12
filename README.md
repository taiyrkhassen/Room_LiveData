dependencies: 

 def lifecycle_version = "1.1.1"

    // ViewModel and LiveData
    implementation "android.arch.lifecycle:extensions:$lifecycle_version"

    kapt "android.arch.lifecycle:compiler:$lifecycle_version"
    // For Kotlin use kapt instead of annotationProcessor

    // optional - ReactiveStreams support for LiveData
    implementation "android.arch.lifecycle:reactivestreams:$lifecycle_version"

    // optional - Test helpers for LiveData
    testImplementation "android.arch.core:core-testing:$lifecycle_version"

    def room_version = "1.1.1"

    implementation "android.arch.persistence.room:runtime:$room_version"
    kapt "android.arch.persistence.room:compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor

    // optional - RxJava support for Room
    implementation "android.arch.persistence.room:rxjava2:$room_version"

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation "android.arch.persistence.room:guava:$room_version"

    // Test helpers
    testImplementation "android.arch.persistence.room:testing:$room_version"
