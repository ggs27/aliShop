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
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

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

    @Override
    public String toString() {
        return "RigisterVo{" +
                "mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
