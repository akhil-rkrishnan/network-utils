# Network Utilities

This is a utility library for network calls.

I use all the functions included in this library for network calls. I was importing this as a separate module for each time when i work on a project. Now it's a library, so that if any one needs these functionalities in their project, they can directly import using the gradle dependency from jitpack.

### Network Extension
> Perform Api call
  ```
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient
                    .Builder().readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .build()
            )
            .build()

        val retrofitApi = retrofit.create(ApiInterface::class.java)
        var response: ApiResult<FreeApiModel> //FreeApiModel -> sample response model
        lifecycleScope.launch {
            response = initApiCall {
                ApiResult.Success(retrofitApi.getAllData())
            }

            response.ifSuccess { freeApiModel ->
                // do the operations after api success 
            }
            response.ifFailed { code, message ->
                // do the operations on api failed 
            }
        }
  }

interface ApiInterface {
    @GET("entries")
    suspend fun getAllData(): FreeApiModel
}
  ``` 
- ApiResult<T> --> Generic class which accepts all the types of data
- ApiResult.Success(T) --> This method will return the response model
  - ApiResult<T>.ifSuccess {} : This will be executed if the api call is success (code: 200)
  - ApiResult<T>.ifFailed { code, message -> } : This will be executed if the api call is failed


# Download
> In your settings.gradle file
```
dependencyResolutionManagement {
    ...
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }    
    }
}
```

> In your app.gradle file
```
dependencies {
    ....
    .....  
    implementation 'com.github.akhil-rkrishnan:network-utils:1.0.0'
    
    //Note: Please check the release tag for the latest version in the repo and replace the version with the latest tag
}
```

> In your project level build.gradle file
```
...
buildscript {
    ext {
        compile_sdk_version = 31 // mention your compile sdk version
        min_sdk_version = 21 // mention your min sdk version
        target_sdk_version = compile_sdk_version // mention your target sdk version
    }
}
...
```
