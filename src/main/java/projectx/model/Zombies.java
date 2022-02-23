package projectx.model;

public class Zombies {
    private int zombie_id;
    private String zombie_type;
    private String zombie_speed;
    private String attack_type;
    private String zombie_weakness;

    public Zombies(int zombie_id, String zombie_type, String zombie_speed, String attack_type, String zombie_weakness) {
        this.zombie_id = zombie_id;
        this.zombie_type = zombie_type;
        this.zombie_speed = zombie_speed;
        this.attack_type = attack_type;
        this.zombie_weakness = zombie_weakness;
    }
    // provide public get and set methods to access(get, make public) and update(set) the value of a private variables inside this class
    public int getZombie_id() {
        return zombie_id;
    }

    public void setZombie_id(int zombie_id) {
        this.zombie_id = zombie_id;
    }

















    @Override
    public String toString() {
        return "Painting{" +
                "zombie_id=" + zombie_id +
                ", name='" + zombie_type + '\'' +
                ", zombie_speed='" + zombie_speed + '\'' +
                ", attack_type='" + attack_type + '\'' +
                ", zombie_weakness=" + zombie_weakness +
                '}';
    }
}