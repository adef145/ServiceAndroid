# ServiceAndroid
[ ![Download](https://api.bintray.com/packages/adef145/maven/service/images/download.svg) ](https://bintray.com/adef145/maven/service/_latestVersion) [![](https://jitpack.io/v/adef145/ServiceAndroid.svg)](https://jitpack.io/#adef145/ServiceAndroid)

Service framework for android

We use retrofit and java rx for our base code.

## Download
```gradle
repositories {
    maven {
        url 'https://dl.bintray.com/adef145/maven/'
    }
    
    maven {
        url 'https://jitpack.io'
    }
}

...

dependencies {
    compile 'com.adefruandta.service:service:0.6.0'
    
    // for jitpack
    compile 'com.github.adef145:ServiceAndroid:0.6.0'
}
```

## How To Use
First, extends the `RetrofitBuilder` for setup `baseUrl`
```kotlin
class CustomRetrofitBuilder: RetrofitBuilder {
    override protected var baseUrl: String = "https://api.com/"
}
```
and then register `CustomRetrofitBuilder` when `Application.onCreate`. And if you want to setup the `Preferences`. Just register in `PreferencesBuilder`
```kotlin
class CustomApplication: Application {

    ...

    override fun onCreate() {
        ...
        RetrofitBuilder.builder = CustomRetrofitBuilder()
        PreferencesBuilder.preferences = PreferenceManager.getDefaultSharedPreferences(this)
    }
}
```

After that, create your interface for Api. And then extends `Repository` for the logic and override some function.
```kotlin
class CustomRepository: Repository<CustomApi> {
    
    ...
    
    override fun onCreateApi(retrofit: Retrofit): CustomApi? {
        return retrofit.create(CustomApi::java.class)
    }
}
```
And you ready for using `Repository`
