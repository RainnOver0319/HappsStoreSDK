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
implementation(files("libs/HappsStoreSDK-1.0.aar"))
```

# **3.初始化SDK**
在 Application 的实现类中的 onCreate 方法中进行配置
<br>
```Java
//初始化SDK
SDK.init("app_xxx", "key_xxx");
```
<br>
配置完毕后就可以进行调用了。

# **4.拉起互联登录**
在 需要拉起登录的页面代码中进行配置
<br>
```Java
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
    Uri data = intent.getData();
    if (data != null && "设置的scheme".equals(data.getScheme()) && "设置的host".equals(data.getHost())) {
        SDK.fetchUserInfo(intent, new SDK.UserInfoCallback() {
            @Override
            public void onSuccess(SDK.UserInfo userInfo) {
                // 成功获取用户信息
                Log.d("Login", "用户名: " + userInfo.getUsername());
                Log.d("Login", "用户昵称: " + userInfo.getNickname());
                Log.d("Login", "头像: " + userInfo.getUsertx());
                Log.d("Login", "签名: " + userInfo.getSignature());
                // 你的逻辑
                runOnUiThread(() -> {

                });
            }

            @Override
            public void onError(String errorMsg) {
                Log.e("Login", "获取用户信息失败: " + errorMsg);
                runOnUiThread(() -> {
                    //登录失败
                });
            }
        });
    }
}
```
