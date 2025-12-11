package Model.dao;

import Model.entity.Reservation;
import Model.entity.Voyage;

public class VoyageDao extends GenericDao<Voyage, Integer> {

	public VoyageDao() {
		super(Voyage.class);
	}

}
