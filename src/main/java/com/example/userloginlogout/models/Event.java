package com.example.userloginlogout.models;

import javax.persistence.*;


@Entity
public class Event {
        @Id
        @GeneratedValue
        private int id;
        private String date;
        private int valeur;
        private String commentaire;
    public Event()
    {

    }
    public Event(String date,int valeur,String commentaire,Serie serie)
    {
        this.date=date;
        this.valeur=valeur;
        this.commentaire=commentaire;
        this.serie=serie;
    }

    @ManyToOne
        @JoinColumn(name="serie_id")
        private Serie serie;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getValeur() {
            return valeur;
        }

        public void setValeur(int valeur) {
            this.valeur = valeur;
        }

        public String getCommentaire() {
            return commentaire;
        }

        public void setCommentaire(String commentaire) {
            this.commentaire = commentaire;
        }


    public Serie getSerie() {
            return serie;
        }

        public void setSerie(Serie serie) {
            this.serie = serie;
        }
    }
