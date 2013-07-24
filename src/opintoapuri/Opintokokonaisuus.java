/**
 * 
 */
package opintoapuri;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

/**
 * @author Matias Partanen
 * @version 16.03.2012
 * Opintokokonaisuus-luokka, joka l‰hinn‰ pit‰‰ tietoa omasta indeksist‰‰n sek‰ nimest‰‰n
 */
public class Opintokokonaisuus {

	private String nimi;
	private int tunnusNro;
	private double keskiarvo;
	private double painotettuKA;
	Random generator = new Random();
	
	private static int seuraavaNro = 1;
	
	/**
	 * Konstruktori. toistaiseksi tyhj‰
	 */
	public Opintokokonaisuus () {
		
	}
	
    /**
     * Tulostetaan opintokokonaisuuden tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }


    /**
     * Tulostetaan opintokokonaisuuden tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
    	out.println("Tutkinto : " + nimi);
    }

    
    /**
     * Annetaan tutkinnolle testiarvo
     */
    public void annaKaupKand() {
    	int satunnainenNro = generator.nextInt(10);
    	nimi = ("Kauppatieteiden kandidaatti " + satunnainenNro);
    }
    
    

    /**
     * Antaa opintokokonaisuudelle seuraavan rekisterinumeron.
     * @return opintokokonaisuuden tunnusnumero;
     * @example
     * <pre name="test">
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }

    
    /**
     * Palautetaan opintokokonaisuuden tunnusnumero
     * @return tunnusnumero
     */
    public int getTunnusNro() {
    	return tunnusNro;
    }


	/**
	 * @param args ei k‰ytˆss‰
	 */
	public static void main(String[] args) {
		Opintokokonaisuus kokonaisuus = new Opintokokonaisuus();
		kokonaisuus.annaKaupKand();
		kokonaisuus.tulosta(System.out);
		
	}


	/**
	 * @return haetaan keskiarvon arvo
	 */
	public double getKeskiarvo() {
		return keskiarvo;
	}

	/**
	 * muutetaan keskiarvon arvoa joksikin
	 * @param arvo, miksi muutetaan
	 */
	public void setKeskiarvo(double keskiarvo) {
		this.keskiarvo = keskiarvo;
	}


	/**
	 * @return haetaan painotetun keskiarvon arvo
	 */
	public double getPainotettuKA() {
		return painotettuKA;
	}


	/**
	 * muutetaan painotetun keskiarvon arvoa joksikin
	 * @param painotettuKA arvo, miksi muutetaan
	 */
	public void setPainotettuKA(double painotettuKA) {
		this.painotettuKA = painotettuKA;
	}


	/**
	 * Palautetaan opintokokonaisuuden nimi
	 * @return opintokokonaisuuden nimi
	 */
	public String getNimi() {
		return this.nimi;
	}

	
	
	
}
