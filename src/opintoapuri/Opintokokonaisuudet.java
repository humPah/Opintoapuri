/**
 * 
 */
package opintoapuri;



/**
 * @author Matias Partanen
 * @version Mar 14, 2012
 * Opintokokonaisuudet-luokka, joka lähinnä pitää kirjaa taulukossa yksittäisistä opintokokonaisuuksista ja tekee niille temppuja
 */
public class Opintokokonaisuudet {

	private String tiedNimi = "";
	private final int maxLkm = 10;
	private int lkm;
	private Opintokokonaisuus opintokTaulu[] = new Opintokokonaisuus[maxLkm]; 
	
	
	/**
	 * Konstruktori opintokokonaisuudet-luokalle.
	 */
	public Opintokokonaisuudet() {
		
	}
	
	
	/**
	 * Lisätään opintokokonaisuus taulukkoon, ottaa sen omistukseensa
	 * @param opintok opintokokonaisuus joka lisätään
	 */
	public void lisaa(Opintokokonaisuus opintok) throws ApuriException{
		if (lkm >= opintokTaulu.length){
			Opintokokonaisuus opintokTauluVali[] = new Opintokokonaisuus[lkm*2];
			
			for(int i = 0; i < lkm; i++){
				opintokTauluVali[i] = opintokTaulu[i];
			}
			
			opintokTaulu = opintokTauluVali;
		}
		opintokTaulu[lkm] = opintok;
		lkm++;
	}
	

    /**
     * Lukee opintokokonaisuudet tiedostosta.  Ei toimi vielä.
     * @param tiedNimi tiedoston nimi
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueOpintokokonaisuudetTiedostosta(String tied) throws ApuriException {
    	throw new ApuriException("Ei osata vielä lukea tiedostoa " + tied);
    }
    

    /**
     * Tallentaa opintokokonaisuudet tiedostoon.  Ei toimi vielä
     * @throws ApuriException mikäli tallennus ei onnistu
     */
    public void talletaOpintokokonaisuudetTiedostoon(String tied) throws ApuriException {
    	tiedNimi = tied;
        throw new ApuriException("Ei osata vielä tallettaa tiedostoa " + tiedNimi);
    }

	
    /**
     * Palauttaa viitteen i:teen opintokokonaisuuteen.
     * @param i indeksi monennen opintokokonaisuuden viite halutaan
     * @return viite opintokokonaisuuteen
     * @throws IndexOutOfBoundsException jos indeksi ei ole sallituissa rajoissa  
     */
    public Opintokokonaisuus getKokonaisuus(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Olematon indeksi ! : " + i);
        return opintokTaulu[i];
    }
	
    
    /**
     * Palauttaa opintokokonaisuuksien lukumäärän
     * @return opintokokonaisuuksien lukumäärän
     */
    public int getLkm() {
        return lkm;
    }
    	
	/**
	 * Palautetaan opintokokonaisuuden tunnusnro
	 * @return opintokokonaisuuden numero
	 */
	public int getTunnusNro() {
		return 0;
	}
	


	/**
	 * Päivitetään painotetun keskiarvon arvoa. Jos ei löydy opintokokonaisuuden tunnusnumerolla arvoa, heitetään ApuriException 
	 * @param opintokNro opintokokonaisuuden numero, jota päivitetään
	 * @param painotettuKA paljonko pistetään painotetun keskiarvon arvoksi
	 * 
	 */
	public void setPainotettuKA(int opintokNro, double painotettuKA) throws ApuriException{
		
		for(int i = 0; i < lkm; i++){
			if(opintokTaulu[i].getTunnusNro() == opintokNro) {
				opintokTaulu[i].setPainotettuKA(painotettuKA);
				return;
			}
		}
		
		throw new ApuriException("Ei löytynyt opintokokonaisuutta!"); 
	}


	/**
	 * Päivitetään keskiarvon arvoa. Jos ei löydy opintokokonaisuuden tunnusnumerolla arvoa, heitetään ApuriException
	 * @param opintokNro opintokokonaisuuden numero, jota päivitetään
	 * @param keskiarvo paljonko pistetään keskiarvon  arvoksi
	 */
	public void setKeskiarvo(int opintokNro, double keskiarvo) throws ApuriException {

		for(int i = 0; i < lkm; i++){
			if(opintokTaulu[i].getTunnusNro() == opintokNro) {
				opintokTaulu[i].setKeskiarvo(keskiarvo);
				return;
			}
		}
		
		throw new ApuriException("Ei löytynyt opintokokonaisuutta!"); 
	}

	
	/**
	 * Haetaan keskiarvo tietylle opintokokonaisuudelle
	 * @param opintokNro opintokokonaisuuden id, jolla haetaan
	 * @return keskiarvo
	 * @throws ApuriException mikäli id:ä ei löydy
	 */
	public double getKeskiarvo(int opintokNro) throws ApuriException {
		
		for(int i = 0; i < lkm; i++) {
			if(opintokTaulu[i].getTunnusNro() == opintokNro) {
				return opintokTaulu[i].getKeskiarvo();
			}
		}
		
		throw new ApuriException("Ei löytynyt ID:llä");
	}
	
	
	/**
	 * Haetaan painotettu tietylle opintokokonaisuudelle
	 * @param opintokNro opintokokonaisuuden id, jolla haetaan
	 * @return painotettu keskiarvo
	 * @throws ApuriException mikäli id:ä ei löydy
	 */
	public double getpainotettuKA(int opintokNro) throws ApuriException {
		
		for(int i = 0; i < lkm; i++) {
			if(opintokTaulu[i].getTunnusNro() == opintokNro) {
				return opintokTaulu[i].getPainotettuKA();
			}
		}
		
		throw new ApuriException("Ei löytynyt ID:llä´!");
	}

	

	/**
	 * Palautetaan opintokokonaisuuden nimi, joka on tunnusnumeroltaan sama kuin parametrina annettu
	 * @param opintokNro numero, jolla etsitään
	 * @return merkkijono tutkinnon nimestä
	 * @throws ApuriException 
	 */
	public String getTutkintoNimi(int opintokNro) throws ApuriException {
		
		for(int i = 0; i < lkm; i++){
			if(opintokTaulu[i].getTunnusNro() == opintokNro) {
				return opintokTaulu[i].getNimi();
			}
			}		
		throw new ApuriException("Ei löytynyt numerolla!");

	}

	/**
	 * Asetetaan tiedoston nimeksi jotain
	 * @param nimi parametrina annettu tiedostonnimi + ".dat", joka pistetään attribuutiksi
	 */
	public void setTiedNimi(String nimi) {
		this.tiedNimi = nimi + ".dat";
	}

	
	/**
	 * Testipääohjelma
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		Opintokokonaisuudet kokonaisuudet = new Opintokokonaisuudet();
		Opintokokonaisuus kokonaisuus = new Opintokokonaisuus();
		Opintokokonaisuus kokonaisuus2 = new Opintokokonaisuus();
		try {
				kokonaisuudet.lisaa(kokonaisuus);
				kokonaisuudet.lisaa(kokonaisuus2);
				kokonaisuus.rekisteroi();
				kokonaisuus2.rekisteroi();
				kokonaisuus.annaKaupKand();
					for(int i = 0; i < kokonaisuudet.getLkm(); i++) {
						kokonaisuudet.opintokTaulu[i].tulosta(System.out);
				}
		} catch (ApuriException ex) {
			System.out.println(ex.getMessage());
		}
	}





}
