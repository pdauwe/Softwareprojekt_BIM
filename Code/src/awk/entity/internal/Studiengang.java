package awk.entity.internal;

public class Studiengang {

	private String name;
	
	public Studiengang(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		Studiengang sgang = (Studiengang) obj;
		if(this.getName().equals(sgang.getName())){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
}
