package se.chalmers.projektgrupplp4.studentlivinggbg;


public class Accommodation {

    private String address = "testgatangatan 999 läg 123";
    private AccommodationHouseType accommodationHouseType = AccommodationHouseType.ONE_ROOM;
    private int price = 1000;
    private double area = 100;
    private int searchers = 10;
    private int image;
    private String description = "Lorem Ipsum är en utfyllnadstext från tryck- och förlagsindustrin. Lorem ipsum har varit standard ända sedan 1500-talet, när en okänd boksättare tog att antal bokstäver och blandade dem för att göra ett provexemplar av en bok. Lorem ipsum har inte bara överlevt fem århundraden, utan även övergången till elektronisk typografi utan större förändringar. Det blev allmänt känt på 1960-talet i samband med lanseringen av Letraset-ark med avsnitt av Lorem Ipsum, och senare med mjukvaror som Aldus PageMaker.";
    private AccommodationHost accommodationHost = AccommodationHost.CHALMERS;

    public Accommodation(String adress){
        this.address=adress;
    }


    public Accommodation(String address, AccommodationHouseType accommodationHouseType, int price, double area, int searchers, int image) {
        this.address=address;
        this.accommodationHouseType = accommodationHouseType;
        this.price=price;
        this.area=area;
        this.searchers=searchers;
        this.image=image;
    }


    public String getAddress() {
        return address;
    }


    public String getAccommodationHouseType() {
        return accommodationHouseType.toString();
    }


    public String getPrice() {
        return Integer.toString(price);
    }


    public String getArea() {
        return Double.toString(area);
    }

    public String getSearchers(){
        return Integer.toString(searchers);
    }

    public int getImage(){
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getAccommodationHost(){
        return accommodationHost.toString();
    }
}
