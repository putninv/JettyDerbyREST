package com.putnin.model;

/**
 * App user.
 *
 * @author putnin.v@gmail.com
 */
public class User {
    /**
     * User id.
     */
    private Long id;

    /**
     * User phone.
     */
    private String phone;

    public User(){
    }

    public User(Long id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
