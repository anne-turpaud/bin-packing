package dataReader;

import java.util.List;

/**
 *  Interface d'un objet étant capable de récupérer les données du problème
 *  depuis une source de données
 */
public interface IDataReader {
	
	public List<Integer> readAll();

}
