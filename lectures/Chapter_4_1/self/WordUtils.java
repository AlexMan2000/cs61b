/**
 * Created by AlexMan
 */
public class WordUtils {
    public static String longest(SLList<String> list){
        // TODO
        int maxIndex = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).length() > list.get(maxIndex).length()){
                maxIndex = i;
            }
        }
        return list.get(maxIndex);
    }
}
