package com.hms;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPwd = "test";
        String encodedpwd = encoder.encode(rawPwd);
        System.out.println(encodedpwd);
    }
}
