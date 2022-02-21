package projectx.model;

public class Backpack {
    private int weapon_id;
    private String weapon_name;
    private int zombie_id;

    public Backpack(int weapon_id, String weapon_name, int zombie_id) {
        this.weapon_id = weapon_id;
        this.weapon_name = weapon_name;
        this.zombie_id = zombie_id;
    }
    public int getWeaponId() {
        return weapon_id;
    }
    public void setWeaponId(int weapon_id) {
        this.weapon_id = weapon_id;
    }
    public String getWeaponName() {
        return weapon_name;
    }
    public void setWeaponName(String weapon_name) {
        this.weapon_name = weapon_name;
    }
    public int getZombieId() {
        return zombie_id;
    }
    public void setZombieId(int zombie_id) {
        this.zombie_id = zombie_id;
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