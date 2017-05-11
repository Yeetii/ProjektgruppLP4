package se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher;

import android.view.View;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;

public class SearchWatcherItem {
    public String title;
    private Search search;


    public SearchWatcherItem(String title, Search search) {
        this.title = title;
        this.search = search;
    }

    public void editSearchWatcher () {
        //TODO: Add functionality
        System.out.println("Currently does nothing");
    }

    public void test (View view) {
        System.out.println("Hai");
    }
    public String getTitle(){return title;}
    public Search getSearch(){return search;}
}
