package se.chalmers.projektgrupplp4.studentlivinggbg.Model;

import android.view.View;

import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Search;

public class SearchWatcherItem {
    public String title;
    private Search search;

    public SearchWatcherItem(String title) {
        this.title = title;
    }

    public SearchWatcherItem(Search search) {
        this.search = search;
    }

    public SearchWatcherItem(String title, Search search) {
        this.title = title;
        this.search = search;
    }


    public void test (View viev) {
        System.out.println("Hai");
    }
    public String getTitle(){return title;}
    public Search getSearch(){return search;}
}
