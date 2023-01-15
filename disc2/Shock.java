/**
 * Created by AlexMan
 */
public class Shock {
    public static int bang;
    public static Shock baby;
    public Shock() {
        this.bang = 100;
    }
    public Shock (int num) {
        this.bang = num;   // num = 200 , so this.bang(static) now is equal to 200
        baby = starter();  // This line changes the bang from 200 -> 100 for all instances
        this.bang += num;  // num is still 200, but this.bang is 100 now, so this.bang = 100 + 200 = 300
    }
    public static Shock starter() {
        Shock gear = new Shock();
        return gear;
    }
    public static void shrink(Shock statik) {
        statik.bang -= 1;
    }
    public static void main(String[] args) {
        Shock gear = new Shock(200);
        System.out.println(gear.bang); // 300
        shrink(gear);  // This line shrink the bang from 300 to 299
        shrink(starter()); // This line first set the bang to 100, and then shrink it to 99.
        System.out.println(gear.bang); // 99
    }
}