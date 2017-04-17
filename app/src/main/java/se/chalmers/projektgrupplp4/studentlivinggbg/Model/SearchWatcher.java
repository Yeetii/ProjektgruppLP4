package se.chalmers.projektgrupplp4.studentlivinggbg.Model;

import android.view.View;

import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Search;

public class SearchWatcher{
    public String title;
    private Search search;

    public SearchWatcher (String title) {
        this.title = title;
    }

    public SearchWatcher(Search search) {
        this.search = search;
    }

    public SearchWatcher(String title, Search search) {
        this.title = title;
        this.search = search;
    }


    public void test (View viev) {
        System.out.println("Hai");
    }
    public String getTitle(){return title;}
    public Search getSearch(){return search;}
}
