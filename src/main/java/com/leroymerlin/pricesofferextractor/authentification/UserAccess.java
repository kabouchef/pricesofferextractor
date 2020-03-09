package com.leroymerlin.pricesofferextractor.authentification;

import lombok.Data;

@Data
public class UserAccess {
    private String idLDAP ="";
    private String firstName = "";
    private String lastName = "";
    private String profil ="";
    private boolean accessRight = false;

    public String toString() {
        String s = "idLDAP : " + idLDAP + "\n";
        s +="firstname : " + firstName + "\n";
        s +="name : " + lastName + "\n";
        s +="profil : " + profil + "\n";
        s +="profil : " + accessRight + "\n";

        return s;
    }
}
