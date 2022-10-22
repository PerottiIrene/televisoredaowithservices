package it.prova.televisoredaowithservices.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDao;
import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.service.MyServiceFactory;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;

public class TestTelevisore {

	public static void main(String[] args) {

		TelevisoreService televisoreService = MyServiceFactory.getTelevisoreServiceImpl();

		try {

			// ora con il service posso fare tutte le invocazioni che mi servono
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");
			
			testInsert(televisoreService);
			System.out.println("in tabella ci sono " + televisoreService.listAll().size() + "elementi.");
			
			testFindByExample(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");
			
			testFindById(televisoreService);
			System.out.println("in tabrlla ci sono " + televisoreService.listAll().size() + "elementi");
			
			testUpdate(televisoreService);
			System.out.println("in tabella ci sono " + televisoreService.listAll().size() + "elementi" );
			
			testDelete(televisoreService);
			System.out.println("in tabella ci sono " + televisoreService.listAll().size() + "elementi" );
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void testFindById(TelevisoreService televisoreService) throws Exception {
		
		Televisore televisore1=new Televisore("apple","smart",60,new Date());
		televisoreService.insert(televisore1);
		
		
		Televisore televisoreCercatoConId= televisoreService.get(televisoreService.listAll().get(0).getId());
		if(!televisoreCercatoConId.getMarca().equals(televisore1.getMarca()))
			throw new RuntimeException("testFindById FAILED ");
		
		System.out.println("test findById andato a buon fine");
		
		for (Televisore element : televisoreService.listAll()) {
			televisoreService.delete(element);
		}
	}


	private static void testFindByExample(TelevisoreService televisoreService) throws Exception {
	
		System.out.println(".......testFindByExample inizio.............");
		// inserisco i dati che poi mi aspetto di ritrovare
		televisoreService.insert(new Televisore("samsung", "noSmart", 50, new Date()));
		televisoreService.insert(new Televisore("apple", "smart", 20, new Date()));

		// preparo un example che ha come nome 'as' e ricerco
		List<Televisore> risultatifindByExample = televisoreService.findByExample(new Televisore("apple"));
		if (risultatifindByExample.size() != 2)
			throw new RuntimeException("testFindByExample FAILED ");

		// se sono qui il test è ok quindi ripulisco i dati che ho inserito altrimenti
		// la prossima volta non sarebbero 2 ma 4, ecc.
		for (Televisore element : risultatifindByExample) {
			televisoreService.delete(element);
		}

		System.out.println(".......testFindByExample PASSED.............");
	}


	public static void  testInsert(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testInserimentoNuovaTelevisone inizio.............");
		Date dataProduzione1 = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2020");
		Televisore newTelevisoreInstance = new Televisore ("samsung","smart", 45, dataProduzione1);
		if (televisoreService.insert(newTelevisoreInstance) != 1)
			throw new RuntimeException("testInserimentoNuovaTelevisione FAILED ");

		System.out.println(".......testInserimentoNuovaTelevisione PASSED.............");
		
		for (Televisore element : televisoreService.listAll()) {
			televisoreService.delete(element);
		}
    }
	
	private static void testUpdate(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testUpdateTelevisore inizio.............");

		// inserisco i dati che poi modifico
		Date dataProduzione = new SimpleDateFormat("dd-MM-yyyy").parse("03-07-2022");
		if (televisoreService.insert(new Televisore("apple", "no", 30, dataProduzione)) != 1)
			throw new RuntimeException("testUpdateTelevisore: inserimento preliminare FAILED ");

		// recupero col findbyexample e mi aspetto di trovarla
		List<Televisore> risultatifindByExample = televisoreService.findByExample(new Televisore("apple","no"));
		if (risultatifindByExample.size() != 1)
			throw new RuntimeException("testUpdateTelevisore: testFindByExample FAILED ");

		//mi metto da parte l'id su cui lavorare per il test
		Long idTelevisoreApple= risultatifindByExample.get(0).getId();
		
		// ricarico per sicurezza con l'id individuato e gli modifico un campo
		String nuovoModello= "samsung";
		Televisore toBeUpdated = televisoreService.get(idTelevisoreApple);
		toBeUpdated.setModello(nuovoModello);
		if (televisoreService.update(toBeUpdated) != 1)
			throw new RuntimeException("testUpdateTelevisore FAILED ");
		
		for (Televisore element : televisoreService.listAll()) {
			televisoreService.delete(element);
		}

		System.out.println(".......testUpdateTelevisore PASSED.............");
	}
	
	private static void testDelete(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testRimozioneTelevisore inizio.............");
		
		Televisore televisore1=new Televisore("apple","smart",60,new Date());
		televisoreService.insert(televisore1);
		
		// recupero tutti gli user
		List<Televisore> interoContenutoTabella = televisoreService.listAll();
		if (interoContenutoTabella.isEmpty() || interoContenutoTabella.get(0) == null)
			throw new Exception("Non ho nulla da rimuovere");

		Long idDelPrimo = interoContenutoTabella.get(0).getId();
		// ricarico per sicurezza con l'id individuato
		Televisore toBeRemoved = televisoreService.get(idDelPrimo);
		if (televisoreService.delete(toBeRemoved) != 1)
			throw new RuntimeException("testRimozioneTelevisore FAILED ");

		System.out.println(".......testRimozioneTelevisore PASSED.............");
	}
}
