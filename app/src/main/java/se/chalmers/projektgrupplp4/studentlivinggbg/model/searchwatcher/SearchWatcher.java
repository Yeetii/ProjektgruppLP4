package se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher;

import java.util.ArrayList;
import java.util.List;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

/**
 * @author Erik Magnusson
 * Used by: AdvancedSearchActivityController, DatabaseUpdater, MainSearchActivity, ModalController,
 * SearchWatcherAdapter, SearchWatcherItemController, SearchWatcherItemView, SearchWatcherList, SettingsController
 * Uses: Search, Accommodation
 * Responsibility: Holding a search that can be performed automaticly
 */

public class SearchWatcher {
    private String title;
    private Search search;
    //Holds objectNumbers of new accommodations
    private List<String> newMatches = new ArrayList<>();

    public SearchWatcher(String title, Search search) {
        this.title = title;
        this.search = search;
    }

    public int checkForMatches(List<Accommodation> newAccommodations){
        try{
            List<String> result = new ArrayList<>();
            List<Accommodation> matches = search.search(newAccommodations);
            for(Accommodation accommodation: newAccommodations){
                if(accommodation != null && matches.contains(accommodation)){
                    result.add(accommodation.getObjectNumber());
                }
            }
            if(result.size() > 0){
                newMatches.addAll(result);
                return result.size();
            }
            return 0;
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }


    public String getTitle(){return title;}
    public Search getSearch(){return search;}
    public List<String> getNewMatches(){
        return newMatches;
    }

    public void setSearch(Search search){
        this.search = search;
    }
}
