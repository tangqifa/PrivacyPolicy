## 前言

[工信部通报2020年第二批侵害用户权益行为APP](https://mp.weixin.qq.com/s/DoLUSBjjZv5HCUsjxdIPDQ)

为解决工信部用户隐私政策的要求，在用户同意隐私协议条款之前，不能擅自收集各种用户信息。

因此，我们APP启动前，不应该去做任何读取系统信息动作，虽然我们应用本身不会去读取系统信息相关信息，但是很多第三方SDK,在APP启动的时候去初始化，导致会请求一些不必要的权限和一些手机信息的采集。

为彻底解决这个问题，我们有必要在我们应用启动前，去弹出用户隐私协议弹框，让用户先同意，我们再打开应用，故而有这个项目。

## 原理

采用两个进程，用户隐私协议弹框单独一个进程，并作为启动Activity使用，并在APP里面判断是否是应用主进程，选择初始化相关SDK动作

```java
 @Override
    public void onCreate() {
        super.onCreate();
        mAppContextTmp = this;
        if (isProcessValid()) {
            mContext = this;
            Log.d("tag", "主工程初始化oncreate");
        } else {
            Log.d("tag", "隐私协议oncreate");
        }
    }
```

