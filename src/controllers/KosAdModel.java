package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class KosAdModel {
    public ArrayList <KosAdController> getAllpizza()
    {
//        Vytvoreni Listu
        ArrayList<KosAdController> pizza = new ArrayList<KosAdController>();
        KosAdController pizz = null;
        Connection c;
//        vypisanie z databaze do toho listu "snad"
        try {
            c = openConnection();
            Statement statement = c.createStatement();
            String s = "SELECT * FROM pizza_app.menu";

            ResultSet rs = statement.executeQuery(s);


            while (rs.next()) {

                pizz.setPizzaId(rs.getInt(null));
                pizz.setPizzaNazov(rs.getString("nazov"));
                pizz.setPizzaIngrediencie(rs.getString("Ingrediencie"));
                pizz.setPizzaCena(rs.getInt(null));


                pizza.add(pizz);
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return pizza;
    }

    private Connection openConnection() {
        return null;
    }
}
