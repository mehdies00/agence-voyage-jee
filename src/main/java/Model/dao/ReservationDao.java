package Model.dao;

import Model.entity.Client;
import Model.entity.Reservation;

public class ReservationDao extends GenericDao<Reservation, Integer> {

	public ReservationDao() {
		super(Reservation.class);
	}

}
