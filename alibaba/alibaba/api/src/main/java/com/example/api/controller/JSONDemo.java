package com.example.api.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
@Data
public class JSONDemo {

    @JsonProperty("abc")
    private String abc;
}
