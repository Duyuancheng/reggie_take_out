//package com.dyc.reggie_take_out1.controller;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.dyc.reggie_take_out1.common.Result;
//import com.dyc.reggie_take_out1.entity.User;
//import com.dyc.reggie_take_out1.service.UserService;
//import com.dyc.reggie_take_out1.utils.SendMessages;
//import com.dyc.reggie_take_out1.utils.ValidateCodeUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.mail.MessagingException;
//import javax.servlet.http.HttpSession;
//import javax.swing.*;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/user")
//@Slf4j
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    /**
//     * 发送手机验证码
//     * @param user
//     * @return
//     */
//    @PostMapping("/sendMsg")
//    public Result<String> sendMsg(@RequestBody User user, HttpSession session) throws MessagingException {
//        //获取邮箱号
//        String phone = user.getPhone();
//        if(StringUtils.isNotEmpty(phone)){
//            //生成随机的四位验证码
//            String code = ValidateCodeUtils.generateValidateCode(6).toString();
//            SendMessages.Send("This is the subject of the email message",code,phone);
//
//            //需要将生成的验证码保存到session
//            session.setAttribute("code",code);
//            return Result.success("短信发送成功");
//        }
//
//        return Result.error("短信发送失败");
//    }
//
//    /**
//     * 移动端用户登录
//     * @param
//     * @return
//     */
//    @PostMapping("/login")
//    public Result<User> login(@RequestBody Map map, HttpSession session)  {
//        //获取手机号和验证码
//
//        String phone = map.get("phone").toString();
//        String code = map.get("code").toString();
////        从session中获取保存的验证码
//        Object attribute = session.getAttribute(code);
//        //进行验证码比对
//
//        if(attribute!=null && attribute.equals(code)){
//
//            //判断当前登录手机号是否为新用户，新用户自动完成注册到user表中
//            LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
//            queryWrapper.eq(User::getPhone,phone);
//            User user = userService.getOne(queryWrapper);
//            if (user==null){
//                user=new User();
//                user.setPhone(phone);
//                user.setStatus(1);
//                userService.save(user);
//            }
//            return Result.success(user);
//        }
//
//        return Result.error("登录失败");
//    }
//}


package com.dyc.reggie_take_out1.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dyc.reggie_take_out1.common.Result;
import com.dyc.reggie_take_out1.entity.User;
import com.dyc.reggie_take_out1.service.UserService;
import com.dyc.reggie_take_out1.utils.SendMessages;
import com.dyc.reggie_take_out1.utils.ValidateCodeUtils;
import com.dyc.reggie_take_out1.utils.SendCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user, HttpSession session) throws MessagingException {

//        if(StringUtils.isNotEmpty(userPhone)){
//            // 生成随机验证码
//            String code = ValidateCodeUtils.generateValidateCode(4).toString();
//            // 发送验证码
//            // SendCode.sendCode(code);
//            log.info("code:{}",code);
//            // 将生成的验证码保存到session中
//            // session.setAttribute(userPhone,code);
//
//            // 将生成的验证码保存到redis中，并且设置有效期，有效期为5分钟
//            redisTemplate.opsForValue().set(userPhone,code,5, TimeUnit.MINUTES);
//            return R.success("短信发送成功");
//        }
//        return R.error("短信发送失败");
        // 获取邮箱号
        String userPhone = user.getPhone();
        //获取随机的六位验证码
        String code=ValidateCodeUtils.generateValidateCode(6).toString();
        //发送短信验证码邮件
        SendMessages.Send("This is the subject of the email message",code,userPhone);
        //将生成的验证码保存到session中
        session.setAttribute("code",code);
        return Result.success("短信发送成功");

    }

    /**
     * 移动端用户登录
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody Map map, HttpSession session){
        log.info(map.toString());
        // 获取邮箱号
        String  phone = map.get("phone").toString();
        // 获取验证码
        String code = map.get("code").toString();
        // 从session中获取验证码

        Object codeInSession = session.getAttribute("code");
        // 从redis中获取缓存验证码
//        Object codeInSession = redisTemplate.opsForValue().get(phone);
        // 进行验证码对比（页面提交的验证码和session中保存的验证码对比
        if (codeInSession != null && codeInSession.equals(code)) {
            // 如果能够比对成功，说明登录成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if (user==null){
                // 判断当前用户的手机号是否为新用户，如果是新用户就自动为其创建账号
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            // 如果用户登录成功，则可以删除验证码
//            redisTemplate.delete(phone);

            return Result.success(user);
        }
        return Result.error("登录失败");
    }
}