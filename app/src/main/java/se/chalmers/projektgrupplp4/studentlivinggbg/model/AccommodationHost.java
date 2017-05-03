package se.chalmers.projektgrupplp4.studentlivinggbg.model;


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

}