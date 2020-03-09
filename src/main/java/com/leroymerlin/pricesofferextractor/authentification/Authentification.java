package com.leroymerlin.pricesofferextractor.authentification;

import com.leroymerlin.pricesofferextractor.lmfr.ConnectionLMFR;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

@Data
public class Authentification {
    private String idLDAP ="";
    private String firstName = "";
    private String lastName = "";
    private String profil ="";
    private boolean accessRight = false;

    public UserAccess getAccessRight(String idLDAP){
        try{
            Statement state = ConnectionLMFR.getInstance("RECETTE", "REEL")
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet res = state.executeQuery("SELECT * FROM T_USER WHERE ID_USER = '" + idLDAP + "'");
            ResultSetMetaData meta = res.getMetaData();
            res.next();
            lastName = res.getString(2);
            firstName = res.getString(3);
            profil = res.getString(4);

            if(profil.equals("ADMINISTRATEUR")/* || profil.equals("OAPCENTRALE")*/) accessRight = true;

            //On ferme le tout
            res.close();
            state.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }

        UserAccess userAccess= new UserAccess();
        userAccess.setIdLDAP(idLDAP);
        userAccess.setFirstName(firstName);
        userAccess.setLastName(lastName);
        userAccess.setProfil(profil);
        userAccess.setAccessRight(accessRight);

        System.out.println("Login request by : ");
        System.out.println("LDAP : " + idLDAP);
        System.out.println("Prénom : " + firstName);
        System.out.println("Nom : " + lastName);
        if (accessRight) System.out.println("Login successfully.");
        else System.out.println("Permission denied.");

        return userAccess;
    }

}
