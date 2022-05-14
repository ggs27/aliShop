package com.jesper.seckill.vo;

import com.jesper.seckill.validator.IsMobile;
import javax.validation.constraints.NotNull;

/**
 *
 * @author guoguisong
 * @date 2022/5/14
 */

public class RigisterVo {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String password2;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }


}
