package projectx.driver;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import projectx.model.Backpack;
import projectx.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import static org.apache.commons.collections.CollectionUtils.select;

public class Driver {

    public static Connection conn = ConnectionUtil.getConnection();
    public static void main(String[] args) {
        // Scan is a new object we have created that relates to the imported scanner class and allows input
        Scanner Scan = new Scanner(System.in);
        // Following statement would create the object bp = which is Backpack method being initialized/instantiated (PARAMETERS WILL BE PASSED IN)
        Backpack bp = new Backpack(1,"TommyGun",5);

        // creating variable test & setting it equal to the stringReturn() method
        String test = stringReturn();
        double testDouble = doubleReturn();

        boolean running = true;
        while (running) {
            int option = 1;
            String zombie = " ";

            System.out.println("A deadly viral-infection has spread at a Secret isolated government facility & your the last survivor fleeing by foot! Do you have what it takes to survive?\n");
            Scan.nextLine();

            System.out.println("I really don't think so but okay...Inside your Backpack you should have several weapons, one for each zombie creature you might need to defend yourself against!\n");

            System.out.println("All the mutated zombie creatures have a unique weakness, which one is currently chasing you?\n");
            zombie = Scan.nextLine();
            switch(zombie) {

                    case "Undead Bloodhound":
                        int z_id = 1;

                                    System.out.println("A nasty vicious Undead Bloodhound is chasing you!");
                                    System.out.println("Would you like you to fight or insert a weapon? [1 or 2]");
                                    option = Scan.nextInt();
                                    String sniper = "Savage M10 Stealth Sniper";
                                    if(option == 1){
                                        System.out.println("You blasted a zombie");
                                    }else{
                                        String insertStmt = "insert into backpack values (5, 'Savage M10 Stealth Sniper', 5)";
                                        try{
                                            Statement stnt = conn.createStatement();
                                            stnt.executeUpdate(insertStmt);
                                        }catch(SQLException e){
                                            e.printStackTrace();
                                        }
                                    }
                        break;
                    case "Legless Zombie":
                                    System.out.println("A creepy little Legless Zombie is trying to bite your legs!");
                        break;
                    case "Flood Parasite":
                                    System.out.println("A disgusting mutated Flood Parasite is chasing you!");
                        break;
                    case "Stalker Zombie":
                                    System.out.println("A Stalker Zombie is following you in the shadows!");
                        break;
                    default:
                        System.out.println("I cant see what kind it is, its to dark!");
                        break;

            }

        }
        ;
    }

    private static void select(int i, String undead_bloodhound, String rapid) {
    }

    public static String stringReturn() {
        return "test";
    }

    public static double doubleReturn() {
        return 1.0;
    }


    public static int Check(String Value, Scanner Console) {
        boolean Result = false;
        try
        {
            Integer.parseInt(Value);
            Result = true;
        }
        catch (Exception e)
        {
            Result = false;
        }
        while(Result == false)
        {
            System.out.println(Value+" is not valid.\nPlease try again.");
            Value = Console.nextLine();
            try
            {
                Integer.parseInt(Value);
                Result = true;
            }
            catch (Exception e)
            {
                Result = false;
            }
        }
        return Integer.parseInt(Value);
    }
}














