package se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Segerstedt
 * Used by: Accommodation, AdvancedSearchFragmentController, AdvancedSearchFragmentView,
 * ChalmersAdapter, SGSAdapter, Search, SearchHandler, SearchWatcherItemView
 * Uses: EnumType
 */
public enum AccommodationHost implements EnumType {
        SGS, CHALMERS;

        //Creating a nice toString() method
        @Override
        public String toString() {
                switch(name()){
                        case "SGS": return "SGS Studentbostäder";
                        case "CHALMERS": return "Chalmers Studentbostäder";
                }
                return "ERROR";
        }

        public String toStringShort(){
                switch(name()){
                        case "SGS": return "SGS";
                        case "CHALMERS": return "Chalmers";
                }
                return "ERROR";
        }

        static public AccommodationHost parseString(String string){
                switch(string){
                        case "SGS Studentbostäder":      return SGS;
                        case "Chalmers Studentbostäder": return CHALMERS;
                }
                return null;
        }

        public static List<AccommodationHost> parseStringList(List<String> stringList){
                List<AccommodationHost> result = new ArrayList<>();
                for(String string: stringList){
                        result.add(parseString(string));
                }
                return result;
        }

        public static String toStringShort(List<AccommodationHost> hostsArray){
                try{
                        String result = "";
                        for(AccommodationHost host: hostsArray){
                                if(host != null){
                                        result = result + host.toStringShort() + ", ";
                                }
                        }
                        if(!result.equals("")){return result.substring(0, result.length() - 2);}
                        return result;                }
                catch(Exception e){
                        return "";}
        }
}