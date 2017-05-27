package se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation;

import java.util.ArrayList;
import java.util.List;




public class EnumHelper {
    public static <T extends EnumType> List<String> toStringList(List<T> list){
        List<String> result = new ArrayList<>();
        for (T  item : list){
            if (item != null){
                result.add(item.toString());
            }
        }
        return result;
    }

    //Written by John, generalized by Erik
    public static <T extends EnumType> String toString(List<T> list){
        try{
            String result = "";
            for(T item: list){
                if(item != null){
                    result = result + item.toString() + ", ";
                }}
            if(!result.equals("")){return result.substring(0, result.length() - 2);}
            return result;
        }
        catch(Exception e){
            return "";}
    }
}
