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

    public int getzombie_id() {

        return zombie_id;
    }

    public void setzombie_id(int zombie_id) {

        this.zombie_id = zombie_id;
    }

    public String getzombie_type() {

        return zombie_type;
    }

    public void setzombie_type(String zombie_type) {

        this.zombie_type = zombie_type;
    }

    public String getzombie_speed() {

        return zombie_speed;
    }

    public void setzombie_speed(String zombie_speed) {

        this.zombie_speed = zombie_speed;
    }

    public String getattack_type() {

        return attack_type;
    }

    public void setattack_type(String attack_type) {

        this.attack_type = attack_type;
    }

    public String getzombie_weakness() {

        return zombie_weakness;
    }

    public void setzombie_weakness(String zombie_weakness) {

        this.zombie_weakness = zombie_weakness;
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