package pl.pollubmy.server.security;

public class SecurityParameters {

    public static String SECRET = "pollubmy";
    public static Long EXPIRATION_TIME  = 864_000_000L;
    public static String HEADER = "Authorization";
    public static String PREFIX = "Token ";
}