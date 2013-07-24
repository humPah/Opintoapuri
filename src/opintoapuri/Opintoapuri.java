/**
 * 
 */
package opintoapuri;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matias Partanen
 * @version 13.03.2012
 * Opintoapuri-luokka, joka pit‰‰ yll‰ opintoapuria ja hakee tietoja yksitt‰isilt‰ tutkinnoilta sek‰ kursseilta, ja v‰litt‰‰ niit‰ eteenp‰in niin 
 * graafiselle k‰yttˆliittym‰lle kuin toisille tutkinnoille tai kursseille sek‰ s‰ilˆˆ haluttua tietoa
 */

public class Opintoapuri {

	private Kurssit kurssit = new Kurssit();
	private Opintokokonaisuudet opintokokonaisuudet = new Opintokokonaisuudet();

		/**
		 * Poistaa tutkinnon numeron perusteella. Tutkinnon kurssit poistetaan samalla
		 * @param numero tutkinnon indeksinumero, jonka perusteella poistetaan
	 	*/	
		public void poistaOpintokokonaisuus(int numero) {

		}
		
		
		/**
		 * Luetaan opintokokonaisuudet ja kurssit tiedostoista
		 * @param nimi k‰ytt‰j‰n nimi, jonka tiedot avataan
		 * @throws ApuriException jos ei onnistu
		 */
		public void lueTiedostosta(String nimi) throws ApuriException {
			opintokokonaisuudet = new Opintokokonaisuudet();
			kurssit = new Kurssit();
			
			opintokokonaisuudet.setTiedNimi(nimi);
			kurssit.setTiedNimi(nimi);			
			
			lueOpintokokonaisuusTiedostosta(nimi);
			lueKurssitTiedostosta(nimi);
		}

	 
		/**
		 * Lis‰t‰‰n uusi kurssi tietorakenteeseen, delegoidaan se eteenp‰in 
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
			kurssit.lisaa(kurssi);
		}
		
		/**
		 * Lis‰t‰‰n uusi opintokokonaisuus tietorakenteeseen
		 * @param opintok viite kokonaisuuteen, joka lis‰t‰‰n
		 */
		public void lisaa(Opintokokonaisuus opintok) throws ApuriException{
			opintokokonaisuudet.lisaa(opintok);
		}
		
		/**
		 * 
		 * @param indeksi
		 * @throws ApuriException
		 */
		public Kurssi annaKurssi(int indeksi) throws ApuriException{
			return kurssit.anna(indeksi);
		}
		
	    /**
	     * Haetaan kaikki tietyn opintokokonaisuuden kurssit 
	     * @param viite opintokokonaisuuteen, jonka kurssit haetaan
	     * @return lista viitteist‰ kyseisiin kursseihin
	     */
	    public List<Kurssi> annaKurssit(Opintokokonaisuus opintok) {
	        return kurssit.haeKurssit(opintok.getTunnusNro());
	    }

	    /**
	     * Lukee kurssien tiedot tiedostosta. Ei toimi viel‰
	     * @param nimi tiedoston nimi, jota k‰ytet‰‰n lukemisessa
	     * @throws ApuriException heitet‰‰n, jos lukeminen ei onnistu
	     */
	    public void lueKurssitTiedostosta(String nimi) throws ApuriException {
	        kurssit.lueKurssitTiedostosta(nimi);
	    }
	    
	    
	    /**
	     * Tallenetaan kurssin tiedot tiedostoon. Ei toimi viel‰.
	     * @param nimi tiedoston nimi, johon tallennetaan
	     * @throws ApuriException heitet‰‰n, jos tallentaminen ei onnistu
	     */
	    public void talletaKurssitTiedostoon(String nimi) throws ApuriException {
	    	kurssit.talletaKurssitTiedostoon(nimi);
	    }
	    
