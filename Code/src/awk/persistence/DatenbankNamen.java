package awk.persistence;


/*
 * Helferklasse, die die Namen der Datenbanktabellen zur Verfügung stellt.
 */
public abstract class DatenbankNamen {

	public abstract class Dozent{
		public static final String Tabelle = "SP_DOZENT";
		public static final String DozentNummer = "DNR";
		public static final String Name = "NAME";
	}
	
	public abstract class Modul{
		public static final String Tabelle = "SP_MODUL";
		public static final String ModulNummer = "MNR";
		public static final String DozentNummer = "DNR";
		public static final String Name = "NAME";
		public static final String BenoetigtComputerraum = "ISCOMPUTERNOTWENDIG";
	}
	
	public abstract class Raum{
		public static final String Tabelle = "SP_RAUM";
		public static final String RaumNummer = "RNR";
		public static final String Name = "NAME";
		public static final String Computerraum = "COMPUTERRAUM";
	}
	
	public abstract class Studiengang{
		public static final String Tabelle = "SP_STUDIENGANG";
		public static final String StudiengangNummer = "SGNR";
		public static final String Name = "NAME";
	}
	
	public abstract class Stundenplan{
		public static final String Tabelle = "SP_STUNDENPLAN";
		public static final String StundenplanNummer = "STDPNR";
		public static final String StudiengangNummer = "SGNR";
		public static final String ModulNummer = "MNR";
		public static final String Zeitslot = "ZEITSLOT";
		public static final String RaumNummer = "RNR";
	}
	
	public abstract class ModulStudiengangZuordnung{
		public static final String Tabelle = "SP_MODUL_STUDIENGANG";
		public static final String ModulNummer = "MNR";
		public static final String StudiengangNummer = "SGNR";
	}
	
	public abstract class ZeitpraeferenzDozentZuordnung{
		public static final String Tabelle = "SP_ZEITPRAEFERENZ_DOZENT";
		public static final String ZeitprefDozentNummer = "SPDNR";
		public static final String DozentNummer = "DNR";
		public static final String Zeitslot = "ZEITSLOT";
	}
	
	
	
	
}
