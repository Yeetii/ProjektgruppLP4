package se.chalmers.projektgrupplp4.studentlivinggbg.model;


import java.util.ArrayList;

public enum AccommodationHost {
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

        static public AccommodationHost parseString(String string){
                switch(string){
                        case "SGS Studentbostäder":      return SGS;
                        case "Chalmers Studentbostäder": return CHALMERS;
                }
                return null;
        }

        public static ArrayList<AccommodationHost> parseStringList(ArrayList<String> stringList){
                ArrayList<AccommodationHost> result = new ArrayList<>();
                for(String string: stringList){
                        result.add(parseString(string));
                }
                return result;
        }

        public static String toStringList(ArrayList<AccommodationHost> hostsArray){
                try{
                        String result = "";
                        for(AccommodationHost host: hostsArray){
                                result = result + host.toString() + ", ";
                        }
                        if(!result.equals("")){return result.substring(0, result.length() - 2);}
                        return result;                }
                catch(Exception e){
                        return "";}
        }

}