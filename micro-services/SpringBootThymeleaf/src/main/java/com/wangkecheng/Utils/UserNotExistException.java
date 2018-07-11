package com.wangkecheng.Utils;

/**
 * Created by Kanson on 2018/7/13.
 */
public class UserNotExistException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    public UserNotExistException(String id) {
        super("user not exist");
        this.id=id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}