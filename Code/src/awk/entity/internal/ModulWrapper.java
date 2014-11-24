package awk.entity.internal;

/**
 * Wrapper-Klasse, die ein Modul im angezeigten Studenplan repräsentiert.
 */
public class ModulWrapper {

	private String modulName;
	private String dozentName;
	private String raumName;
	private String zeit;
	
	public ModulWrapper(String modulName, String dozentName, String raumName, String zeit)
	{
		this.modulName = modulName;
		this.dozentName = dozentName;
		this.raumName = raumName;
		this.zeit = zeit;
	}
	
	public String getModulName() {
		return modulName;
	}
	public void setModulName(String modulName) {
		this.modulName = modulName;
	}
	public String getDozentName() {
		return dozentName;
	}
	public void setDozentName(String dozentName) {
		this.dozentName = dozentName;
	}
	public String getRaumName() {
		return raumName;
	}
	public void setRaumName(String raumName) {
		this.raumName = raumName;
	}
	public String getZeit() {
		return zeit;
	}
	public void setZeit(String zeit) {
		this.zeit = zeit;
	}
	
	
}
