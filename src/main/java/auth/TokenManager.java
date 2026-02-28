package auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenManager {
    private static final Map<String,String> userToken = new ConcurrentHashMap<>();

    private TokenManager(){}

    public static String getToken(String username){
        return userToken.get(username);
    }

    public static String setToken(String username,String token){
        return userToken.put(username,token);
    }

    public static String hasToken(String username){
        return userToken.get(username);
    }

    public static void removeToken(String userName){
        userToken.remove(userName);
    }
}
