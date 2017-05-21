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
        try{
            int i = 0;
            for(Accommodation accommodation: this.newAccommodations){
                if(accommodation == null){
                    i++;}
            }
                return this.newAccommodations.size()-i;
        }
        catch(NullPointerException e){}
        return 0;
    }


    public String getTitle(){return title;}
    public Search getSearch(){return search;}



    /*
    todo: remove this?
    public void test (View view) {
        System.out.println("Hai");
    }

    */


    /*
    public List<Accommodation> getNewAccommodations() {
        //TODO and reset list?
        return newAccommodations;
    }
    */
}
