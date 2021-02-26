package com.cy;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class MD5Tests {
    @Test
    void testMD501(){
        String password="123456";
        String salt= UUID.randomUUID().toString();
        SimpleHash simpleHash=//shiro框架中的一个加密API
                new SimpleHash("MD5",password,salt,1);
        password=simpleHash.toHex();
        System.out.println(password);
    }
}
