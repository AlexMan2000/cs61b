/**
 * Created by AlexMan
 */
public class ex_1a {
    public static void drawTriangle(int numLines){
        if(numLines>0){
            int i = 1;
            while(i<numLines){
                int j=i;
                while(j>0){
                    System.out.print("*");
                    j--;
                }
                i++;
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        ex_1a.drawTriangle(10);
    }
}