	    /**
	     * Lukee opintokokonaisuuksien tiedot tiedostosta. Ei toimi viel‰
	     * @param nimi tiedoston nimi, jota k‰ytet‰‰n lukemisessa
	     * @throws ApuriException heitet‰‰n, jos lukeminen ei onnistu
	     */
	    public void lueOpintokokonaisuusTiedostosta(String nimi) throws ApuriException {
	        opintokokonaisuudet.lueOpintokokonaisuudetTiedostosta(nimi);
	    }
	    
	    
	    /**
	     * Tallenetaan opintokokonaisuuksien tiedot tiedostoon. Ei toimi viel‰.
	     * @param nimi tiedoston nimi, johon tallennetaan
	     * @throws ApuriException heitet‰‰n, jos tallentaminen ei onnistu
	     */
	    public void talletaOpintokokonaisuusTiedostoon(String nimi) throws ApuriException {
	    	opintokokonaisuudet.talletaOpintokokonaisuudetTiedostoon(nimi);
	    }


		/**
		 * Lasketaan keskiarvo ja p‰itet‰‰n attribuutin arvo opintokokonaisuuteen
		 * @throws ApuriException virhe, jos laskeminen ei onnistu
		 */
		public void laskeKeskiarvo(int opintokNro) throws ApuriException {
			double apuArvolauseSumma = 0;
			
			if ( kurssit.getLkmTiettyKokonaisuus(opintokNro) == 0) return;
				for(int i = 0; i < kurssit.getLkm(); i++) {
						if (kurssit.getKurssiTaulu()[i].getOpintokNro() == opintokNro && kurssit.getKurssiTaulu()[i].getArvolause() != 0) {
							apuArvolauseSumma = apuArvolauseSumma + kurssit.getKurssiTaulu()[i].getArvolause();
						}
				}
			
			double keskiarvo = ( apuArvolauseSumma / kurssit.getLkmTiettyKokonaisuus(opintokNro));
			opintokokonaisuudet.setKeskiarvo(opintokNro, keskiarvo);
		}
		

		/**
		 * Lasketaan painotettu keskiarvo ja p‰itet‰‰n attribuutin arvo opintokokonaisuuteen
		 * @throws ApuriException virhe, jos laskeminen ei onnistu
		 *@example
		 * <pre name="test">
		 * #THROWS ApuriException
		 * Opintoapuri opintoapuri = new Opintoapuri();
		 * Kurssi kurssi = new Kurssi(1);
		 * Kurssi kurssi2 = new Kurssi(1);
		 * Kurssi kurssi3 = new Kurssi(1);
		 * Kurssi kurssi4 = new Kurssi(2);
		 * Opintokokonaisuus kaupKand = new Opintokokonaisuus();
		 * Opintokokonaisuus jokuToinen = new Opintokokonaisuus();
		 * kaupKand.annaKaupKand();
		 * jokuToinen.annaKaupKand();
		 * kaupKand.rekisteroi();
		 * jokuToinen.rekisteroi();
		 * kurssi.annaOhj2();
		 * kurssi2.annaOhj2();
		 * kurssi3.annaOhj2();
		 * kurssi4.annaOhj2();
		 * opintoapuri.lisaa(kaupKand);
		 * opintoapuri.lisaa(jokuToinen);
		 * opintoapuri.lisaa(kurssi);
		 * opintoapuri.lisaa(kurssi2);
		 * opintoapuri.lisaa(kurssi3);
		 * opintoapuri.lisaa(kurssi4);
		 * kurssi.setArvolause(5);
		 * kurssi.setLaajuus(4);
		 * kurssi2.setArvolause(2);
		 * kurssi2.setLaajuus(6);
		 * kurssi3.setArvolause(0);
		 * kurssi3.setLaajuus(10);
		 * #TOLERANCE="0.1";
		 * opintoapuri.laskeKeskiarvot(1);
		 * opintoapuri.getKeskiarvo(kaupKand.getTunnusNro()) ~~~ 3.5;
		 * opintoapuri.getPainotettuKA(kaupKand.getTunnusNro()) ~~~ 3.2;
		 * </pre>
		 */
		public void laskePainotettuKA(int opintokNro) throws ApuriException {
			double apuLaajuusSumma = 0;
			double apuLaajuusKertaaArvolauseSumma = 0;
			
			if ( kurssit.getLkmTiettyKokonaisuus(opintokNro) == 0 ) return;
				for(int i = 0; i < kurssit.getLkm(); i++) {
					if (kurssit.getKurssiTaulu()[i].getOpintokNro() == opintokNro && kurssit.getKurssiTaulu()[i].getArvolause() != 0) {
							apuLaajuusSumma = apuLaajuusSumma + kurssit.getKurssiTaulu()[i].getLaajuus();
							apuLaajuusKertaaArvolauseSumma = apuLaajuusKertaaArvolauseSumma + (kurssit.getKurssiTaulu()[i].getLaajuus() * kurssit.getKurssiTaulu()[i].getArvolause());
						}
				}
				
			double painotettuKA = (apuLaajuusKertaaArvolauseSumma / apuLaajuusSumma);
			opintokokonaisuudet.setPainotettuKA(opintokNro, painotettuKA);
		}
		

		
		/**
		 * Lasketaan molemmat keskiarvot ja p‰ivitet‰‰n attribuuttien arvot
		 * @param opintkNro opintokokonaisuuden numero, jonka keskiarvoja lasketaan
		 * @throws ApuriException virhe, jos laskeminen ei onnistu
		 */
		public void laskeKeskiarvot(int opintkNro) throws ApuriException {
			laskeKeskiarvo(opintkNro);
			laskePainotettuKA(opintkNro);
		}
		
