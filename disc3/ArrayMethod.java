import java.util.Arrays;

/**
 * Created by AlexMan
 */
public class ArrayMethod {

    public static int[] insert(int[] arr, int item, int position){
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        if(position >= arr.length){
            newArr[newArr.length - 1] = item;
            return newArr;
        }
        for(int i = newArr.length - 1; i > position; i--){
            newArr[i] = newArr[i-1];
        }
        newArr[position] = item;
        return newArr;

    }

    public static int[] replicate(int[] arr){
        // 计算新的array的长度
        int newLength = 0;
        for(int x: arr){
            newLength += x;
        }
        int[] newArr = new int[newLength];
        int index = 0;
        for(int x: arr){
            for(int count =0 ; count < x; count++){
                newArr[index] = x;
                index ++;
            }
        }
        return newArr;
    }


    public static void main(String[] args) {
        int[] temp = new int[] {1,2,3};
        int[] newTemp = replicate(temp);
        System.out.println(Arrays.toString(newTemp));
    }
}
