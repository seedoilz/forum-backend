package com.wanted.project.model.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    /** 用户名 **/
    private String name;
    /** 密码明文 **/
    private String password;
    /** 电子邮箱 **/
    private String email;
}
