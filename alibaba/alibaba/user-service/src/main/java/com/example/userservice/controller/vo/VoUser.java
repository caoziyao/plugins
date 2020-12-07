package com.example.userservice.controller.vo;

import com.example.userservice.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * Description: 用户vo
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/26
 */
@Data
//@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoUser {

//    @Tolerate
    public VoUser() {
    }

    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String token;

    public VoUser(User user) {
        id = user.getId();
        name = user.getUsername();
        password = user.getPassword();
        age = user.getAge();
    }
}
