/**
 * 
 */
package opintoapuri;



/**
 * @author Matias Partanen
 * @version Mar 14, 2012
 * Opintokokonaisuudet-luokka, joka l�hinn� pit�� kirjaa taulukossa yksitt�isist� opintokokonaisuuksista ja tekee niille temppuja
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
	 * Lis�t��n opintokokonaisuus taulukkoon, ottaa sen omistukseensa
	 * @param opintok opintokokonaisuus joka lis�t��n
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
     * Lukee opintokokonaisuudet tiedostosta.  Ei toimi viel�.
     * @param tiedNimi tiedoston nimi
     * @throws SailoException jos lukeminen ep�onnistuu
     */
    public void lueOpintokokonaisuudetTiedostosta(String tied) throws ApuriException {
    	throw new ApuriException("Ei osata viel� lukea tiedostoa " + tied);
    }
    

    /**
     * Tallentaa opintokokonaisuudet tiedostoon.  Ei toimi viel�
     * @throws ApuriException mik�li tallennus ei onnistu
     */
    public void talletaOpintokokonaisuudetTiedostoon(String tied) throws ApuriException {
    	tiedNimi = tied;
        throw new ApuriException("Ei osata viel� tallettaa tiedostoa " + tiedNimi);
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
     * Palauttaa opintokokonaisuuksien lukum��r�n
     * @return opintokokonaisuuksien lukum��r�n
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
	 * P�ivitet��n painotetun keskiarvon arvoa. Jos ei l�ydy opintokokonaisuuden tunnusnumerolla arvoa, heitet��n ApuriException 
	 * @param opintokNro opintokokonaisuuden numero, jota p�ivitet��n
	 * @param painotettuKA paljonko pistet��n painotetun keskiarvon arvoksi
	 * 
	 */
	public void setPainotettuKA(int opintokNro, double painotettuKA) throws ApuriException{
		
		for(int i = 0; i < lkm; i++){
			if(opintokTaulu[i].getTunnusNro() == opintokNro) {
				opintokTaulu[i].setPainotettuKA(painotettuKA);
				return;
			}
		}
		
		throw new ApuriException("Ei l�ytynyt opintokokonaisuutta!"); 
	}


	/**
	 * P�ivitet��n keskiarvon arvoa. Jos ei l�ydy opintokokonaisuuden tunnusnumerolla arvoa, heitet��n ApuriException
	 * @param opintokNro opintokokonaisuuden numero, jota p�ivitet��n
	 * @param keskiarvo paljonko pistet��n keskiarvon  arvoksi
	 */
	public void setKeskiarvo(int opintokNro, double keskiarvo) throws ApuriException {

		for(int i = 0; i < lkm; i++){
			if(opintokTaulu[i].getTunnusNro() == opintokNro) {
				opintokTaulu[i].setKeskiarvo(keskiarvo);
				return;
			}
		}
		
		throw new ApuriException("Ei l�ytynyt opintokokonaisuutta!"); 
	}

	
	/**
	 * Haetaan keskiarvo tietylle opintokokonaisuudelle
	 * @param opintokNro opintokokonaisuuden id, jolla haetaan
	 * @return keskiarvo
	 * @throws ApuriException mik�li id:� ei l�ydy
	 */
	public double getKeskiarvo(int opintokNro) throws ApuriException {
		
		for(int i = 0; i < lkm; i++) {
			if(opintokTaulu[i].getTunnusNro() == opintokNro) {
				return opintokTaulu[i].getKeskiarvo();
			}
		}
		
		throw new ApuriException("Ei l�ytynyt ID:ll�");
	}
	
	
	/**
	 * Haetaan painotettu tietylle opintokokonaisuudelle
	 * @param opintokNro opintokokonaisuuden id, jolla haetaan
	 * @return painotettu keskiarvo
	 * @throws ApuriException mik�li id:� ei l�ydy
	 */
	public double getpainotettuKA(int opintokNro) throws ApuriException {
		
		for(int i = 0; i < lkm; i++) {
			if(opintokTaulu[i].getTunnusNro() == opintokNro) {
				return opintokTaulu[i].getPainotettuKA();
			}
		}
		
		throw new ApuriException("Ei l�ytynyt ID:ll�!");
	}

	

	/**
	 * Palautetaan opintokokonaisuuden nimi, joka on tunnusnumeroltaan sama kuin parametrina annettu
	 * @param opintokNro numero, jolla etsit��n
	 * @return merkkijono tutkinnon nimest�
	 * @throws ApuriException 
	 */
	public String getTutkintoNimi(int opintokNro) throws ApuriException {
		
		for(int i = 0; i < lkm; i++){
			if(opintokTaulu[i].getTunnusNro() == opintokNro) {
				return opintokTaulu[i].getNimi();
			}
			}		
		throw new ApuriException("Ei l�ytynyt numerolla!");

	}

	/**
	 * Asetetaan tiedoston nimeksi jotain
	 * @param nimi parametrina annettu tiedostonnimi + ".dat", joka pistet��n attribuutiksi
	 */
	public void setTiedNimi(String nimi) {
		this.tiedNimi = nimi + ".dat";
	}

	
	/**
	 * Testip��ohjelma
	 * @param args ei k�yt�ss�
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
