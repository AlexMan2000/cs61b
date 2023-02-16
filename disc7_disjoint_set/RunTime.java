/**
 * Created by AlexMan
 */
public class RunTime {
    public static int ping(int i, int j) {
        return 65;
    }


    public static void main(String[] args) {
        int j = 0;
        int N = 100;
        int M = 100;
        for (int i = N; i >0 ; i--) {
            System.out.println(i);
            for(; j <= M; j++) {
                if(ping(i,j)>64){
                    break;
                }
            }
        }
    }
}
