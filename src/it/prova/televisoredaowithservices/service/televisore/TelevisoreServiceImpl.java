package it.prova.televisoredaowithservices.service.televisore;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.prova.televisoredaowithservices.connection.MyConnection;
import it.prova.televisoredaowithservices.dao.Constants;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAOImpl;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDao;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreServiceImpl implements TelevisoreService {

	private TelevisoreDao televisoreDao;

	public void setTelevisoreDao(TelevisoreDao televisoreDao) {
		this.televisoreDao = televisoreDao;
	}

	@Override
	public List<Televisore> listAll() throws Exception {

		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore get(Long idInput) throws Exception {

		Televisore result = new Televisore();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Televisore input) throws Exception {

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Televisore input) throws Exception {
		
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		int result=0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
}

	@Override
	public int delete(Televisore input) throws Exception {
		
		if (input == null || input.getId() == null || input.getId() <1)
			throw new Exception("Valore di input non ammesso.");
		
		int result = 0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
}
}
