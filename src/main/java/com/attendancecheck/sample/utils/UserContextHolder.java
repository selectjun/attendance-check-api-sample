package com.attendancecheck.sample.utils;

import org.springframework.util.Assert;

/**
 * UserContext Holder
 */
public class UserContextHolder {

    /**
     * UserContext
     */
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();

    /**
     * UserContext 조회
     * @return UserContext
     */
    public static UserContext getContext(){
        UserContext context = userContext.get();

        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);

        }

        return userContext.get();
    }

    /**
     * UserContext 설정
     * @param context UserContext
     */
    public static void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        userContext.set(context);
    }

    /**
     * UserContext ThreadLocal로 생성
     * @return UserContext(ThreadLocal)
     */
    public static final UserContext createEmptyContext(){
        return new UserContext();
    }

}
