package Chapter_1_2.psvm;

/**
 * Created by AlexMan
 */
public class ArgsExercise {
    public static void main(String[] args) {
        int res =0;
        for(int i=0;i<args.length;i++){
            res += Integer.parseInt(args[i]);
        }
        System.out.println(res);
    }
}
