package com.classes;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.ArrayList;

public class Keyboard implements Serializable {
	private static int idBase = 1;
	private final int id;
	private String name;
	private String manufacturer;
	private int year;
	private boolean ergonomic;
	private boolean backlight;
	private String layout;
	private ArrayList<String> commenti;
	
	
	public Keyboard() {
		this.id = idBase;
		idBase++;
		this.commenti = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public boolean isErgonomic() {
		return ergonomic;
	}
	public void setErgonomic(boolean ergonomic) {
		this.ergonomic = ergonomic;
	}
	public boolean isBacklight() {
		return backlight;
	}
	public void setBacklight(boolean backlight) {
		this.backlight = backlight;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) throws InvalidObjectException {
		if(!layout.equals("QWERTY") && !layout.equals("AZERTY") && !layout.equals("Dvorak")) throw new InvalidObjectException(layout + " is not accepted");
		else this.layout = layout;
	}

	public int getId() {
		return id;
	}

	public static void setIdBase(int set){
		idBase = set;
	}
	public ArrayList<String> getCommenti() {
		return commenti;
	}
	public void setCommenti(ArrayList<String> commenti) {
		this.commenti = commenti;
	}
	public void addCommenti(String commento){
		if(commento != null) this.commenti.add(commento);
	}

	public void setAll(String name, String manufacturer, int year, boolean ergonomic, boolean backlight, String layout) throws InvalidObjectException {
		this.setName(name);
		this.setManufacturer(manufacturer);
		this.setYear(year);
		this.setErgonomic(ergonomic);
		this.setBacklight(backlight);
		this.setLayout(layout);
	}

	@Override
	public String toString() {
		return id + " : [name=" + name + ", manufacturer=" + manufacturer + ", year=" + year + ", ergonomic="
				+ ergonomic + ", backlight=" + backlight + ", layout=" + layout + "]";
	}

}
