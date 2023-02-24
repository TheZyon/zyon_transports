package DAO;

import java.sql.Timestamp;

import org.slf4j.Logger;

import controller.MainProject;
import model.Tessera;
import utils.DateUtil;

public class DAOTessera extends DAO<Tessera> {

	
	Logger log = MainProject.log;
	
	
	/*
	 * verifica se tessera è valida 
	 * se non è valida  rinnova data erogazione e data scadenza
	 * 
	 * */
	public boolean testValiditaTessera(Tessera t) {
		
		var ora = new Timestamp(System.currentTimeMillis());
		
		if(t.getData_scadenza().before(ora)) {//abbonamento scaduto
		return false;
		}
		return true;
	}
	
	public void rinnovoTessera(Tessera t) {
		
		var ora = new Timestamp(System.currentTimeMillis());
		t.setData_erogazione(ora);
		t.setData_scadenza(DateUtil.addDaysTimestamp(ora, 365));
		super.merge(t);
		
	}
	
	
}
