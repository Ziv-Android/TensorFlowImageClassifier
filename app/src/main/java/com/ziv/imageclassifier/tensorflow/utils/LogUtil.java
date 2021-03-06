package com.ziv.imageclassifier.tensorflow.utils;

import android.util.Log;

import java.util.Locale;

/**
 * @author ziv on 18-4-8.
 */

public class LogUtil {
    public static void d(String msg) {
        Log.d(getTag(), msg);
    }

    public static void w(String msg) {
        Log.w(getTag(), msg);
    }

    public static void w(String msg, Throwable throwable) {
        Log.w(getTag(), msg, throwable);
    }

    public static void e(String msg) {
        Log.e(getTag(), msg);
    }

    public static void processLog(String msg) {
        Log.d("ProcessLog", getTag() + msg);
    }

    public static void stateLog(String msg) {
        Log.d("StateLog", getTag() + msg);
    }

    public static void timeLog(long time) {
        Log.d("TimeTest", getTag() + " - time is " + (System.currentTimeMillis() - time) + " ms.");
    }

    private static String getTag() {
        String className;
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                className = trace[i].getClassName();
                className = className.substring(className.lastIndexOf('.') + 1);
                stringBuilder.append(className).append(".");
                stringBuilder.append(trace[i].getMethodName()).append(" --> ");
                break;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 获取调用方法和行号
     */
    private static String getNumber() {
        Thread thread = Thread.currentThread();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        String[] classNames = stackTrace[4].getClassName().split("\\.");
        return String.format(Locale.CHINA, "[Thread:%s(%d) %s.%s(Line: %d)]", thread.getName(),
                thread.getId(), classNames[classNames.length -1], stackTrace[4].getMethodName(), stackTrace[4].getLineNumber());
    }
}
