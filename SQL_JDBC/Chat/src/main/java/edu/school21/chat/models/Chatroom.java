package edu.school21.chat.models;

import java.util.*;

public class Chatroom
{
    private long id;
    private String name;
    private User owner;
    private List<Message> messageList;

    public Chatroom(long id, String name, User owner, List<Message> messageList)
    {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messageList = messageList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    @Override
    public String toString()
    {
        return "{ Id = \"" + id + "\" Name = \"" + name + "\" Owner = \"" + owner.getId() +
                "\" messageList = \"" + messageList + "\" }";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, messageList);
    }

    @Override
    public boolean equals(Object object)
    {
        Chatroom chatroom = (Chatroom)object;
        if (id == chatroom.getId() && name.equals(chatroom.getName()) && messageList.equals(chatroom.getMessageList())
            && owner.equals(chatroom.getOwner()))
            return true;
        else
            return false;
    }
}