package awk.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import awk.DatenhaltungsException;
import awk.entity.DozentTO;
import awk.entity.ModulTO;
import awk.entity.RaumTO;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanTO;
import awk.persistence.DatenbankNamen;
import awk.persistence.IStundenplanDatenzugriff;

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
		
		return null;
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
				modul.setBenoetigtComputerraum( resultSet.getString(DatenbankNamen.Modul.BenoetigtComputerraum).charAt(0) == 'J' ? true : false);
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
				raum.setComputerraum(resultSet.getString(DatenbankNamen.Raum.Computerraum).charAt(0) == 'J' ? true : false);
				
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
	
	/*
	 *  Helpers
	 */
	
	
	private ArrayList<Integer> zeitPrefsFuerDozent(int dozentNummer) throws DatenhaltungsException{
		ArrayList<Integer> zeitPrefs = new ArrayList<Integer>();
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * FROM " + DatenbankNamen.ZeitpraeferenzDozentZuordnung.Tabelle + " WHERE" + DatenbankNamen.ZeitpraeferenzDozentZuordnung.DozentNummer + " = " + dozentNummer);
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
	
}
