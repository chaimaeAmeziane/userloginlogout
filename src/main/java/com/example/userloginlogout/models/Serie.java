package com.example.userloginlogout.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Serie {
    @Id
    @GeneratedValue
    private int id;
    private String titre;

    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Serie()
    {

    }
    public Serie(String titre,String description,User user){
        this.titre=titre;
        this.description=description;
        this.user=user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
