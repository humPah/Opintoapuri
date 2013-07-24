/**
 * 
 */
package opintoapuri;
import java.io.*;
import java.util.Random;
/**
 * @author Matias Partanen
 * @version 14.03.2012
 * Yksinkertainen kurssi-luokka, joka osaa antaa itsest‰‰n tietoja eteenp‰in Kurssit-luokalle ja tarpeen mukaan p‰ivitell‰ niit‰
 */
public class Kurssi {
	private int tunnusNro = 0;
	private static int seuraavaNro = 1;
	private int opintokNro;
	private String nimi = "";
	private int laajuus = 0;
	private Pvm suoritusPvm = new Pvm();
	private int arvolause = 0;
	private String koodi = "";
    Random generator = new Random();
	
	/**
	 * Konstruktori kurssille, alustetaan arvot 
	 */
	public Kurssi(int opintokNro) {	
		this.nimi = "";
		this.laajuus = 0;
		this.suoritusPvm = new Pvm(); // t‰m‰n p‰iv‰n p‰iv‰
		this.arvolause = 0;
		this.koodi = "";
		this.opintokNro = opintokNro;
	}
	
	
	/**
	 * Palauttaa yhden testikurssin eli Ohj2 t‰ss‰ tapauksessa
	 */
	public void annaOhj2() {
		nimi = "Ohjelmointi 2";
		laajuus = generator.nextInt(7) + 1;
		suoritusPvm = new Pvm(30, 4, 2012);
		arvolause = generator.nextInt(4) + 	1;
		koodi = "TIEP111";
	}
	
	
	/**
	 * Hakee seuraavan numeron ja antaa sen
	 * @return olion tunnusnro
	 */
	public int rekisteroiNro() {
		tunnusNro = seuraavaNro;
		seuraavaNro++;
		return tunnusNro;
	}
	


    /**
     * Tulostetaan kurssin tiedot tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
    	out.println("Kurssi : " + nimi);
    	out.println("Kurssin laajuus : " + laajuus);
    	out.println("Kurssin suoritusp‰iv‰m‰‰r‰: " + suoritusPvm.toString());
    	out.println("Kurssin arvolause : " + arvolause);
    	out.println("Kurssin kurssikoodi : " + koodi);
    	out.println("Opintokokonaisuuden indeksi : " + opintokNro);
    }

    
    /**
     * Palauttaa mihin opintokokonaisuuteen kurssi kuuluu
     * @return indeksi, mihin kokonaisuuteen kurssi kuuluu
     */
    public int getOpintokNro(){
    	return opintokNro;
    }

    /**
     * Tulostetaan kurssin tiedot tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

	

    /**
     * Palauttaa kurssin tunnusnumeron.
     * @return kurssin tunnusnumero
     *@example
     * <pre name="test">
     * Kurssi kurssi = new Kurssi(1);
     * Kurssi kurssi2 = new Kurssi(1);
     * kurssi.getTunnusNro() === 0;
     * kurssi.rekisteroiNro();
     * kurssi2.rekisteroiNro();
     * kurssi.getTunnusNro() === 1;
     * kurssi2.getTunnusNro() === 2;
     * </pre>
     */
    public int getTunnusNro() {
        return tunnusNro;	
    }

    
    /**
     * Palauttaa kurssin laajuudeen.
     * @return kurssin laajuus
     */
    public int getLaajuus() {
    	return laajuus;
    }
    
    
    /**
     * Palauttaa arvolauseen arvon 
     * @return arvolauseen arvo
     */
    public int getArvolause() {
    	return arvolause;
    }
    
    
    /**
     * Laittaa arvolauseen arvoksi jotain 
     * @param arvolause paljon pistet‰‰n numeroksi
     */
    public void setArvolause(int arvolause) {
    	this.arvolause = arvolause;
    }
    
    
    /**
     * Laittaa laajuuden arvoksi jotain 
     * @param laajuus paljon pistet‰‰n numeroksi
     */
    public void setLaajuus(int laajuus) {
    	this.laajuus = laajuus;
    }
      
    
    /**®
     * Pieni testip‰‰ohjelma
     * @param args ei k‰ytˆss‰
     */
    public static void main(String args[]){
    	Kurssi kurssi = new Kurssi(1);
    	kurssi.annaOhj2();
    	kurssi.tulosta(System.out);
    }
    
}
