package com.bill.util;

import java.util.Map;

/**
 * ThreadLocal工具類
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    // 防止實例化
    private ThreadLocalUtil() {
        throw new IllegalStateException("Utility class");
    }
    
    @SuppressWarnings("unchecked")
	public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public static Integer getUserId() {
        Map<String, Object> claims = get();
        return (Integer) claims.get("userId");
    }

}
