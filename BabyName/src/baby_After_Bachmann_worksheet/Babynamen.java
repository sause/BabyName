package baby_After_Bachmann_worksheet;

public class Babynamen {

	private String name;
	private String geschlecht;
	private String ranglisteSchweiz;
	private String ranglisteAllerNamen;
	private String ranglisteWelt;
	private String biblisch;
	
	
//	public String print() {
//		return (name + " - " + geschlecht + " - " + ranglisteSchweiz + " - " + ranglisteAllerNamen + " - " + ranglisteWelt + " - " + biblisch);
//	}
	
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGeschlecht() {
        return geschlecht;
    }
    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }
    public String getRanglisteSchweiz() {
        return ranglisteSchweiz;
    }
    public void setRanglisteSchweiz(String ranglisteSchweiz) {
        this.ranglisteSchweiz = ranglisteSchweiz;
    }
    public String getRanglisteAllerNamen() {
        return ranglisteAllerNamen;
    }
    public void setRanglisteAllerNamen(String ranglisteAllerNamen) {
        this.ranglisteAllerNamen = ranglisteAllerNamen;
    }
    public String getRanglisteWelt() {
        return ranglisteWelt;
    }
    public void setRanglisteWelt(String ranglisteWelt) {
        this.ranglisteWelt = ranglisteWelt;
    }
    public String getBiblisch() {
        return biblisch;
    }
    public void setBiblisch(String biblisch) {
        this.biblisch = biblisch;
    }

//@Override - wäre nur für Ausgabe auf Konsole gut. 
    public String toString() {
        return "Name [name=" + name + ", geschlecht=" + geschlecht
                + ", "+"top100=" + ranglisteSchweiz + ", " + ranglisteAllerNamen + ", " + ranglisteWelt + ", "+"biblisch=" + biblisch + "]";   

}


public Babynamen(String name, String geschlecht, String ranglisteSchweiz, String ranglisteAllerNamen,
		String ranglisteWelt, String biblisch) {
	this.name = new String(name);
	this.geschlecht = new String(geschlecht);
	this.ranglisteSchweiz = new String(ranglisteSchweiz);
	this.ranglisteAllerNamen = new String(ranglisteAllerNamen);
	this.ranglisteWelt = new String(ranglisteWelt);
	this.biblisch = new String(biblisch);
}


}

