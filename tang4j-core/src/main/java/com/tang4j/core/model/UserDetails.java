package com.tang4j.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetails {
    /**
     * 用户id
     */
    private String userID;
    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户名
     */
    private String userName;

    public UserDetails(String userID, String account, String userName) {
        this.userID = userID;
        this.account = account;
        this.userName = userName;
    }
}