		/**
		 * Haetaan kaikki tietyn tutkinnon kurssit
		 * @param tunnusNro kurssin tunnusnumero
		 * @return lista kursseista
		 */
		public List<Kurssi> haeKurssit(int opintokNro) {
			return kurssit.haeKurssit(opintokNro);
		}
		
		
		/**
		 * Haetaan keskiarvon arvo tietylle opintokokonaisuudelle
		 * @param opintokNro opintokokonaisuuden numero
		 * @return keskiarvo
		 * @throws ApuriException virhe, jos opintokNro-parametrilla ei lˆydy mit‰‰n
		 */
		public double getKeskiarvo(int opintokNro) throws ApuriException {
			return opintokokonaisuudet.getKeskiarvo(opintokNro);
		}
		
		
		/**
		 * Haetaan keskiarvon arvo tietylle opintokokonaisuudelle
		 * @param opintokNro opintokokonaisuuden numero
		 * @return keskiarvo
		 * @throws ApuriException virhe, jos opintokNro-parametrilla ei lˆydy mit‰‰n
		 */
		public double getPainotettuKA(int opintokNro) throws ApuriException {
			return opintokokonaisuudet.getpainotettuKA(opintokNro);
		}
		
		
		/**
		 * Palautetaan opintokokonaisuuden nimi
		 * @param opintokNro numero, jolla etsit‰‰n
		 * @return merkkijono tutkinnon nimest‰
		 * @throws ApuriException 
		 */
		public String getTutkintoNimi(int opintokNro) throws ApuriException {
			return opintokokonaisuudet.getTutkintoNimi(opintokNro);
		}		
		

		/**
		 * Haetaan paljon tutkintoja on olemassa
		 * @return tutkintojen lukum‰‰r‰
		 */
		public int getKokonaisuuksiaLkm() {
			return opintokokonaisuudet.getLkm();
		}


