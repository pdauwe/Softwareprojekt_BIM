package awk.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.DozentTO;
import awk.entity.ModulTO;
import awk.entity.RaumTO;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanSlotTO;
import awk.entity.StundenplanTO;
import awk.persistence.DatenbankNamen;
import awk.persistence.IStundenplanDatenzugriff;
import awk.usecase.impl.DozentManager;
import awk.usecase.impl.ModulManager;
import awk.usecase.impl.RaumManager;
import awk.usecase.impl.StudiengangManager;

public class StundenplanDatenzugriff implements IStundenplanDatenzugriff {

	@Override
	public ArrayList<DozentTO> alleDozenten() throws DatenhaltungsException {
		
		ArrayList<DozentTO> alleDozenten = new ArrayList<DozentTO>();
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * FROM " + DatenbankNamen.Dozent.Tabelle);
			
			while(resultSet.next()){
				DozentTO dozent = new DozentTO();
				dozent.setName(resultSet.getString(DatenbankNamen.Dozent.Name));
				dozent.setZeiten(this.zeitPrefsFuerDozent(resultSet.getInt(DatenbankNamen.Dozent.DozentNummer)));
				alleDozenten.add(dozent);
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		
		return alleDozenten;
	}

	@Override
	public ArrayList<ModulTO> alleModule() throws DatenhaltungsException {
		
		ArrayList<ModulTO> alleModule = new ArrayList<ModulTO>();
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * FROM " + DatenbankNamen.Modul.Tabelle);
			
			while(resultSet.next()){
				ModulTO modul = new ModulTO();
				modul.setNummer(resultSet.getInt(DatenbankNamen.Modul.ModulNummer));
				modul.setBenoetigtComputerraum( resultSet.getString(DatenbankNamen.Modul.BenoetigtComputerraum).charAt(0) == 'Y' ? true : false);
				
				alleModule.add(modul);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		
		return alleModule;
	}

	@Override
	public ArrayList<StudiengangTO> alleStudiengaenge() throws DatenhaltungsException {
		
		ArrayList<StudiengangTO> alleStudiengaenge = new ArrayList<StudiengangTO>();
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * FROM " + DatenbankNamen.Studiengang.Tabelle);
			
			while(resultSet.next()){
				StudiengangTO studiengang = new StudiengangTO();
				studiengang.setName(resultSet.getString(DatenbankNamen.Studiengang.Name));
				
				alleStudiengaenge.add(studiengang);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		
		return alleStudiengaenge;
	}

	@Override
	public ArrayList<RaumTO> alleRaeume() throws DatenhaltungsException {
		
		ArrayList<RaumTO> alleRaeume = new ArrayList<RaumTO>();
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * FROM " + DatenbankNamen.Raum.Tabelle);
			
			while(resultSet.next()){
				RaumTO raum = new RaumTO();
				raum.setName(resultSet.getString(DatenbankNamen.Raum.Name));
				raum.setComputerraum(resultSet.getString(DatenbankNamen.Raum.Computerraum).charAt(0) == 'Y' ? true : false);
				
				alleRaeume.add(raum);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		
		return alleRaeume;
	}

	@Override
	public boolean speichereDozentZeitpraeferenzen(DozentTO dozent)
			throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		
		try{
			// Nummer des Dozenten aus der Datenbank auslesen
			ResultSet resultSet = Persistence.executeQueryStatement(aConnection, "SELECT " + DatenbankNamen.Dozent.DozentNummer + " FROM " + DatenbankNamen.Dozent.Tabelle + " WHERE " + DatenbankNamen.Dozent.Name + " LIKE '" + dozent.getName() + "'");
			if(resultSet.next()){
				int nummer = resultSet.getInt("DNR");
				// Alle Zeitpraeferenzen des Dozenten loeschen bevor die neuen hinzugefügt werden.
				Persistence.executeUpdateStatement(aConnection, "DELETE FROM " + DatenbankNamen.ZeitpraeferenzDozentZuordnung.Tabelle + " WHERE " + DatenbankNamen.ZeitpraeferenzDozentZuordnung.DozentNummer + " = " + nummer);
				
				//Neue Zeitpraeferenzen in die Tabelle schreiben
				for(Integer zeitpref : dozent.getZeiten()){
					Persistence.executeUpdateStatement(aConnection, "INSERT INTO " + DatenbankNamen.ZeitpraeferenzDozentZuordnung.Tabelle + " VALUES(" + zeitpref + "," + nummer + ")");
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		return true;
	}

	@Override
	public boolean speichereStundenplan(StundenplanTO stundenplan)
			throws DatenhaltungsException {

		Connection aConnection = Persistence.getConnection();

		try{
			for (Entry<Integer, StundenplanSlotTO> entry : stundenplan.getZuordnung().entrySet()) {
			
				int modulnummer = -1;
				try {
					modulnummer = ModulManager.getManager().modulNummerVonModul(entry.getValue().getModul());
				} catch (AnwendungskernException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int studiengangnummer = -1;
				try {
					studiengangnummer = StudiengangManager.getManager().studiengangNummerVonStudiengang(stundenplan.getStudiengang());
				} catch (AnwendungskernException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int raumnummer = -1;
				try {
					raumnummer = RaumManager.getManager().raumNummerVonRaum(entry.getValue().getRaum());
				} catch (AnwendungskernException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(modulnummer >= 0 && studiengangnummer >= 0 && raumnummer >= 0){
					Persistence.executeUpdateStatement(aConnection, 
							"INSERT INTO " + DatenbankNamen.Stundenplan.Tabelle + " VALUES(" + studiengangnummer + 
							"," + entry.getKey() + "," +
							modulnummer + "," + raumnummer + ")");
				}
			}
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
	}

	@Override
	public StundenplanTO ladeStundenplanFuerStudiengang(
			StudiengangTO studiengang) throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		StundenplanTO stundenplan = new StundenplanTO();
		stundenplan.setStudiengang(studiengang);
		try{
			
			int studiengangNummer = -1;
			try {
				studiengangNummer = StudiengangManager.getManager().studiengangNummerVonStudiengang(studiengang);
			} catch (AnwendungskernException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(studiengangNummer < 0){
				return null;
			}
			
			resultSet = Persistence.executeQueryStatement(aConnection,
					"SELECT * FROM "+ DatenbankNamen.Stundenplan.Tabelle +" WHERE sgnr = " + studiengangNummer
					);
			
			while(resultSet.next()){
				StundenplanSlotTO slot = new StundenplanSlotTO();
				ModulTO modul = null;
				DozentTO dozent = null;
				RaumTO raum = null;
				int zeitslot = resultSet.getInt(DatenbankNamen.Stundenplan.Zeitslot);
				try {
					int modulNummer = resultSet.getInt(DatenbankNamen.Stundenplan.ModulNummer);
					int raumNummer = resultSet.getInt(DatenbankNamen.Stundenplan.RaumNummer);
					 modul = ModulManager.getManager().modulMitNummer(modulNummer);
					 dozent = DozentManager.getManager().dozentVonModulNummer(modulNummer);
					 raum = RaumManager.getManager().raumMitNummer(raumNummer);
				} catch (AnwendungskernException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				slot.setDozent(dozent);
				slot.setModul(modul);
				slot.setRaum(raum);
			
				stundenplan.addZuordnung(zeitslot, slot);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}

		return stundenplan;
	}
	
	@Override
	public ArrayList<ModulTO> moduleVonDozent(int dozentNummer)
			throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			ArrayList<ModulTO> module = new ArrayList<ModulTO>();
			
			resultSet = Persistence.executeQueryStatement(aConnection,
					"SELECT * FROM " + DatenbankNamen.Modul.Tabelle + " WHERE " + DatenbankNamen.Modul.DozentNummer + " = " + dozentNummer);
			
			while(resultSet.next()){
				ModulTO m = new ModulTO();
				m.setBezeichnung(resultSet.getString(DatenbankNamen.Modul.Name));
				m.setBenoetigtComputerraum(resultSet.getString(DatenbankNamen.Modul.BenoetigtComputerraum).charAt(0) == 'Y' ? true : false);
				module.add(m);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		
		return null;
	}
	
	public DozentTO dozentMitNummer(int nummer) throws DatenhaltungsException{
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		DozentTO d = null;
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, 
					"SELECT * FROM " + DatenbankNamen.Dozent.Tabelle +
					" WHERE " + DatenbankNamen.Dozent.DozentNummer +  " = " + nummer);
			
			if(resultSet.next()){
				d = new DozentTO();
				d.setName(resultSet.getString(DatenbankNamen.Dozent.Name));
				d.setZeiten(this.zeitPrefsFuerDozent(nummer));
			}	
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		return d;
	}
	
	

	@Override
	public ArrayList<DozentTO> dozentenMitZeitprefUndStudiengang(int zeitpref,
			StudiengangTO studiengang) throws DatenhaltungsException {
		
		ArrayList<DozentTO> dozenten = new ArrayList<DozentTO>();
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection,
					"SELECT d.dnr from sp_dozent d, sp_modul_studiengang ms, sp_modul m, sp_studiengang s, sp_zeitpraeferenz_dozent z " +
					  "WHERE d.dnr = m.dnr AND m.mnr = ms.mnr AND ms.sgnr = s.sgnr AND d.dnr = z.dnr AND s.name = '" + studiengang.getName() + "' AND "
							+"z.zeitslot = " + zeitpref
					);
			
			
			while(resultSet.next()){
				
				
				DozentTO d;
				try {
					d = DozentManager.getManager().dozentMitNummer(resultSet.getInt(DatenbankNamen.Dozent.DozentNummer));
					dozenten.add(d);
				} catch (AnwendungskernException e) {
					e.printStackTrace();
				}
				
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}

		return dozenten;
	}

	@Override
	public ArrayList<DozentTO> dozentenVonStudiengang(StudiengangTO studiengang)
			throws DatenhaltungsException {
		
		ArrayList<DozentTO> dozenten = new ArrayList<DozentTO>();
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, 
					"SELECT d.dnr from sp_dozent d, sp_modul_studiengang ms, sp_modul m, sp_studiengang s " +
						 "WHERE d.dnr = m.dnr AND m.mnr = ms.mnr AND ms.sgnr = s.sgnr AND s.name = '" + studiengang.getName() + "'"
					);
			
			System.out.println(resultSet);
			while( resultSet.next() ){

				try {
					DozentTO d = DozentManager.getManager().dozentMitNummer(resultSet.getInt(DatenbankNamen.Dozent.DozentNummer));
					dozenten.add(d);
				} catch (AnwendungskernException e) {
					e.printStackTrace();
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}

		return dozenten;
	}
	
	
	private ArrayList<Integer> zeitPrefsFuerDozent(int dozentNummer) throws DatenhaltungsException{
		ArrayList<Integer> zeitPrefs = new ArrayList<Integer>();
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * FROM " + DatenbankNamen.ZeitpraeferenzDozentZuordnung.Tabelle + " WHERE " + DatenbankNamen.ZeitpraeferenzDozentZuordnung.DozentNummer + " = " + dozentNummer);
			while(resultSet.next()){
				zeitPrefs.add(resultSet.getInt(DatenbankNamen.ZeitpraeferenzDozentZuordnung.Zeitslot));
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		
		return zeitPrefs;
	}

	@Override
	public ArrayList<ModulTO> moduleVonDozentImStudiengang(DozentTO dozent,
			StudiengangTO studiengang) throws DatenhaltungsException {
		
		ArrayList<ModulTO> alleModule = new ArrayList<ModulTO>();
		
		Connection connection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			
			int dnr = -1;
			try {
				dnr = DozentManager.getManager().dozentNummerVonDozent(dozent);
			} catch (AnwendungskernException e) {
				e.printStackTrace();
			}
			
			if(dnr != -1){
				resultSet = Persistence.executeQueryStatement(connection,
						"SELECT DISTINCT m.name, m.iscomputernotwendig FROM " +
						"sp_dozent d, sp_modul m, sp_modul_studiengang ms, sp_studiengang s " +
						"WHERE "+
						"d.dnr = m.dnr AND m.mnr = ms.mnr AND s.sgnr = ms.sgnr AND d.dnr = " + dnr + " AND s.name = '" + studiengang.getName() + "'");	
				
				while(resultSet.next()){
					ModulTO modul = new ModulTO();
					modul.setBezeichnung(resultSet.getString(DatenbankNamen.Modul.Name));
					modul.setBenoetigtComputerraum( resultSet.getString(DatenbankNamen.Modul.BenoetigtComputerraum).charAt(0) == 'Y' ? true : false);
					
					alleModule.add(modul);
				}
				
				
			}else{
				return null;
			}

		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}

		return alleModule;
	}

	@Override
	public int dozentNummerVonDozent(DozentTO dozent)
			throws DatenhaltungsException {
		
		Connection connection = Persistence.getConnection();
		ResultSet resultSet;
		
		// dnr auf -1 setzen, so kann mit -1 ueberprueft werden, ob eine dnr legitim ist oder ob der Dozent nicht in der Datenbank ist
		int dnr = -1;
		
		try{
			resultSet = Persistence.executeQueryStatement(connection,
					"SELECT d.dnr FROM " +
					"sp_dozent d " +
					"WHERE " +
					"d.name = '" + dozent.getName() + "'");
			
			if(resultSet.next()){
				dnr = resultSet.getInt(DatenbankNamen.Dozent.DozentNummer);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}
		
		return dnr;
	}

	@Override
	public int modulNummerVonModul(ModulTO modul) throws DatenhaltungsException {
		Connection connection = Persistence.getConnection();
		ResultSet resultSet;
		
		
		int modulNummer = -1;
		
		try{
			resultSet = Persistence.executeQueryStatement(connection,
					"SELECT m.mnr FROM " +
					"sp_modul m " +
					"WHERE " +
					"m.name = '" + modul.getBezeichnung() + "'");
			
			if(resultSet.next()){
				modulNummer = resultSet.getInt(DatenbankNamen.Modul.ModulNummer);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}
		
		return modulNummer;
	}

	@Override
	public int studiengangNummerVonStudiengang(StudiengangTO studiengang)
			throws DatenhaltungsException {
		
		Connection connection = Persistence.getConnection();
		ResultSet resultSet;
		
		
		int studiengangNummer = -1;
		
		try{
			resultSet = Persistence.executeQueryStatement(connection,
					"SELECT s.sgnr FROM " +
					"sp_studiengang s " +
					"WHERE " +
					"s.name = '" + studiengang.getName() + "'");
			
			if(resultSet.next()){
				studiengangNummer = resultSet.getInt(DatenbankNamen.Studiengang.StudiengangNummer);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}
		
		return studiengangNummer;
	}

	@Override
	public int raumNummerVonRaum(RaumTO raum) throws DatenhaltungsException {
		
		Connection connection = Persistence.getConnection();
		ResultSet resultSet;
		
		
		int raumNummer = -1;
		
		try{
			resultSet = Persistence.executeQueryStatement(connection,
					"SELECT r.rnr FROM " +
					"sp_raum r " +
					"WHERE " +
					"r.name = '" + raum.getName() + "'");
			
			if(resultSet.next()){
				raumNummer = resultSet.getInt(DatenbankNamen.Raum.RaumNummer);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}
		
		return raumNummer;
	}

	@Override
	public ModulTO modulMitNummer(int modulNummer)
			throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		ModulTO m = null;
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, 
					"SELECT * FROM sp_modul" + 
					" WHERE mnr = " + modulNummer);
			
			if(resultSet.next()){
				m = new ModulTO();
				m.setBezeichnung(resultSet.getString(DatenbankNamen.Modul.Name));
				m.setBenoetigtComputerraum((resultSet.getString(DatenbankNamen.Modul.BenoetigtComputerraum).charAt(0) == 'Y' ? true : false));
			}	
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		return m;
	}

	@Override
	public RaumTO raumMitNummer(int raumNummer) throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		RaumTO r = null;
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, 
					"SELECT * FROM sp_raum" + 
					" WHERE rnr = " + raumNummer);
			
			if(resultSet.next()){
				r = new RaumTO();
				r.setName(resultSet.getString(DatenbankNamen.Raum.Name));
				r.setComputerraum((resultSet.getString(DatenbankNamen.Raum.Computerraum).charAt(0) == 'Y' ? true : false));
			}	
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		return r;
	}

	@Override
	public DozentTO dozentVonModulNummer(int modulNummer)
			throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		DozentTO d = null;
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, 
					"SELECT d.name FROM sp_dozent d, sp_modul m "
					+ "WHERE m.dnr = d.dnr AND m.mnr = " + modulNummer);
			
			if(resultSet.next()){
				d = new DozentTO();
				d.setName(resultSet.getString(DatenbankNamen.Dozent.Name));
			}	
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		return d;
	}

	@Override
	public RaumTO raumFuerModul(ModulTO modul) throws DatenhaltungsException {
		
		RaumTO raum = null;
		Connection connection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			if(modul.isBenoetigtComputerraum()){
				resultSet = Persistence.executeQueryStatement(connection,
					"SELECT * FROM " + DatenbankNamen.Raum.Tabelle +
					" WHERE " + DatenbankNamen.Raum.Computerraum + " = 'Y'");
			}else{
				resultSet = Persistence.executeQueryStatement(connection,
					"SELECT * FROM " + DatenbankNamen.Raum.Tabelle +
					" WHERE " + DatenbankNamen.Raum.Computerraum + " = 'N'");
			}
			
			if(resultSet.next()){
				raum = new RaumTO();
				raum.setName(resultSet.getString(DatenbankNamen.Raum.Name));
				raum.setComputerraum(resultSet.getString(DatenbankNamen.Raum.Computerraum).charAt(0) == 'Y' ? true : false);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}

		return raum;
	}
	
	public boolean deleteUrplan() throws DatenhaltungsException{
		Connection connection = Persistence.getConnection();
		try{
			Persistence.executeUpdateStatement(connection, "DELETE FROM " + DatenbankNamen.Stundenplan.Tabelle);
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}
		return true;
	}

	@Override
	public boolean doesUrplanExists() throws DatenhaltungsException {
		Connection connection = Persistence.getConnection();
		ResultSet resultSet;
		try{
			resultSet = Persistence.executeQueryStatement(connection, "SELECT * FROM " + DatenbankNamen.Stundenplan.Tabelle + " WHERE ROWNUM < 2");
			if(resultSet.next()){
				return true;
			}else{
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}
	}

	@Override
	public boolean loescheDozentZeitpraeferenzen(DozentTO dozent)
			throws DatenhaltungsException {
		
		int dozentNummer = -1;
		try {
			dozentNummer = DozentManager.getManager().dozentNummerVonDozent(dozent);
		} catch (AnwendungskernException e) {
			e.printStackTrace();
		}
		
		Connection connection = Persistence.getConnection();
		try{
			if(dozentNummer != -1){
				Persistence.executeUpdateStatement(connection, 
					"DELETE FROM " + DatenbankNamen.ZeitpraeferenzDozentZuordnung.Tabelle + " WHERE "
							+ DatenbankNamen.ZeitpraeferenzDozentZuordnung.DozentNummer + " = " + dozentNummer);
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}
		return false;
	}

	@Override
	public int maxModuleFuerStudiengang(StudiengangTO studiengang) throws DatenhaltungsException {
		
		Connection connection = Persistence.getConnection();
		ResultSet resultSet;
		int count = 0;
		int sgnr = -1;
		try {
			 sgnr = StudiengangManager.getManager().studiengangNummerVonStudiengang(studiengang);
		} catch (AnwendungskernException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			if(sgnr != -1){
				resultSet = Persistence.executeQueryStatement(connection,
						"SELECT COUNT(" + DatenbankNamen.ModulStudiengangZuordnung.StudiengangNummer + ") AS anzahl FROM " + DatenbankNamen.ModulStudiengangZuordnung.Tabelle +
								" WHERE " + DatenbankNamen.ModulStudiengangZuordnung.StudiengangNummer + " = " + sgnr);
				if(resultSet.next()){
					count = resultSet.getInt("anzahl");
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}
		return count;
	}

	@Override
	public int anzahlStudiengaenge() throws DatenhaltungsException {
		Connection connection = Persistence.getConnection();
		ResultSet resultSet;
		int count = 0;
	
		try{
			resultSet = Persistence.executeQueryStatement(connection,
					"SELECT COUNT(COUNT(DISTINCT sgnr)) as anzahl FROM sp_modul_studiengang GROUP BY sgnr");
			if(resultSet.next()){
				count = resultSet.getInt("anzahl");
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}
		return count;
	}
	
}
