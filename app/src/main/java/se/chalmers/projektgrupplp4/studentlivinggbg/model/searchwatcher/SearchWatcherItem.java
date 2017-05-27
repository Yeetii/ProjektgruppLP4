package se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher;

import java.util.ArrayList;
import java.util.List;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;

public class SearchWatcherItem {
    private String title;
    private Search search;

    public SearchWatcherItem(String title, Search search) {
        this.title = title;
        this.search = search;
    }

    public int checkForMatches(List<Accommodation> newAccommodations){
        try{
            int i = 0;
            List<Accommodation> result = new ArrayList<>();
            for(Accommodation accommodation: newAccommodations){
                if(accommodation == null){
                    i++;}
                else if(search.search(newAccommodations).contains(accommodation)){
                    result.add(accommodation);
                }
            }
            if(result.size() > 0){
            return result.size();}
            return newAccommodations.size()-i;
        }
        catch(NullPointerException e){}
        return 0;
    }


    public String getTitle(){return title;}
    public Search getSearch(){return search;}

    public void setSearch(Search search){
        this.search = search;
    }
}
