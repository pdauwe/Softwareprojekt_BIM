package awk.entity.internal;

public class Modul {

	private int nummer;
	private boolean benoetigtComputerraum;
	private String bezeichnung;
	
	private boolean isVerplant;
	
	public Modul(int nummer, String bezeichnung, boolean benoetigtComputerraum){
		this.nummer = nummer;
		this.benoetigtComputerraum = benoetigtComputerraum;
		this.bezeichnung = bezeichnung;
	}
	
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public boolean isBenoetigtComputerraum() {
		return benoetigtComputerraum;
	}
	public void setBenoetigtComputerraum(boolean benoetigtComputerraum) {
		this.benoetigtComputerraum = benoetigtComputerraum;
	}

	public boolean isVerplant() {
		return isVerplant;
	}

	public void setVerplant(boolean isVerplant) {
		this.isVerplant = isVerplant;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	@Override
	public int hashCode() {
		return this.getBezeichnung().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Modul m = (Modul) obj;
		if(this.getBezeichnung().equals(m.getBezeichnung())){
			return true;
		}
		return false;
	}
	
	
}
