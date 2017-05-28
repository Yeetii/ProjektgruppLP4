package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Host;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.HouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.EnumHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

/**
 * Created by Erik on 2017-05-15.
 * @author Erik
 * Revised by John Segerstedt
 * Used by: AdvancedSearchFragmentController
 * Uses: Host, EnumHelper, Region, HouseType
 * Responsibility: View for advanced search fragment
 */

public class AdvancedSearchFragmentView {
    private final View view;

    private final List<String> roomTypeItems = new ArrayList<>();
    private final List<String> areasItems = new ArrayList<>();
    private final List<String> landlordItems = new ArrayList<>();

    public AdvancedSearchFragmentView(View view){
        this.view = view;
        initMultiSpinners();
    }

    private void initMultiSpinners() {
        MultiSpinner roomTypeSpinner = (MultiSpinner) view.findViewById(R.id.roomType_spinner);
        MultiSpinner areasSpinner = (MultiSpinner) view.findViewById(R.id.areas_spinner);
        MultiSpinner landlordSpinner = (MultiSpinner) view.findViewById(R.id.landlord_spinner);

        fillLists();

        roomTypeSpinner.setItems(roomTypeItems, view.getContext().getString((R.string.multiSpinner_roomType)));
        areasSpinner.setItems(areasItems, view.getContext().getString(R.string.multiSpinner_areas));
        landlordSpinner.setItems(landlordItems, view.getContext().getString(R.string.multiSpinner_landlord));
    }

    private void fillLists(){
        roomTypeItems.addAll(EnumHelper.toStringList(Arrays.asList(HouseType.values())));
        areasItems.addAll(EnumHelper.toStringList(Arrays.asList(Region.values())));
        landlordItems.addAll(EnumHelper.toStringList(Arrays.asList(Host.values())));
    }
}
