package com.blm.saytheirnames.models;

import android.media.Image;

import java.util.List;

public class Person {

    String id, full_name, identifier,date_of_incident,context,date_of_birth,number_of_children,age,city,country,biography;
    List<Images> images;
    List<Donation> donation_links;
    List<Petition> petition_links;
    List<Media> media_links;
    List<SocialMedia> social_links;

    public Person(String id, String full_name, String identifier, String date_of_incident, String context, String date_of_birth, String number_of_children, String age, String city, String country, String biography, List<Images> images, List<Donation> donation_links, List<Petition> petition_links, List<Media> media_links, List<SocialMedia> social_links) {
        this.id = id;
        this.full_name = full_name;
        this.identifier = identifier;
        this.date_of_incident = date_of_incident;
        this.context = context;
        this.date_of_birth = date_of_birth;
        this.number_of_children = number_of_children;
        this.age = age;
        this.city = city;
        this.country = country;
        this.biography = biography;
        this.images = images;
        this.donation_links = donation_links;
        this.petition_links = petition_links;
        this.media_links = media_links;
        this.social_links = social_links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDate_of_incident() {
        return date_of_incident;
    }

    public void setDate_of_incident(String date_of_incident) {
        this.date_of_incident = date_of_incident;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getNumber_of_children() {
        return number_of_children;
    }

    public void setNumber_of_children(String number_of_children) {
        this.number_of_children = number_of_children;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<Donation> getDonation_links() {
        return donation_links;
    }

    public void setDonation_links(List<Donation> donation_links) {
        this.donation_links = donation_links;
    }

    public List<Petition> getPetition_links() {
        return petition_links;
    }

    public void setPetition_links(List<Petition> petition_links) {
        this.petition_links = petition_links;
    }

    public List<Media> getMedia_links() {
        return media_links;
    }

    public void setMedia_links(List<Media> media_links) {
        this.media_links = media_links;
    }

    public List<SocialMedia> getSocial_links() {
        return social_links;
    }

    public void setSocial_links(List<SocialMedia> social_links) {
        this.social_links = social_links;
    }
}
