package org.sid.secservice.sec;

public class JWTUtil {
    public static final String SECRET = "mySecret123";
    public static final String AUTH_HEADER = "Authorization";
    public  static final long EXPIRE_ACCESS_TOKEN=60*60*1000;
    public  static final long EXPIRE_REFRESH_TOKEN=30*60*1000;
    public  static final String PREFIX = "Bearer ";
}
