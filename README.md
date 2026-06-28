# HappsStoreSDK
本仓库为接入HappsStore互联示例
<br>官方QQ群：1036365995

# **1.添加应用**
在接入SDK前您需要先在HappsStore互联平台内添加应用，来获取AppID 和 AppKey

请访问：[HappsStore互联中心](https://store.mlykj.com/open/)

# **2.引入SDK**
下载本仓库中的aar文件进行导入
<br>在 app 的 build.gradle 文件中找到 dependencies{} 代码块，并在其中加入以下语句：
<br>
```Java
implementation("com.google.code.gson:gson:2.10.1")//解析json 必须
implementation(files("libs/HappsStoreSDK-1.0.aar"))//SDK主体 必须
```

# **3.配置AndroidManifest.xml**
在application中配置activity
<br>
```Java
<application
----其他参数----
<activity
    android:name="页面"
    android:exported="true"
    android:launchMode="singleTask"
    android:windowSoftInputMode="adjustResize">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="保持和互联设置一样"
            android:host="保持和互联设置一样"/>
    </intent-filter>
</activity>
</application>
```

# **4.初始化SDK**
在 Application 的实现类中的 onCreate 方法中进行配置
<br>
```Java
//初始化SDK
SDK.init("app_xxx", "key_xxx");
```
<br>
配置完毕后就可以进行调用了。

# **5.拉起互联登录**
在 需要拉起登录的页面代码中进行配置
<br>
```Java
import com.happsstore.sdk.SDK;

SDK.launchLogin(this); //activity
SDK.launchLogin(requireActivity());//fragment

//回调代码
 @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        SDK.fetchUserInfo(intent, new SDK.UserInfoCallback() {
            @Override
            public void onSuccess(SDK.UserInfo userInfo) {
                runOnUiThread(() -> {
                    //登录成功
                    //用户昵称 serInfo.getNickname();
                    //用户头像 serInfo.getUsertx();
                    //用户签名 userInfo.getSignature();
                });
            }

            @Override
            public void onError(String errorMsg) {
                runOnUiThread(() -> {
                    //登录失败
                });
            }
        });
    }
```
