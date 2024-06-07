package com.example.projetfinal;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.os.Bundle;

@Entity(tableName = "TO_DO")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String contenu;
    private String  Statut;

    public Task(int id, String title,String Statut,String contenu) {
        this.id = id;
        this.title = title;
        this.contenu=contenu;
        this.Statut=Statut;
    }
    public Task(){}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getContenu() {
        return contenu;
    }
    public String getStatut() {
        return Statut;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setContenu(String contenu)
    {
       this.contenu=contenu;
    }
    public void setStatut(String Statut)
    {
         this.Statut=Statut;
    }


}



