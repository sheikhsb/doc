package com.example.doc;

public class UserProfile
{
    String name;
    String number;
    String age;
    String gender;
    String relationship;
    String user;


    public UserProfile() {

    }
    public UserProfile(String name, String number, String age, String gender, String relationship,String user) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.gender = gender;
        this.relationship = relationship;
        this.user = user;
    }
    public String getUser() {
        return user;
    }

    public void setUsers(String user) {
        this.user = user;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
