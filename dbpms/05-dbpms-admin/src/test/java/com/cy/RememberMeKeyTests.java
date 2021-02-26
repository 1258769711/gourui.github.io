package com.cy;

import org.apache.shiro.codec.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class RememberMeKeyTests {
    //生成Base64算法加密时使用的密钥
    @Test
    void testGeneratorKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
        SecretKey secretKey = keyGenerator.generateKey();
        String key=Base64.encodeToString(secretKey.getEncoded());
        System.out.println(key);
    }
}
