package awk.entity.internal;

public class Uhrzeit {

	private String tag;
	private int block;
	
	public Uhrzeit (String tag, int block){
		this.tag = tag;
		this.block = block;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}
	
	
}
