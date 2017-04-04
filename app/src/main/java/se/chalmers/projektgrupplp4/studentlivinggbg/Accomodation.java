package se.chalmers.projektgrupplp4.studentlivinggbg;

/**
 * Created by Erik on 2017-04-03.
 */

//TODO Actually implement, just temporary for testing
public class Accomodation {
    private String address;
    private String description = "Lorem Ipsum är en utfyllnadstext från tryck- och förlagsindustrin. Lorem ipsum har varit standard ända sedan 1500-talet, när en okänd boksättare tog att antal bokstäver och blandade dem för att göra ett provexemplar av en bok. Lorem ipsum har inte bara överlevt fem århundraden, utan även övergången till elektronisk typografi utan större förändringar. Det blev allmänt känt på 1960-talet i samband med lanseringen av Letraset-ark med avsnitt av Lorem Ipsum, och senare med mjukvaror som Aldus PageMaker.";

    public Accomodation (String address){
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public int getRent(){
        return 2000 + (int) (Math.random() * 5000);
    }

    public int getArea(){
        return 10 + (int) (Math.random() * 40);
    }

    public accoHost getHost(){
        return accoHost.SGS;
    }

    public accoType getType(){
        return accoType.ONE;
    }
}
