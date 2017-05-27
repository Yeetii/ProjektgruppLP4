package se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher;

import java.util.ArrayList;
import java.util.List;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;

/**
 * @author
 * Used by: AdvancedSearchActivityController, DatabaseUpdater, MainSearchActivity, ModalController,
 * SearchWatcherActivity, SettingsController
 * Uses: Accommodation, Search, SearchWatcherItem
 * Responsibility: Holding all SearchWatcherItems that are shown in SearchWatcherActivity
 */

public class SearchWatcherModel {
    private static List<SearchWatcherItem> searchWatcherItems = new ArrayList<>();


    public static SearchWatcherItem createSearchWatcher(String name, Search search){
        SearchWatcherItem sWItem = new SearchWatcherItem(name, search);
        searchWatcherItems.add(sWItem);
        return sWItem;
    }

    public static List<SearchWatcherItem> getSearchWatcherItems(){
        return searchWatcherItems;}


    public static int checkForMatches(List<Accommodation> newAccommodations){
        int matches = 0;
        for (SearchWatcherItem sWItem : searchWatcherItems){
            matches += sWItem.checkForMatches(newAccommodations);
        }
        return matches;
    }
}

