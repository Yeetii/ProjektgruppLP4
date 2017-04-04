package se.chalmers.projektgrupplp4.studentlivinggbg;


public enum HouseType {
    KORRIDORSRUM, KOKVRÅ, KOVSKÅP, ETT_RUM, TVÅ_RUM, TRE_RUM, FYRA_RUM;


    //Creating a nice toString() method
    @Override
    public String toString() {
        switch(name()){
            case "KORRIDORSRUM": return "Enkelrum med gruppkök";
            case "KOKVRÅ": return "Enkelrum med kokvrå";
            case "KOVSKÅP": return "Enkelrum med kokskåp";
            case "ETT_RUM": return "1-rum och kök";
            case "TVÅ_RUM": return "2-rum och kök";
            case "TRE_RUM": return "3-rum och kök";
            case "FYRA_RUM": return "4-rum och kök";
        }
        return "ERROR";
    }
}
