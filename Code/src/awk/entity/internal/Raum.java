package awk.entity.internal;


public class Raum {

	private String name;
	private boolean isComputerraum;
	
	public Raum (String name, boolean isComputerraum)
	{
		this.name = name;
		this.isComputerraum = isComputerraum;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isComputerraum() {
		return isComputerraum;
	}
	public void setComputerraum(boolean isComputerraum) {
		this.isComputerraum = isComputerraum;
	}
	
	@Override
	public boolean equals(Object obj) {
		Raum r = (Raum) obj;
		if(r.getName().equals(this.getName())){
			return true;
		}
		return false;
	}
	
	
}
