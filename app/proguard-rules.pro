# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
# НА ОБНОВУ ТОЛЬКО РЕЛИЗ BY EDGAR 3.0
# made by EDGAR 3.0
-keep class com.nvidia.** {*;}
-keep class com.wardrumstudios.** {*;}
-keep class connect.mta.launcher.activity.MainActivity {*;}
-keepclassmembers class com.nvidia.** {*;}
-keepclassmembers class com.wardrumstudios.** {*;}
-keepclassmembers class connect.mta.launcher.activity.MainActivity {*;}
-keepclasseswithmembernames class * {
     native <methods>;
}
# EDGAR 3.0

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile
#-dontobfuscate

# Remove unused resources
-dontwarn com.nvidia.**
-dontwarn com.wardrumstudios.**
-dontwarn connect.mta.launcher.activity.MainActivity

# Additional optimizations
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-printmapping
