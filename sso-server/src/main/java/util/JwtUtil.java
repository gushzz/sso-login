package util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//JWT工具类
public class JwtUtil {

    private static  final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQKsdfkjsdrow32234545fdf>?N<:{LWPW";
    private static final String EXP = "exp";
    private static final String PAYLOAD = "payload";
    private static final long MAX_TIME = 1000 * 60 * 30;

    public static <T> String encode(T object){
        return encode(object, MAX_TIME);
    }

    //加密
    private static <T> String encode(T object, long maxTime){
        try{
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String ,Object> data = new HashMap<String ,Object>(10);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(object);
            data.put(PAYLOAD, jsonString);
            data.put(EXP, System.currentTimeMillis() + maxTime);
            return signer.sign(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //解密
    public static<T> T decode(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String,Object> claims = verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long)claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String)claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


}
