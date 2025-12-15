package Model.dao;

import java.util.Collection;

import Model.entity.Destination;

public class DestinationDao extends GenericDao<Destination, Integer> {

	public DestinationDao() {
		super(Destination.class);
	}

}
