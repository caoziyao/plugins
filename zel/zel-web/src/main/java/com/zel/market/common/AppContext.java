package com.zel.market.common;

import com.zel.dbmanager.entity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * 上下文
 */
@Getter
@Setter
public class AppContext {
    private User user;
}
