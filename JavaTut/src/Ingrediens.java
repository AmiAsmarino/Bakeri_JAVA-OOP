import java.util.ArrayList;

public class Ingrediens {

	private int id;
	private String navn;
	private int vektGram;
	public ArrayList<Ingrediens> Inglist = new ArrayList<Ingrediens>();
	//public ArrayList<Ingrediens> Lagerlist = new ArrayList<Ingrediens>();
	
	public Ingrediens () {
		this.id = 0;
		this.navn = null;
		this.vektGram = 0;
		
	}
	
	public void setNavn(String nyttNavn) {
		this.navn = nyttNavn;		
	}
	public void setId(int NyId) {
		this.id = NyId;		
	}
	public void setVektGram(int nyVektGram) {
		this.vektGram = nyVektGram;		
	}
	public int getId() {
		return this.id;
	}
	public String getNavn() {
		return this.navn;
	}
	public int getVektGram() {
		return this.vektGram;
	}
	 public ArrayList<Ingrediens> getIngList() {
	        return this.Inglist;
	    }
	
	
	public Ingrediens getIngrObj(int id) {

		for(int i= 0; i<Inglist.size();i++) {
			if(id == Inglist.get(i).id) {
				return Inglist.get(i);
			}
		}
		return null;
	}

	public void RegIngrediens( String Nynavn, int NyvektGram ) {
		
		 ArrayList<Ingrediens> Ingli = this.getIngList();
		 System.out.println("Lager Ingrediens list : " + Inglist);
		for(int i =0; i < Ingli.size(); i++) {
			if(Ingli.get(i).getNavn() == Nynavn ) {
				Ingli.get(i).vektGram += NyvektGram;
				return;
			}
		}
		Ingrediens nyObjIng = new Ingrediens();
		nyObjIng.id = id++;
		nyObjIng.navn = Nynavn;
		nyObjIng.vektGram = NyvektGram;
		Inglist.add(nyObjIng);
		
	}
	
	
	public void oppdater(int id, int vektGram, String navn) {
		
		getIngrObj(id).vektGram = vektGram;
		getIngrObj(id).navn = navn;
	}
	public void addIngr() {
		RegIngrediens("Egg",200);
		RegIngrediens("Mel",500);
		RegIngrediens("Sukker",350);
	}
	public String toString() {
        return ("  "+this.getId()+
                    "  "+ this.getNavn() +
                    "  "+ this.getVektGram());
   }
	public static void main(String[] args) {
		
		Ingrediens nyObject = new Ingrediens();
		nyObject.addIngr();
		nyObject.toString();
		nyObject.oppdater(2, 100,"Salt");
		System.out.println(nyObject.Inglist);
		
		
		 System.out.println("my new list" + nyObject.getIngList());
	}
	
	
	
}
