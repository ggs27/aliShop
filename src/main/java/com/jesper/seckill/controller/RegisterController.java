package com.jesper.seckill.controller;

import com.jesper.seckill.result.Result;
import com.jesper.seckill.service.UserService;
import com.jesper.seckill.vo.RigisterVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 *
 * @author guoguisong
 * @date 2022/6/10
 */

@Controller
@RequestMapping("/register")
public class RegisterController {

    private static Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/to_register")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/do_register")
    @ResponseBody
    public Result<String> doRegister(HttpServletResponse response, @Valid RigisterVo rigisterVo) {//加入JSR303参数校验
        log.info(rigisterVo.toString());
        userService.register(response, rigisterVo);
        return Result.success("注册成功");
    }

}
