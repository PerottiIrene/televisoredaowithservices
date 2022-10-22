package it.prova.televisoredaowithservices.dao.televisore;

import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.IBaseDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreDao extends IBaseDAO <Televisore>{
	
	public List<Televisore> televisoriProdottiInIntervalloDiDate (Date dataInizio,Date dataFine) throws Exception;
	
	public Televisore televisorePiuGrande() throws Exception;
	
	public List<String> marcheDiTelevisoriProdottiNegliUltimiSeiMesi (Date dataSeiMesiFa) throws Exception;

}
