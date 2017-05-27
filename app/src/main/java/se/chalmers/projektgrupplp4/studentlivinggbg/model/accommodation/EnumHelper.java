package se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation;

import java.util.ArrayList;
import java.util.List;

public class EnumHelper {
    public static <T extends Type> List<String> toStringList(List<T> list){
        List<String> result = new ArrayList<>();
        for (T  item : list){
            if (item != null){
                result.add(item.toString());
            }
        }
        return result;
    }
}
