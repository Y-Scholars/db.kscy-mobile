package org.kscy.db.kscydatabase.model;

/**
 * Created by pyh42 on 2017-07-27.
 */

public class Data {
    private String scheme;

    private String token;

    private String email;

    private String created_at;

    private String user_id;

    private String expired_at;

    public String getScheme ()
    {
        return scheme;
    }

    public void setScheme (String scheme)
    {
        this.scheme = scheme;
    }

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getExpired_at ()
    {
        return expired_at;
    }

    public void setExpired_at (String expired_at)
    {
        this.expired_at = expired_at;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [scheme = "+scheme+", token = "+token+", email = "+email+", created_at = "+created_at+", user_id = "+user_id+", expired_at = "+expired_at+"]";
    }
}
