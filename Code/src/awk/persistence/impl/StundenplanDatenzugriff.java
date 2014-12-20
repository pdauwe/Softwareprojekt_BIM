package awk.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.DozentTO;
import awk.entity.ModulTO;
import awk.entity.RaumTO;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanTO;
import awk.persistence.DatenbankNamen;
import awk.persistence.IStundenplanDatenzugriff;
import awk.usecase.impl.DozentManager;

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
				modul.setVerplant(false);
				
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
				int nummer = resultSet.getInt(0);
				// Alle Zeitpraeferenzen des Dozenten loeschen bevor die neuen hinzugefügt werden.
				Persistence.executeUpdateStatement(aConnection, "DELETE FROM " + DatenbankNamen.ZeitpraeferenzDozentZuordnung.Tabelle + " WHERE " + DatenbankNamen.ZeitpraeferenzDozentZuordnung.DozentNummer + " = " + nummer);
				
				//Neue Zeitpraeferenzen in die Tabelle schreiben
				for(Integer zeitpref : dozent.getZeiten()){
					Persistence.executeUpdateStatement(aConnection, "INSERT INTO " + DatenbankNamen.ZeitpraeferenzDozentZuordnung.Tabelle + "VALUES(" + nummer + "," + zeitpref + ")");
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StundenplanTO ladeStundenplanFuerStudiengang(
			StudiengangTO studiengang) throws DatenhaltungsException {
		// TODO Auto-generated method stub
		return null;
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
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, 
					"SELECT * FROM " + DatenbankNamen.Dozent.Tabelle + 
					" WHERE " + DatenbankNamen.Dozent.DozentNummer + " = " + nummer);
			
			if(resultSet.next()){
				DozentTO d = new DozentTO();
				d.setName(resultSet.getString(DatenbankNamen.Dozent.Name));
				d.setZeiten(this.zeitPrefsFuerDozent(nummer));
			}	
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		return null;
	}
	
	

	@Override
	public ArrayList<DozentTO> dozentenMitZeitprefUndStudiengang(int zeitpref,
			StudiengangTO studiengang) throws DatenhaltungsException {
		
		ArrayList<DozentTO> dozenten = new ArrayList<DozentTO>();
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection,
					"select d.dnr from sp_dozent d, sp_modul_studiengang ms, sp_modul m, sp_studiengang s, sp_zeitpraeferenz_dozent z" +
					  "where d.dnr = m.dnr AND m.mnr = ms.mnr AND ms.sgnr = s.sgnr AND d.dnr = z.dnr AND s.name = '" + studiengang.getName() + "' AND "
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
		
		/*
		SELECT d.dnr from sp_dozent d, sp_modul m, sp_modul_studiengang ms 
		where d.dnr = m.dnr AND m.mnr = ms.mnr;
		*/
		
		/*
		 * select d.name from sp_dozent d, sp_modul_studiengang ms, sp_modul m, sp_studiengang s
						 where d.dnr = m.dnr AND m.mnr = ms.mnr AND ms.sgnr = s.sgnr AND s.name = 'BIM3';
		 */
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, 
					"select d.dnr from sp_dozent d, sp_modul_studiengang ms, sp_modul m, sp_studiengang s" +
						 "where d.dnr = m.dnr AND m.mnr = ms.mnr AND ms.sgnr = s.sgnr AND s.name = '" + studiengang.getName() + "'"
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
						"SELECT * FROM " +
						"sp_dozent d, sp_modul m, sp_modul_studiengang ms, sp_studiengang s " +
						"WHERE "+
						"d.dnr = m.dnr AND m.mnr = m.mnr AND s.sgnr = ms.sgnr AND d.dnr = " + dnr + " AND s.name = '" + studiengang.getName() + "'");	
				
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
				dnr = resultSet.getInt("dnr");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(connection);
		}
		
		return dnr;
	}
	
}
