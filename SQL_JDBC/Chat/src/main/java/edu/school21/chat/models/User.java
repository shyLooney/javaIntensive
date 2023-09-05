package edu.school21.chat.models;

import java.util.*;
public class User
{
    private long id;
    private String login;
    private String password;
    private List<Chatroom> createdChatroomList;
    private List<Chatroom> socializesChatroomList;

    public User(long id, String login, String password, List<Chatroom> createdChatroomList, List<Chatroom> socializesChatroomList)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdChatroomList = createdChatroomList;
        this.socializesChatroomList = socializesChatroomList;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedChatroomList() {
        return createdChatroomList;
    }

    public List<Chatroom> getSocializesChatroomList() {
        return socializesChatroomList;
    }

    @Override
    public String toString()
    {
        return "{ Id = \"" + id + "\" Login = \"" + login + "\" Password = \"" + password +
            "\" createdChatroomList = \"" + createdChatroomList +
            "\" socializesChatroomList = \"" + socializesChatroomList + "\" }";
    }

    @Override
    public boolean equals(Object object)
    {
        User user = (User)object;
        if (id == user.getId() && login.equals(user.getLogin()) && password.equals(user.getPassword()) &&
                createdChatroomList.equals(user.getCreatedChatroomList()) &&
                socializesChatroomList.equals(user.getSocializesChatroomList()))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdChatroomList, socializesChatroomList);
    }
}