/**
 * Created by CSTS Team
 */
public class Lecture_1_1 {
    public static void main(String[] args) {
        int res =0;
        for(int i=0;i<args.length;i++){
            res += Integer.parseInt(args[i]);
        }
        System.out.println(res);
    }
}
