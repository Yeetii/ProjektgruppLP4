package se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation;

import java.util.List;

/**
 * @author Peter Gärdenäs
 * Used by: ChalmersAdapter, SGSAdapter, DatabaseUpdater
 * Uses: Accommodation
 * Responsibility: Interface for AccommodatinsAdapter.
 */

public interface AccommodationAdapter {
    List<Accommodation> getAccommodations();
}
