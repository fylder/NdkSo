#include <jni.h>
#include <string>
#include "android_log.h"

//当动态库被加载时这个函数被系统调用
extern "C"
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("JNI_OnLoad 上低音号");
    JNIEnv *env;

    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
    return JNI_VERSION_1_6;
}

//当动态库被卸载时这个函数被系统调用
extern "C"
JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *vm, void *reserved) {
    LOGW("JNI_OnUnload 上低音号");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_fylder_ndk_demo_jni_JniTools_stringFromJNI(JNIEnv *env, jobject thiz) {
    std::string hello = "上低音号";
    return env->NewStringUTF(hello.c_str());
}