		/**
		 * Annetaan indeksi‰ vastaava opintokokonaisuus
		 * @param i indeksi, mones haetaan
		 * @return yksi opintokokonaisuus
		 */
		public Opintokokonaisuus getKokonaisuus(int i) {
			return opintokokonaisuudet.getKokonaisuus(i);
		}
		
		
		/**
		 * Testip‰‰ohjelma opintoapurille
		 * @param args ei k‰ytˆss‰
		 */
		public static void main(String args[]) {
			
			Opintoapuri opintoapuri = new Opintoapuri();
			Opintokokonaisuudet opintokk = new Opintokokonaisuudet();
			Opintokokonaisuus kaupKand = new Opintokokonaisuus();
			Opintokokonaisuus toinenKaupKand = new Opintokokonaisuus();

			kaupKand.annaKaupKand();
			toinenKaupKand.annaKaupKand();
			kaupKand.rekisteroi();
			toinenKaupKand.rekisteroi();
			
			Kurssi kurssi = new Kurssi(kaupKand.getTunnusNro());
			Kurssi kurssi2 = new Kurssi(kaupKand.getTunnusNro());
			Kurssi kurssi3 = new Kurssi(toinenKaupKand.getTunnusNro());
			Kurssi kurssi4 = new Kurssi(kaupKand.getTunnusNro());
			Kurssi kurssi5 = new Kurssi(toinenKaupKand.getTunnusNro());
			kurssi.annaOhj2();
			kurssi2.annaOhj2();
			kurssi3.annaOhj2();
			kurssi4.annaOhj2();
			kurssi5.annaOhj2();
			
			System.out.println("=========================TESTI========================");
			try {
			
			opintoapuri.lisaa(kaupKand);
			opintoapuri.lisaa(toinenKaupKand);
			
			opintoapuri.lisaa(kurssi);
			opintoapuri.lisaa(kurssi2);
			opintoapuri.lisaa(kurssi3);
			opintoapuri.lisaa(kurssi4);
			opintoapuri.lisaa(kurssi5);
			System.out.println(opintoapuri.getTutkintoNimi(kaupKand.getTunnusNro()));
						
			for(int i = 0; i < opintokk.getLkm(); i++) {
				Opintokokonaisuus kk = opintokk.getKokonaisuus(i);
				kk.tulosta(System.out);
				}
			
			List<Kurssi> kurssiListaKaupKand = new ArrayList<>();
			kurssiListaKaupKand = opintoapuri.haeKurssit(kaupKand.getTunnusNro());
			
			for(Kurssi kurs : kurssiListaKaupKand)
				kurs.tulosta(System.out);

			System.out.println();
			System.out.println("Pˆˆ!");
			System.out.println();
			System.out.println(opintoapuri.getTutkintoNimi(toinenKaupKand.getTunnusNro()));
			
			List<Kurssi> kurssiListaToinenKaupKand = new ArrayList<>();
			kurssiListaToinenKaupKand = opintoapuri.haeKurssit(toinenKaupKand.getTunnusNro());
			for(Kurssi kurs : kurssiListaToinenKaupKand)
				kurs.tulosta(System.out);
			
			System.out.println();
			
			opintoapuri.laskeKeskiarvot(kaupKand.getTunnusNro());
			opintoapuri.laskeKeskiarvot(toinenKaupKand.getTunnusNro());
			
			System.out.println(kaupKand.getTunnusNro() + ":n indeksin keskiarvo : " + opintoapuri.getKeskiarvo(kaupKand.getTunnusNro()));
			System.out.println(kaupKand.getTunnusNro() + ":n indeksin painotettu keskiarvo : " + opintoapuri.getPainotettuKA(kaupKand.getTunnusNro()));
			System.out.println();
			System.out.println(toinenKaupKand.getTunnusNro() + ":n indeksin keskiarvo : " + opintoapuri.getKeskiarvo(toinenKaupKand.getTunnusNro()));
			System.out.println(toinenKaupKand.getTunnusNro() + ":n indeksin painotettu keskiarvo : " + opintoapuri.getPainotettuKA(toinenKaupKand.getTunnusNro()));			
			
			} catch (ApuriException ex) {
				System.out.println("Virhe " + ex.getMessage());
			}
		}

}

