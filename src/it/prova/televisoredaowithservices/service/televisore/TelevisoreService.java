package it.prova.televisoredaowithservices.service.televisore;

import java.util.List;

import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAOImpl;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDao;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreService {
	
	// questo mi serve per injection
    public void setTelevisoreDao(TelevisoreDao televisoreDao);

	public List<Televisore> listAll() throws Exception;
	
	public Televisore get(Long idInput) throws Exception;
	
	public int update(Televisore input) throws Exception;
	
	public int insert(Televisore input) throws Exception;
	
	public List<Televisore> findByExample(Televisore input) throws Exception;
	
	public int delete(Televisore input) throws Exception;
	

}
