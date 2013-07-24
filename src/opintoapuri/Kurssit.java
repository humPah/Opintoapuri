/**
 * 
 */
package opintoapuri;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matias Partanen
 * @version 14.03.2012
 * Kurssit-luokka, joka hakee pit‰‰ yll‰ listaa yksitt‰isit‰ kursseista Kurssi-luokasta sek‰ tarpeen mukaan hakee niist‰ tietoa,
 *  sek‰ v‰litt‰‰ niit‰ eteenp‰in Opintoapuri-luokalle
 */
public class Kurssit {

	private static final int maxKurssiLkm = 10;
	private int lkm = 0;
	private Kurssi kurssiTaulu [] = new Kurssi[maxKurssiLkm];
	private String tiedNimi = "";
	
	
	/**
	 * Konstruktori, jolle riitt‰‰ attribuuttien arvot
	 */
	public Kurssit() {
		
	}
	
		
	/**
	 * Lukee kurssit tiedostosta. eee toimii viel‰
	 * @param tiedosto tiedosto, josta luetaan
	 * @throws ApuriException heitet‰‰n, mik‰li luku ei onnistu
	 */
	public void lueKurssitTiedostosta(String tiedosto) throws ApuriException {
        throw new ApuriException("Ei osata viel‰ lukea tiedostoa ! " + tiedNimi);
	}
	

	/**
	 * Tallennetaan kurssit tiedostoon. ei toimi viel‰
	 * @param tiedosto tiedosto, johon tallennetaan
	 * @throws ApuriException heitet‰‰n, jos tallennus ei onnistu
	 */
	public void talletaKurssitTiedostoon(String tiedosto) throws ApuriException{
        throw new ApuriException("Ei osata viel‰ tallentaa tiedostoa ! " + tiedNimi);
		
	}
	

	/**
	 * Lis‰t‰‰n uusi kurssi tietorakenteeseen. Lasketaan lopuksi keskiarvot uudelleen uusilla kurssiarvoilla. 
	 * @param kurssi Viite kurssiin, joka lis‰t‰‰n
	 *@example
	 * <pre name="test">
	 * #THROWS ApuriException
	 * Kurssit kurssit = new Kurssit();
	 * Kurssi kurssi = new Kurssi(1);
	 * Kurssi kurssi2 = new Kurssi(1);
	 * Kurssi kurssi3 = new Kurssi(1);
	 * Kurssi kurssi4 = new Kurssi(1);
	 * kurssi3.annaOhj2();
	 * kurssit.lisaa(kurssi);
	 * kurssit.lisaa(kurssi2);
	 * kurssit.lisaa(kurssi3);
	 * kurssit.lisaa(kurssi4);
	 * kurssit.anna(0) === kurssi;
	 * kurssit.anna(2) == kurssi4 === false;
	 * kurssit.getLkm() === 4;
	 * kurssit.anna(4) === kurssi #THROWS IndexOutOfBoundsException
	 * </pre>
	 */
	public void lisaa(Kurssi kurssi) throws ApuriException{
		if (lkm >= kurssiTaulu.length) throw new ApuriException("Ei mahdu en‰‰ !");
		kurssi.rekisteroiNro();
		kurssiTaulu[lkm] = kurssi;
		lkm++;
	}
	
	
    /**
     * Palauttaa viitteen i:teen kurssi.
     * @param i indeksi monennen kurssin viite halutaan
     * @return viite kurssiin
     * @throws IndexOutOfBoundsException jos indeksi ei ole sallituissa rajoissa  
     */
    public Kurssi anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Olematon indeksi ! : " + i);
        return kurssiTaulu[i];
    }
	
    
    /**
     * Palauttaa kurssien lukum‰‰r‰n
     * @return kurssien lukum‰‰r‰	
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Hakee kaikki tietylle opintokokonaisuudelle kuuluvat kurssit
     * @param opintokNro Tunnusnumero, jolla kokonaisuus tunnistetaan
     * @return lista kursseista
     */
    public List<Kurssi> haeKurssit(int opintokNro) {
    	List<Kurssi> loytyneet = new ArrayList<Kurssi>();
    	for(int i = 0; i < lkm; i++) {
    		if (kurssiTaulu[i].getOpintokNro() == opintokNro) loytyneet.add(kurssiTaulu[i]);
    	}
    		
    	return loytyneet;
    }
    
    
    /**
     * Haetaan lukum‰‰r‰ kursseilta, joiden arvolause on jotain muuta kuin 0
     * @param opintokNro Tunnusnumero, jolla kokonaisuus tunnistetaan
     * @return kuinka monta kurssia lˆytyi
     */
    public int getLkmTiettyKokonaisuus(int opintokNro) {
    	int loytyneet = 0;
    	for(int i = 0; i < lkm; i++) 
    		if ( kurssiTaulu[i].getOpintokNro() == opintokNro && kurssiTaulu[i].getArvolause() != 0 ) loytyneet++;
    	return loytyneet;    	
    }
    
    
    
    /**
     * Setteri tiedoston nimelle, asetetaan
     * @param nimi parametrina annettu tiedostonnimi + .kur, joka asetetaan attribuutiksi
     */
	public void setTiedNimi(String nimi) {
		this.tiedNimi = nimi + ".kur";
	}
	
	
    /**
     * Testiohjelma
     * @param args ei k‰ytˆss‰
     */
    public static void main(String args[]) {
    	Kurssit kurssit = new Kurssit();
    	
    	Kurssi kurssi = new Kurssi(1);
    	Kurssi kurssi2 = new Kurssi(1);
    	kurssi.rekisteroiNro();
    	kurssi2.rekisteroiNro();
    	kurssi.annaOhj2();
    	kurssi2.annaOhj2();
    	
    	try {
    		kurssit.lisaa(kurssi);
    		kurssit.lisaa(kurssi2);
    		System.out.println("Pikku testi ! :");
    		
    		for(int i = 0; i < kurssit.getLkm(); i++) {
    			kurssit.kurssiTaulu[i].tulosta(System.out);    			
    		}
    	} catch (ApuriException ex) {
    		System.out.println("Ei onnistunut ! syy :" + ex.getMessage());
    	}
    }



}
