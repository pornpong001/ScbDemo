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
public class UserManage {
    @Autowired
    UserRepository userRepository;


    @Transactional
    public int createUser(Login datalogin) throws Exception {
        try {
            log.info("Username : " + datalogin.getUsername());
            String encryptedString = StrongAES.encrypt(datalogin.getPassword());
            log.info("PasswordEncrypt : " + encryptedString);
            datalogin.setPassword(encryptedString);
            // String decryptedString = AES.decrypt(encryptedString, secretKey) ;
            log.info("getUserAndPassword");
            datalogin.setDateOfBirth(SetFormat(datalogin.getDateOfBirth()));
            //datalogin.setDateOfBirth(setDateFormat(datalogin.getDateOfBirth()));
            Login user = userRepository.save(new Login(datalogin.getUsername(), datalogin.getPassword(), datalogin.getDateOfBirth()));
            log.info("DateOfBirth : " + user.getDateOfBirth());
            //return HttpStatus.OK.value();
        }catch (final Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        return HttpStatus.OK.value();
    }

    @Transactional
    public int deleteUser(Login datalogin) throws Exception {
        try{
            log.info("Delete User : "+datalogin.getUsername());
            userRepository.deleteByUsername(datalogin.getUsername());
        }catch (final Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        return HttpStatus.OK.value();

    }
    public String SetFormat(String data){
       String[] dataSplit = data.split("/");
        return  dataSplit[2]+"-"+dataSplit[1]+"-"+dataSplit[0];

    }
    /*public String setDateFormat(String date) throws ParseException {
        String pattern = "yyyy-dd-MM";
        DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date gg = simpleDateFormat.parse(date);
        String dateFormate = simpleDateFormat.format(gg);
        return dateFormate;
    }*/

    @Transactional
    public String getOrder(Login datalogin) throws Exception {
        try {

           // log.info("OrderNo : " + datalogin.getOrdersNo());
            log.info("getPrine from Order");
            Login user = userRepository.findByUsername(datalogin.getUsername());
            if (null != user /*&& user.getOrdersNo().equals(datalogin.getOrdersNo())*/) {
                log.info("Have Price in Database : " + user.getPrice());
                return user.getPrice();
            } else {
                log.info("not found Prine  in Database : " + datalogin.getUsername());
                return "0.00";
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
        }
        // log.info(bCryptPasswordEncoder.encode(datalogin.getPassword()));
        return "0.00";
    }

}
