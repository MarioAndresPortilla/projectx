package xproject;
import static xproject.Driver.conn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xproject.util.ConnectionUtil;

class DriverTest {

    @BeforeEach
    void setUp() throws Exception {
    }





    @Test
    public static void main(String[] args) throws SQLException {
        final Logger log = Logger.getLogger(Driver.class);
        conn = ConnectionUtil.getConnection();
        log.info("Hello logger!");
        Scanner input = new Scanner(System.in);
        AlienDatabaseService c = new AlienDatabaseService();
        //Initializing Variables
        String table = "";
        int x = 0;

        System.out.println("A deadly alien invasion has wreaked havoc on Earth for the last 24 hours and you have been assigned to a special ops team collecting vital data near ground zero..\n");
        System.out.println("..Inside your weapon there are several weapons, one for each known alien or creature you might need to defend yourself against...Are you weapon ready!!?\n");
        input.nextLine();
        System.out.println("We have orders from HQ to document all the undiscovered species and their weapon weakness to our best abilities in the next 24 hours... \n");

        System.out.println("Please choose the best option: ");
        System.out.println("0) If you have no new data or information to add to the database");
        System.out.println("1) To View the current Alien Data or Weapon Data");
        System.out.println("2) To Insert a new Alien or new Weapon into our Database");
        System.out.println("3) To Remove a Alien or Weapon from our Database");

        x = input.nextInt();

        while (x != 0) {                                                                                                   //while to use the command line user option menu
            switch (x) {
                case 1:
                    System.out.print("Please choose which database table to access [ --> ");
                    table = input.next();
                    log.info("User has chosen to view the Database");
                    System.out.print("Please choose what column you wish to see from the database (EX: alien_id)--> ");
                    String selected = input.next();
                    System.out.println("\n");
                    ResultSet Aliens = c.Select(table, selected);
                    c.printAliensResultset(Aliens, selected);
                    System.out.print("\n\n");
                    c.printResultSet(c.Select(table, selected));                                                        //call function to see the tables
                    break;
                case 2:
                    System.out.print("Insert the table that you want to update --> ");
                    table = input.next();
                    switch (table) {                                                                                     //*add a new case for every new table that you add
                        case "aliens":
                            System.out.print("Insert the alien_id --> ");
                            int alien_id1 = input.nextInt();
                            System.out.print("Insert the alien_type --> ");
                            String alien_type = input.next();
                            System.out.print("Insert the alien_speed --> ");
                            String alien_speed = input.next();

                            System.out.print("\nInsert the attack_type -->");
                            String attack_type = input.next();

                            System.out.print("Insert the alien_weakness --> ");
                            String alien_weakness = input.next();
                            Object[] newalien = new Object[]{alien_id1, alien_type, alien_speed, attack_type, alien_weakness};//create the object to add the new alien
                            c.Insert(table, newalien);                                                                  //call the function to insert an object to a table
                            break;
                        case "weapon":
                            System.out.print("Insert the weapon_id --> ");
                            int weapon_id = input.nextInt();
                            System.out.print("Insert the weapon_name --> ");
                            String weapon_name = input.next();
                            System.out.print("Insert the alien_id --> ");
                            int alien_id2 = input.nextInt();
                            Object[] newweapon = new Object[]{weapon_id, weapon_name, alien_id2};                      //create the object to add the new weapon
                            c.Insert(table, newweapon);                                                                 //call the function to insert an object to a table
                            break;
                        default:
                            System.out.print("Table doesn't exist!");
                            break;
                    }
                    break;
                case 3:
                    System.out.print("Input the table that you want to delete from --> ");
                    table = input.next();
                    System.out.print("Input the where clause you want to use to remove elements with (EX: alien_id=1)--> ");
                    String condition = input.next();
                    c.Delete(table, condition);                                                                         //call the function to delete an object from a table
                    break;
                default:
                    System.out.println("Not valid number!");
                    break;
            }
            System.out.println("\n\n\n\nIf you wish to keep adding to the any of the database please choose the appropriate corresponding option or press 0 to quit:");
            System.out.println("0) Quit");
            System.out.println("1) To View the current Alien Data or Weapon Data");
            System.out.println("2) To Insert a new alien or new weapon into our DB");
            System.out.println("3) To Remove a alien or weapon from our DB");
            x = input.nextInt();
        }

        input.close();
        c.close();
    }


    @Test
    public void Delete(String table, String condition) throws SQLException {
        String command = "DELETE  FROM " + table + " WHERE " + condition + ";";
        PreparedStatement st = conn.prepareStatement(command);
        st.execute();

        assertNotNull(st);
    }

    @Test
    public ResultSet Select(String table, String columns) throws SQLException {
        PreparedStatement st = conn.prepareStatement(" SELECT * FROM " + table + ";");
        return st.executeQuery();
    }



    @Test
    public void printResultSet(ResultSet ris) throws SQLException {
        ResultSetMetaData rsmd = ris.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        for (int i = 1; i <= columnsNumber; i++)
            System.out.print(rsmd.getColumnName(i) + ((i != columnsNumber) ? " , " : "\n"));

        while (ris.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = ris.getString(i);
                System.out.print(columnValue + " ");
            }
            System.out.println("");
        }



    }
}