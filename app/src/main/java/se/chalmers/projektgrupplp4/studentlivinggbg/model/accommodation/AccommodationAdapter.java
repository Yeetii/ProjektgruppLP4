package se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation;

import java.util.List;

/**
 * @author Peter Gärdenäs
 * Used by: ChalmersAdapter, SGSAdapter, DatabaseUpdater
 * Uses: Accommodation
 * Responsibility: Interface for AccommodatinsAdapter.
 */

public abstract class AccommodationAdapter {

    public abstract List<Accommodation> getAccommodations();
}
