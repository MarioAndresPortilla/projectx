package projectx.model;

public class Backpack {
    // encapsulation: declaring class variables/attributes as private
    private int weapon_id;
    private String weapon_name;
    private int zombie_id;

    public Backpack(int weapon_id, String weapon_name, int zombie_id) {
        this.weapon_id = weapon_id;
        this.weapon_name = weapon_name;
        this.zombie_id = zombie_id;
    }
    // provide public get and set methods to access and update the value of a private variables
    // get method returns the variable value



    public int getWeaponId() {
        return weapon_id;
    }


    // set method sets the value
    public void setWeaponId(int weapon_id) {
        this.weapon_id = weapon_id;
    }




















    @Override
    public String toString() {
        return "Backpack{" +
                "weapon_id='" + weapon_id + '\'' +
                "weapon_name='" + weapon_name + '\'' +
                ", zombie_id=" + zombie_id +
                '}';

    }
}