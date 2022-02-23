package projectx.dao;
import projectx.model.Zombies;
import projectx.util.ConnectionUtil;
import java.sql.*;

import projectx.model.Backpack;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static projectx.driver.Driver.conn;

//Zombies table in our database we'd like to query, we created zombiesDAO interface:
public class zombiesDAO {

     private static void select() {
        int z_id = 1;
        String sql = "SELECT * FROM zombies WHERE zombie_id = 1";
        try {
            Statement stnt = conn.createStatement();
            ResultSet rs = stnt.executeQuery(sql);
            while(rs.next()){
                System.out.println("Zombie Weakness: " + rs.getString("zombie_weakness"));
                System.out.println("Zombie Type: " + rs.getString("zombie_type"));
                System.out.println("Zombie Speed: " + rs.getString("zombie_speed"));
                System.out.println("Zombie Attack: " + rs.getString("attack_type"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}