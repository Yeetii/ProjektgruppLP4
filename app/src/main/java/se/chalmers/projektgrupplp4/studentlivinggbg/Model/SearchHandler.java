package se.chalmers.projektgrupplp4.studentlivinggbg.Model;

import java.util.ArrayList;
import java.util.List;

class SearchHandler {

    private List previousSearches = getPreviousSearches();

    public SearchHandler () {

    }



    private List<Search> getPreviousSearches () {
        return new ArrayList<Search>();
    }


}
