# ServiceAndroid
Service framework for android

## Download
```gradle
repositories {
    maven {
        url 'https://dl.bintray.com/adef145/teslacode/'
    }
}

...

dependencies {
    compile 'com.teslacode.service:service:0.1.1'
}
```

Additional setup in Project Gradle
```gradle
allprojects {
    repositories {
        ...
        // For DBFlow
        maven {
            url "https://www.jitpack.io"
        }
    }
}
```
