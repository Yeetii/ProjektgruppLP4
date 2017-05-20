package se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher;

import android.view.View;

import java.util.List;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;

public class SearchWatcherItem {
    public String title;
    private Search search;
    private List<Accommodation> newAccommodations;


    public SearchWatcherItem(String title, Search search) {
        this.title = title;
        this.search = search;
    }

    public void editSearchWatcher () {
        //TODO: Add functionality
        System.out.println("Currently does nothing");
    }

    public int checkForMatches(List<Accommodation> newAccommodations){
        try{this.newAccommodations = search.search(newAccommodations);}
        catch(NullPointerException e){}
        return this.newAccommodations.size();
    }


    public void test (View view) {
        System.out.println("Hai");
    }
    public String getTitle(){return title;}
    public Search getSearch(){return search;}

    public List<Accommodation> getNewAccommodations() {
        //TODO and reset list?
        return newAccommodations;
    }
}
