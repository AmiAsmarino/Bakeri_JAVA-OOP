
import java.util.ArrayList;

public class Lager {
	private int Varer_id;
	private String Varer_navn;
	private int En_Vare_Gram;
	private int Totalt_Gram;
	
	public ArrayList<Lager> Varerlist = new ArrayList<Lager>();
	
	public Lager () {
		this.Varer_id = 0;
		this.Varer_navn = null;
		this.En_Vare_Gram = 0;
		this.Totalt_Gram = 0;
		
	}
	public void RegVarer(String V_navn, int V_Gram, int antall_Varer) {
		Lager nyObjVarer = new Lager();
		nyObjVarer.Varer_id = Varer_id++;
		nyObjVarer.Varer_navn = V_navn;
		nyObjVarer.En_Vare_Gram = V_Gram;
		nyObjVarer.Totalt_Gram = V_Gram * antall_Varer ;
		Varerlist.add(nyObjVarer);
	}

	public int getVare_id() {
		return this.Varer_id;
	}
	public String getVare_Navn() {
		return this.Varer_navn;
	}
	public int getVare_Gram() {
		return this.En_Vare_Gram;
	}
	public int getTotalt_Gram() {
		return this.Totalt_Gram;
	}
	public void setVare_Navn(String nyttNavn) {
		this.Varer_navn = nyttNavn;		
	}
	public void setVare_Gram(int nyGram) {
		this.En_Vare_Gram = nyGram;		
	}
	
	public ArrayList<Lager> getLagerList() {
        return this.Varerlist;
    }
}
