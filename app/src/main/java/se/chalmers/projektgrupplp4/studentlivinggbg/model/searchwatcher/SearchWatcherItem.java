package se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher;

import java.util.ArrayList;
import java.util.List;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;

/**
 * @author Erik Magnusson
 * Used by: AdvancedSearchActivityController, DatabaseUpdater, MainSearchActivity, ModalController,
 * SearchWatcherAdapter, SearchWatcherItemController, SearchWatcherItemView, SearchWatcherModel, SettingsController
 * Uses: Accommodation, Search
 * Responsibility: Holding a search that can be performed automaticly
 */

public class SearchWatcherItem {
    private String title;
    private Search search;
    private List<Accommodation> newMatches;

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
                    i++;
                } else if(search.search(newAccommodations).contains(accommodation)){
                    result.add(accommodation);
                }
            }
            if(result.size() > 0){
                newMatches = result;
                return result.size();
            }
            return newAccommodations.size()-i;
        }
        catch(NullPointerException e){}
        return 0;
    }


    public String getTitle(){return title;}
    public Search getSearch(){return search;}
    public List<Accommodation> getNewMatches(){
        return newMatches;
    }

    public void resetNewMatches(){
        newMatches.clear();
    }

    public void setSearch(Search search){
        this.search = search;
    }
}
