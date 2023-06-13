# **Android Studio Project Sample With Caller SDK Integration:**

## **This Project provides an easy walk through for integrating Calldorado Caller SDK 7.0 into your app**

### **Installation**
Download:
git clone https://github.com/Calldorado-com/cdo-7-integration.git

### **Databinding and SDKVersions**

The Caller SDK makes use of data bindings so in order to make it work we need to enable it for the app. 
To configure your app to use data binding, add the dataBinding element to your build.gradle file in the app module, 
as shown in the following example:
```
android {
...
dataBinding {
enabled = true
}
}

//add this below dependency
implementation 'com.android.databinding:compiler:3.1.4'
```




