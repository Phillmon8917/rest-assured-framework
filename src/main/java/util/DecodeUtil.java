package util;

import java.util.Base64;

public class DecodeUtil {

    public static String decode64(String encodedString){
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encodedString.getBytes()));
    }
}
