package Model.dao;

import Model.entity.Client;

public class ClientDao extends GenericDao<Client, Integer> {

	public ClientDao() {
		super(Client.class);
	}
}
