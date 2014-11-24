package awk.entity;

import java.io.Serializable;

import awk.entity.internal.Uhrzeit;

public class UhrzeitTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tag;
	private int block;
	
	public UhrzeitTO(){
		
	}
	
	public Uhrzeit toUhrzeit(){
		return new Uhrzeit(this.getTag(), this.getBlock());
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
