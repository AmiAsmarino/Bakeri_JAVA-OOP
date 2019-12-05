
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Oppskrift {
	private int Opp_id;
	public static int IngOpp_id;
	private String Opp_navn;
	private LocalDate Opp_dato; 
	private String Type_prod;
	public Ingrediens  OppIngrediens;
	public ArrayList<Ingrediens> Lager_Inglist = new ArrayList<Ingrediens>();
	public ArrayList<Ingrediens> Opp_Inglist = new ArrayList<Ingrediens>();
	
	public ArrayList<Oppskrift> OppSkriftlist = new ArrayList<Oppskrift>();
	
	public Oppskrift () {
		this.IngOpp_id = 0;
		this.Opp_id = 0;
		this.Opp_dato = LocalDate.now();
		this.Opp_navn = null;
		this.Type_prod = null;
		this.Opp_Inglist = null;
	}
	
	public void RegOppskrift(String Ny_Opp_navn, String Ny_Type_prod, ArrayList<Ingrediens> Ny_OppIngrediens) {
		
		Oppskrift nyObjOppsk = new Oppskrift();
		nyObjOppsk.Opp_id = Opp_id++;
		nyObjOppsk.Opp_navn = Ny_Opp_navn;
		nyObjOppsk.Opp_dato = LocalDate.now();
		nyObjOppsk.Type_prod = Ny_Type_prod;
		nyObjOppsk.Opp_Inglist = Ny_OppIngrediens;
		
		this.OppSkriftlist.add(nyObjOppsk);
		System.out.println("   Ingrediens List " + OppSkriftlist);
		
		
	}
	public ArrayList<Ingrediens> RegIngOppsk(String IngOppNavn, int IngOppVekt, ArrayList<Ingrediens> Engang_Opp_Inglist) {
		Ingrediens nyObjIngOpp = new Ingrediens();
		
		nyObjIngOpp.setId(this.IngOpp_id);
		nyObjIngOpp.setNavn(IngOppNavn);
		nyObjIngOpp.setVektGram(IngOppVekt);
		this.IngOpp_id +=1;
		Engang_Opp_Inglist.add(nyObjIngOpp);
		return Engang_Opp_Inglist;
		//System.out.println("  Engang Ingrediens List " + Engang_Opp_Inglist);
	}
	
	public int getOpp_id() {
		return this.Opp_id;
	}
	public String getOpp_Navn() {
		return this.Opp_navn;
	}
	public LocalDate getOpp_dato() {
		return this.Opp_dato;
	}
	
	public String getType_prod() {
		return this.Type_prod;
	}
	public ArrayList<Oppskrift> getOppList() {
        return this.OppSkriftlist;
    }
	public ArrayList<Ingrediens> getOppIngList() {
        return this.Opp_Inglist;
    }
	public ArrayList<Ingrediens> getLagerIngList() {
        return this.Lager_Inglist;
    }
	public void setOpp_Navn(String nyttNavn) {
		this.Opp_navn = nyttNavn;		
	}
	
	public void setType_prod(String NyType_prod) {
		this.Type_prod = NyType_prod;		
	}
	
	
	 public static int sammenlig(String først, String andre) {
		 int compare = først.compareTo(andre);
		 if (compare <= 0)
		     return 1;
		 else 
		     return 2;
	    }
	public ArrayList<Oppskrift> ArrSameProd( String newTypeProd) {
		ArrayList<Oppskrift> newArr = new ArrayList<Oppskrift>();
		String NavnTypeProd;
		System.out.println("k result :" );
		int k, resultSamm =0;
		for(int i = 0; i<OppSkriftlist.size(); i++) {
			NavnTypeProd = OppSkriftlist.get(i).Type_prod;
			if(NavnTypeProd == newTypeProd) {
				
				if(newArr.size() == 0) 
					newArr.add(0,OppSkriftlist.get(i));
					
				else {
					for(k=0;k <= newArr.size() ; k++) {
						if(k== newArr.size()) {
							newArr.add(k,OppSkriftlist.get(i));
							break;
						}
						resultSamm = sammenlig(OppSkriftlist.get(i).Opp_navn, newArr.get(k).Opp_navn);
						if(resultSamm != 2) {
							newArr.add(k,OppSkriftlist.get(i));
							break;
							}
					}	
				
				}
			}	
		}
		
		return newArr;
	}
	public ArrayList<Oppskrift> toDagerOpp() {
		ArrayList<Oppskrift> toDagerArr = new ArrayList<Oppskrift>();
		int interv;
		LocalDate idag = LocalDate.now();
		LocalDate OppskDato;
		
		for(int i = 0; i < OppSkriftlist.size(); i++) {
			OppskDato = OppSkriftlist.get(i).Opp_dato;
			Period intervalPeriod = Period.between(OppskDato,idag);
			interv = intervalPeriod.getDays();
			if(interv <= 2) 
				toDagerArr.add(OppSkriftlist.get(i));
		}
		return toDagerArr;
	}
	
	public boolean Sjekk(int antall,int OppId,int LagerIgram) {
		ArrayList<Oppskrift> OppList = getOppList();
		ArrayList<Ingrediens> Ny_Opp_Ing_list = new ArrayList<Ingrediens>();
		int SumIng = 0;
		for(int i=0; i< OppList.size(); i++) {
			if(OppId== OppList.get(i).Opp_id) {
				Ny_Opp_Ing_list = OppList.get(i).getOppIngList();
				for(int j=0; j < Ny_Opp_Ing_list.size(); j++) {
					SumIng += Ny_Opp_Ing_list.get(j).getVektGram();
				}
				if (SumIng * antall <= LagerIgram)
					return true;
				else
					return false;
			}
		}
		return false;
	}
	// Need to test
	public ArrayList<Ingrediens> SpareIngFraLager(int OppSk_id, int antall) {
		Ingrediens IngObject = new Ingrediens();
		ArrayList<Ingrediens> LagerInglist = IngObject.getIngList();
		System.out.println("LagerInglist SIZE :" + LagerInglist);
		ArrayList<Ingrediens>  Ny_IngArr = new ArrayList<Ingrediens>();
		ArrayList<Ingrediens>  IngOppArr = new ArrayList<Ingrediens>();
		String OppIngNavn; 
		int OppIngVekt, OppIngAntall, NumOk = 0;
		ArrayList<Oppskrift> OppList = getOppList();
		//System.out.println(" OppInglist size() :" + OppList.size());
		for(int i=0; i< OppList.size(); i++) {
			if(OppSk_id == OppList.get(i).Opp_id) {
				//OppIngAntall = OppList.get(i).getOpp_id();
				IngOppArr = OppList.get(i).getOppIngList();
				
				OppIngAntall =IngOppArr.size();
				
				for(int j=0; j < OppIngAntall ; j++) {
					OppIngNavn= IngOppArr.get(j).getNavn();			
					OppIngVekt = IngOppArr.get(j).getVektGram();
					System.out.println("LagerInglist SIZE :" + IngObject.getIngList().size());
					for(int k=0; k < LagerInglist.size(); k++) {
						System.out.println("LagerInglist SIZE :" + LagerInglist);
						if(OppIngNavn == LagerInglist.get(k).getNavn() && (antall * OppIngVekt <= LagerInglist.get(k).getVektGram())) {
							
							IngObject.setId(NumOk);
							IngObject.setNavn(OppIngNavn);
							IngObject.setVektGram(antall * OppIngVekt);
							
							Ny_IngArr.add(IngObject);
							
							NumOk ++;
						}
					}
			 }
				if(Ny_IngArr.size() == OppIngAntall )
					return Ny_IngArr;
				  else
					return null;
				
		}
		
	  }
	  return null;
	}
	
	public String toString() {
        return (+this.getOpp_id() +"  "
                + this.getOpp_Navn()+"  " 
                + this.getOpp_dato()+"  "
                + this.getType_prod()+"  ");
   }
	public static void main(String[] args) {
		LocalDate idag = LocalDate.now();
		LocalDate iforgårs = idag.minusDays(2);
		Period intervalPeriod = Period.between(iforgårs,idag);
		int interv = intervalPeriod.getDays();
		if(interv <=2) {
			System.out.println(interv);
			
		}
		
		Oppskrift nyObjOppsk = new Oppskrift();

		ArrayList<Ingrediens> Opp_Inglist = new ArrayList<Ingrediens>();
		Opp_Inglist = nyObjOppsk.RegIngOppsk("smør",30, Opp_Inglist);
		Opp_Inglist = nyObjOppsk.RegIngOppsk("mel",500,Opp_Inglist);
		Opp_Inglist = nyObjOppsk.RegIngOppsk("melk",100,Opp_Inglist);
		nyObjOppsk.RegOppskrift("Verdensbestkake","kake",Opp_Inglist);
		System.out.println("   Verdensbestkake Opp list" +Opp_Inglist);
		IngOpp_id = 0;
		
		
		ArrayList<Ingrediens> Engang_Opp_Inglist = new ArrayList<Ingrediens>();
		Engang_Opp_Inglist = nyObjOppsk.RegIngOppsk("mel",300,Engang_Opp_Inglist);
		Engang_Opp_Inglist = nyObjOppsk.RegIngOppsk("sukker",30,Engang_Opp_Inglist);
		Engang_Opp_Inglist = nyObjOppsk.RegIngOppsk("smør",50,Engang_Opp_Inglist);
		nyObjOppsk.RegOppskrift("dagensbrød","Brød", Engang_Opp_Inglist);
		IngOpp_id = 0;
		System.out.println("   dagensbrød first list" + Engang_Opp_Inglist);
		
		
		ArrayList<Ingrediens> Engang_Opp_Ingli = new ArrayList<Ingrediens>();
		Engang_Opp_Ingli = nyObjOppsk.RegIngOppsk("melk",100,Engang_Opp_Ingli);
		Engang_Opp_Ingli = nyObjOppsk.RegIngOppsk("salt",30,Engang_Opp_Ingli);
		Engang_Opp_Ingli = nyObjOppsk.RegIngOppsk("smør",30, Engang_Opp_Ingli);
		nyObjOppsk.RegOppskrift("Sjokoladekake","kake",Engang_Opp_Ingli);
		IngOpp_id = 0;
		System.out.println("   Sjokoladekake Opp list" +Engang_Opp_Ingli);
		//Engang_Opp_Inglist.clear();
		
		//Engang_Opp_Inglist.clear();
		//nyObjOppsk.RegOppskrift("Verdensbestkake","kake",nyObjOppsk.getOppIngList());
		//nyObjOppsk.RegOppskrift("Gulrotkake","kake",nyObjOppsk.getOppIngList());
		
		//nyObjOppsk.RegIngOppsk("eple",30);
		//nyObjOppsk.RegOppskrift("Eplekake","kake",nyObjOppsk3.Engang_Opp_Inglist);
		
		System.out.println("Oppskrift list  " +nyObjOppsk.getOppList());
		System.out.println("Array of same product" +nyObjOppsk.ArrSameProd("kake"));
		
		
		System.out.println("True eller false :" +nyObjOppsk.Sjekk(10,2, 1600));
		Ingrediens nyIngr = new Ingrediens();
		nyIngr.RegIngrediens("egg",200);
		nyIngr.RegIngrediens("sukker",100);
		nyIngr.RegIngrediens("mel",550);
		nyIngr.RegIngrediens("mel",550);
		nyIngr.RegIngrediens("salt",100);
		nyIngr.RegIngrediens("smør",200);
		nyIngr.RegIngrediens("melk",200);
		System.out.println("Lager Ingrediens list : " +nyIngr.getIngList());
		//SpareIngFraLager(int OppSk_id, int antall)
		System.out.println("Check SpareIngFraLager list " +nyObjOppsk.SpareIngFraLager(0,1));
		
	}
	
	
}
