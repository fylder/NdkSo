//
// Created by fylder on 2019/3/19.
//

#ifndef NDKTEST_ANDROID_LOG_H
#define NDKTEST_ANDROID_LOG_H

#include <android/log.h>

#define LOG_TAG    "native_log"
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN, LOG_TAG, __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

#endif //NDKTEST_ANDROID_LOG_H
