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
