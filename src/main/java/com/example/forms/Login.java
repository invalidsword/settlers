package com.example.forms;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Login {
    @NotNull
    @Size(min=4, max=35)
    private String username;
    @NotNull
    @Size(min=4, max=35)
    private String password;

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
