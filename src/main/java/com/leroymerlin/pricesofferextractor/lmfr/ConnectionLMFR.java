package com.leroymerlin.pricesofferextractor.lmfr;

import com.leroymerlin.pricesofferextractor.serveurs.ServerLMFR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionLMFR {
        /**
         * Boite de dialogue : Environnement
         */
        /*static Object[] choixList = ServerLMFR.values();*/
        /*static String environnement = JOptionPane.showInputDialog(null,
        "Selectionner l'environnement d'extraction de la simulation :", "Environnement",
        JOptionPane.INFORMATION_MESSAGE, null, choixList, choixList[0]).toString();*/

        /**
         * Nom du url
         */
        /*private static String url = ServerLMFR.valueOfUrl(environnement);*/
        /**
         * Nom du user
         */
        /*private static String user = ServerLMFR.valueOfUser(environnement);*/

        /**
         * Mot de passe du
         */
        /*private static String passwd = ServerLMFR.valueOfPassword(environnement);*/

        /**
         * Objet Connection
         */
        private static Connection connect;


    /**
     * Méthode qui va retourner notre instance20200129S51509
     * et la créer si elle n'existe pas...
     *
     * @return connect
     */
    public static Connection getInstance(String environnement, String schema) {
        Object[] choixList = ServerLMFR.values();
        String serveur = environnement + "_" + schema;
        /**
         * Url
         */

        String url = ServerLMFR.valueOfUrl(serveur);
        /**
         * User
         */
        String user = ServerLMFR.valueOfUser(serveur);
        /**
         * Mot de passe
         */
        String passwd = ServerLMFR.valueOfPassword(serveur);

        if (connect == null) {
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException e) {
                Logger.getLogger(e.getMessage());
            }
        }
        return connect;
    }
}
