package xproject;
import xproject.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.*;



public class Driver {
    public static final Logger log = Logger.getLogger(Driver.class);
    public static Connection conn;

    public static void main(String[] args) throws SQLException {
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
        Driver.log.warn("User is about to make a choice from the switch case");
        println("Please choose the best option: ");
        println("0) If you have no new data or information to add to the database");
        println("1) To View the current Alien Data or Weapon Data");
        println("2) To Insert a new Alien or new Weapon into our Database");
        println("3) To Remove a Alien or Weapon from our Database");

        x = input.nextInt();

        while(x!=0) {                                                                                                   //while to use the command line user option menu
            switch(x) {
                case 1:
                    print("Please choose which database table to access [ --> ");
                    table = input.next();
                    log.info("User has chosen to view the Database");
                    print("Please choose what column you wish to see from the database (EX: alien_id)--> ");
                    String selected = input.next();
                    println("\n");
                    ResultSet Aliens = c.Select(table, selected);
                    c.printAliensResultset(Aliens, selected);
                    print("\n\n");
                    c.printResultSet(c.Select(table, selected));                                                        //call function to see the tables
                    break;
                case 2:
                    print("Insert the table that you want to update --> ");
                    table = input.next();
                    switch(table) {                                                                                     //*add a new case for every new table that you add
                        case "aliens":
                            print("Insert the alien_id --> ");
                            int alien_id1 = input.nextInt();
                            print("Insert the alien_type --> ");
                            String alien_type = input.next();
                            print("Insert the alien_speed --> ");
                            String alien_speed = input.next();

                            print("\nInsert the attack_type -->");
                            String attack_type = input.next();

                            print("Insert the alien_weakness --> ");
                            String alien_weakness = input.next();
                            Object[] newalien = new Object[] {alien_id1, alien_type, alien_speed, attack_type, alien_weakness};//create the object to add the new alien
                            c.Insert(table, newalien);                                                                  //call the function to insert an object to a table
                            break;
                        case "weapon":
                            print("Insert the weapon_id --> ");
                            int weapon_id = input.nextInt();
                            print("Insert the weapon_name --> ");
                            String weapon_name = input.next();
                            print("Insert the alien_id --> ");
                            int alien_id2 = input.nextInt();
                            Object[] newweapon = new Object[] {weapon_id, weapon_name, alien_id2};                      //create the object to add the new weapon
                            c.Insert(table, newweapon);                                                                 //call the function to insert an object to a table
                            break;
                        default:
                            print("Table doesn't exist!");
                            break;
                    }
                    break;
                case 3:
                    print("Input the table that you want to delete from --> ");
                    table = input.next();
                    print("Input the where clause you want to use to remove elements with (EX: alien_id=1)--> ");
                    String condition = input.next();
                    c.Delete(table, condition);                                                                         //call the function to delete an object from a table
                    break;
                default:
                    println("Not valid number!");
                    break;
            }
            println("\n\n\n\nIf you wish to keep adding to the any of the database please choose the appropriate corresponding option or press 0 to quit:");
            println("0) Quit");
            println("1) To View the current Alien Data or Weapon Data");
            println("2) To Insert a new alien or new weapon into our DB");
            println("3) To Remove a alien or weapon from our DB");
            x = input.nextInt();
        }

        input.close();
        c.close();
    }


    static public void print(Object o) {
        System.out.print(o);
    }

    static public void println(Object o) {
        System.out.println(o);
    }


    //closes the connection
    public boolean close() {
        try {
            conn.close();
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}



