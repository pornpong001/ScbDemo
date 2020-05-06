package com.example.services;

import com.example.model.Login;
import com.example.util.StrongAES;
import com.example.util.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserAuten {
    @Autowired
    UserRepository userRepository;


        @Transactional
        public int getAuten(Login datalogin) throws Exception {
            log.info("Username : "+datalogin.getUsername());
            //log.info("Password : "+datalogin.getPassword());
            String encryptedString = StrongAES.encrypt(datalogin.getPassword()) ;
            log.info("PasswordEncrypt : "+encryptedString);
            datalogin.setPassword(encryptedString);
           // String decryptedString = AES.decrypt(encryptedString, secretKey) ;
            log.info("getUserAndPassword");
            Login user = userRepository.findByUsername(datalogin.getUsername());
            if(null != user && user.getPassword().equals(datalogin.getPassword())){
                log.info("Have Username And Password in Database : "+String.valueOf(user));
                log.info("Login Success : "+user.getUsername());
                return HttpStatus.OK.value();
            }else {
                log.info("Login Fail or not found user in Database : " + datalogin.getUsername());
                return HttpStatus.NOT_FOUND.value();
            }
           // log.info(bCryptPasswordEncoder.encode(datalogin.getPassword()));

        }

    /*private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }*/

    /*public static void main(String[] args)
    {
        final String secretKey = "ssshhhhhhhhhhh!!!!";

        String originalString = "howtodoinjava.com";
        String encryptedString = StrongAES.encrypt(originalString,secretKey) ;
        String decryptedString = StrongAES.decrypt(encryptedString,secretKey) ;

        System.out.println(secret);
        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }*/
}
