package se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher;

import java.util.ArrayList;
import java.util.List;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;

/**
 * @author
 * Used by: AdvancedSearchActivityController, DatabaseUpdater, MainSearchActivity, ModalController,
 * SearchWatcherActivity, SettingsController
 * Uses: Accommodation, Search, SearchWatcher
 * Responsibility: Holding all SearchWatcherItems that are shown in SearchWatcherActivity
 */

public class SearchWatcherList {
    private static List<SearchWatcher> searchWatcherItems = new ArrayList<>();


    public static SearchWatcher createSearchWatcher(String name, Search search){
        SearchWatcher sWItem = new SearchWatcher(name, search);
        searchWatcherItems.add(sWItem);
        return sWItem;
    }

    public static List<SearchWatcher> getSearchWatchers(){
        return searchWatcherItems;}


    public static int checkForMatches(List<Accommodation> newAccommodations){
        int matches = 0;
        for (SearchWatcher sWItem : searchWatcherItems){
            matches += sWItem.checkForMatches(newAccommodations);
        }
        return matches;
    }
}

