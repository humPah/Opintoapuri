package opintoapuri;

import fi.jyu.mit.ohj2.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * Luokka p‰iv‰m‰‰r‰‰ varten
 * @author Vesa Lappalainen
 * @modified by Matias Partanen
 * @version 1.0, 07.02.2003
 * @version 1.1, 14.02.2003
 * @version 1.2, 17.02.2003
 * @version 1.3, 11.02.2008
 * @version 05.03.2012
 */
public class Pvm {

    private int pv;
    private int kk;
    private int vv;
    private int tamaPv;
    private int tamaKk;
    private int tamaVv;
    

  

    /**
     *  Muuttaa p‰iv‰m‰‰r‰n nykyp‰iv‰lle.
     *  Todo: pit‰‰ vaihtaa hakemaan p‰iv‰m‰‰r‰ oikeasti. 
     *  Mutta perinn‰ss‰k‰‰n ei nyt saa k‰ytt‰‰ enemp‰‰ attribuutteja kuin t‰ss‰ on
     */
    public void paivays() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        String paivays = dateFormat.format(calendar.getTime());
        StringBuilder paivaysBuilder = new StringBuilder(paivays);
        tamaPv = Integer.parseInt(Mjonot.erota(paivaysBuilder, ' ', false));
        tamaKk = Integer.parseInt(Mjonot.erota(paivaysBuilder, ' ', false));
        tamaVv = Integer.parseInt(paivaysBuilder.toString());
    }


    /**
     * Alustetaan p‰iv‰m‰‰r‰. Otetaan vanhat p‰iv‰m‰‰r‰t talteen, ja asetetaan uudet annetut p‰iv‰m‰‰r‰t paikoilleen. 
     * T‰m‰n j‰lkeen katsotaan onko vuosi karkausvuosi, jotta voidaan verrata kuukauden p‰iv‰m‰‰ri‰ sopivatko ne kyseisiin kuukausiin.
     * Kuukauden arvot voivat olla v‰lill‰ 1-12
     * Kuukauden p‰iv‰t voivat olla v‰lilt‰ 1-31 kuukausille 1, 3, 5, 7, 8, 10 ja 12
     * Kuukauden p‰iv‰t voivat olla v‰lilt‰ 1-30 kuukausille 4, 6, 9, 11
     * Kuukauden p‰iv‰t voivat olla joko 1-28 tai 1-29 kuukaudelle 2, riippuen onko vuosi karkausvuosi
     * Vuosi voi olla teoriassa mik‰ tahansa nollan yli oleva luku
     * Mik‰li olisi saamassa v‰‰r‰n arvon, otetaan t‰m‰n p‰iv‰n arvo ja pistet‰‰n tilalle
     * @param pv p‰iv‰n alustus
     * @param kk kuukauden alustus
     * @param vv vuoden alustus
     *@example
     * <pre name="test">
     * Pvm pvm1 = new Pvm(19, 2, 2012);
     * pvm1.toString() === "19.2.2012";
     * 
     * Pvm pvm2 = new Pvm(28, 2, 2012);
     * pvm2.toString() === "28.2.2012";
     * 
     * Pvm pvm3 = new Pvm(19, 4, 2012);	
     * pvm3.toString() === "19.4.2012";
     * 
     * Pvm pvm4 = new Pvm(29, 2, 2013);
     * pvm4.toString() === "19.2.2013";
     * 
     * Pvm pvm5 = new Pvm(123, 209324, 39413);
     * pvm5.toString() === "19.2.39413";
     * 
     * </pre>
     */
    public void alusta(int pv, int kk, int vv) {
    	    	
    	this.vv = vv;
    	this.kk = kk;
    	this.pv = pv;
    	
    	int[] kuukaudet;
    	
    	if((vv % 4 == 0) && (vv % 400 != 0))
    	{
    		kuukaudet = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    	}	
    	else
    	{
    		if(vv % 4 == 0)
    		{
    			kuukaudet = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    		}
    		else
    		{
    			kuukaudet = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    		}
    	}
    	
    	if(kk <= 0 || kk > 12) this.kk = tamaKk;
    	if(pv <= 0 || pv > kuukaudet[(this.getKk())-1]) this.pv = tamaPv;
      	if(vv <= 0) this.vv = tamaVv;
    }

    

    /** Alustetaan kaikki attribuutit oletusarvoon */
    public Pvm() {
    	paivays();
    	this.pv = tamaPv;
    	this.kk = tamaKk;
    	this.vv = tamaVv;
    	alusta(this.pv, this.kk, this.vv);
    }


    /** 
     * Alustetaan kuukausi ja vuosi oletusarvoon
     * @param pv p‰iv‰n alustusarvo
     */
    public Pvm(int pv) {
    	paivays();
        this.pv = pv;
        this.kk = tamaKk;
        this.vv = tamaVv;
    	alusta(this.pv, this.kk, this.vv);
    }


    /**
     * Alustetaan vuosi oletusarvoon
     * @param pv p‰iv‰n alustusarvo
     * @param kk kuukauden oletusarvo
     */
    public Pvm(int pv, int kk) {
    	paivays();
    	this.pv = pv;
    	this.kk = kk;
    	this.vv = tamaVv;
    	alusta(this.pv, this.kk, this.vv);
    }


    /**
     * Alustetaan vuosi oletusarvoon
     * @param pv p‰iv‰n alustusarvo
     * @param kk kuukauden oletusarvo
     * @param vv vuoden alustusarvo
     */
    public Pvm(int pv, int kk, int vv) {
        paivays();
        this.pv = pv;
        this.kk = kk;
        this.vv = vv;
        alusta(this.pv, this.kk, this.vv);
    } // NOPMD


    /**
     * Alustetaan p‰iv‰m‰‰r‰ merkkijonosta
     * @param s muotoa 12.3.2008 oleva merkkijono
     */
    public Pvm(String s) {
        paivays();
        pvmParse(s);
    } // NOPMD 


    /**
     * P‰iv‰m‰‰r‰ merkkijonona
     * @return p‰iv‰m‰‰r‰ muodossa 17.2.2007
     * @example
     * <pre name="test">
     *   Pvm tammi2011 = new Pvm(1,1,2011);
     *   tammi2011.toString() === "1.1.2011"
     *   Pvm helmi2011 = new Pvm(1,2,2011);
     *   helmi2011.toString() === "1.2.2011"
     *   Pvm tanaan = new Pvm(14,2,2011);
     *   tanaan.toString()    === "14.2.2011"
     *   Pvm maalis97 = new Pvm(1,3,1997);
     *   maalis97.toString()  === "1.3.1997"
     * </pre>
     */
    @Override
    public String toString() {
        return pv + "." + kk + "." + vv;
    }


    /**
     * Ottaa p‰iv‰m‰‰r‰n tiedot merkkijonosta joka on muotoa 17.2.2007
     * Jos joku osa puuttuu, sille k‰ytet‰‰n t‰m‰n p‰iv‰n arvoa oletuksena.
     * @param sb tutkittava merkkijono
     */
    protected final void pvmParse(StringBuilder sb) {
        int p = Mjonot.erota(sb, '.', 0);
        int k = Mjonot.erota(sb, '.', 0);
        int v = Mjonot.erota(sb, ' ', 0);
        alusta(p, k, v);
        // tai alusta(Mjonot.erota(sb,'.',0),Mjonot.erota(sb,'.',0),Mjonot.erota(sb,'.',0));
    }

    /**
     * Ottaa p‰iv‰m‰‰r‰n tiedot merkkijonosta joka on muotoa 17.2.2007
     * Jos joku osa puuttuu, sille k‰ytet‰‰n t‰m‰n p‰iv‰n arvoa oletuksena.
     * @param s tutkittava merkkijono
     */
    protected final void pvmParse(String s) {
        pvmParse(new StringBuilder(s));
    }


    /**
     * Ottaa p‰iv‰m‰‰r‰n tiedot merkkijonosta joka on muotoa 17.2.2007
     * Jos joku osa puuttuu, sille k‰ytet‰‰n t‰m‰n p‰iv‰n arvoa oletuksena.
     * @param s tutkittava merkkijono
     * 
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm(11,3,2003);
     * pvm.parse("12");           pvm.toString() === "12.3.2003";
     * pvm.parse("..2001");       pvm.toString() === "12.3.2001";
     * pvm.parse("..2009 14:30"); pvm.toString() === "12.3.2009"; 
     * </pre>
     */
    public void parse(String s) {
        pvmParse(s);
    }


    /**
     * Ottaa p‰iv‰m‰‰r‰n tiedot merkkijonosta joka on muotoa 17.2.2007
     * Jos joku osa puuttuu, sille k‰ytet‰‰n t‰m‰n p‰iv‰n arvoa oletuksena.
     * Merkkijonosta otetaan pois vain se osa, jota tarvitaan.
     * @param sb tutkittava merkkijono
     * 
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm(11,3,2003);
     * StringBuilder jono = new StringBuilder("12");
     * pvm.parse(jono); pvm.toString() === "12.3.2003"; jono.toString() === "";
     * jono = new StringBuilder("..2001");
     * pvm.parse(jono); pvm.toString() === "12.3.2001"; jono.toString() === "";
     * jono = new StringBuilder("..2009 14:30");
     * pvm.parse(jono); pvm.toString() === "12.3.2009"; jono.toString() === "14:30";
     * </pre>
     */
    public void parse(StringBuilder sb) {
        pvmParse(sb);
    }


    // Lis‰tty saantimetodit:

    /**
     * @return p‰iv‰n arvo
     * @example
     * <pre name="test">
     *   Pvm pv = new Pvm("14.2.2011");
     *   pv.getPv() === 14;
     * </pre>
     */
    public int getPv() {
        return pv;
    }


    /**
     * @return kuukauden arvo
     * @example
     * <pre name="test">
     *   Pvm pv = new Pvm("14.2.2011");
     *   pv.getKk() === 2;
     * </pre>
     */
    public int getKk() {
        return kk;
    }


    /**
     * @return vuoden arvo
     * @example
     * <pre name="test">
     *   Pvm pv = new Pvm("14.2.2011");
     *   pv.getVv() === 2011;
     * </pre>
     */
    public int getVv() {
        return vv;
    }
    
    
    /**
     * Ohjelma vertaa kahta p‰iv‰m‰‰r‰-oliota. Palauttaa 1, mik‰li ensimm‰inen parametri pv1 on ennen pv2.
     * Mik‰li p‰iv‰m‰‰r‰t ovat samoja palautetaan 0
     * Mik‰li toinen parametri pv2 on ennen pv1:‰, palautetaan -1
     * @param pv1 Ensimm‰inen p‰iv‰m‰‰r‰, jota tutkitaan
     * @param pv2 Toinen p‰iv‰m‰‰r‰, jota tutkitaan
     * @return -1, 0 tai 1 riippuen vertailun tuloksesta
     *@example
     * <pre name="test">
     * Pvm pvm1 = new Pvm(19, 2, 2012);
     * Pvm pvm2 = new Pvm(1, 2, 2012);
     * Pvm pvm3 = new Pvm(1, 2, 2013);
     * Pvm pvm4 = new Pvm(20, 12, 2014);
     * Pvm pvm5 = new Pvm(20, 12, 2014);
     * compareTo(pvm1, pvm2) === -1;
     * compareTo(pvm2, pvm3) === 1;
     * compareTo(pvm1, pvm3) === 1;
     * compareTo(pvm1, pvm4) === 1;
     * compareTo(pvm4, pvm1) === -1;
     * compareTo(pvm5, pvm4) === 0;
     * </pre>
     */
    public static int compareTo(Pvm pv1, Pvm pv2)
    {
       if (pv1.getVv() == pv2.getVv() && pv1.getKk() == pv2.getKk() && pv1.getPv() == pv2.getPv()) return 0;
       
       if (pv1.getVv() < pv2.getVv()) return 1;
       else if (pv1.getKk() < pv2.getKk()) return 1;
       else if (pv1.getPv() < pv2.getPv()) return 1;
       
       return -1;
    }

    
    /**
     * Verrataan olion metodilla p‰iv‰m‰‰r‰‰ parametrina annettavaan toiseen p‰iv‰m‰‰r‰‰n.
     * Mik‰li olion p‰iv‰m‰‰r‰ on ennen parametrina annetun olion p‰iv‰m‰‰r‰‰, palautetaan 1
     * Mik‰li p‰iv‰m‰‰r‰t ovat samoja, palautetaan 0
     * Mik‰li parametrina annetun olion Pvm on ennen metodia kutsuvaa, palautetaan -1
     * @param vrtPvm p‰iv‰m‰‰r‰ johon verrataan
     * @return -1, 0 tai 1 riippuen tuloksesta
     *@example
     * <pre name="test">
     * Pvm pvm1 = new Pvm(19, 2, 2012);
     * Pvm pvm2 = new Pvm(1, 2, 2012);
     * Pvm pvm3 = new Pvm(1, 2, 2013);
     * Pvm pvm4 = new Pvm(20, 12, 2014);
     * Pvm pvm5 = new Pvm(20, 12, 2014);
     * pvm1.compareTo(pvm2) === -1;
     * pvm2.compareTo(pvm3) === 1;
     * pvm1.compareTo(pvm3) === 1;
     * pvm1.compareTo(pvm4) === 1;
     * pvm4.compareTo(pvm1) === -1;
     * pvm5.compareTo(pvm4) === 0;
     * </pre>
     */
    public int compareTo(Pvm vrtPvm) {
        if (this.getVv() == vrtPvm.getVv() && this.getKk() == vrtPvm.getKk() && this.getPv() == vrtPvm.getPv()) return 0;
        
        if (this.getVv() < vrtPvm.getVv()) return 1;
        else if (this.getKk() < vrtPvm.getKk()) return 1;
        else if (this.getPv() < vrtPvm.getPv()) return 1;
        
        return -1;
    }
    
    
    /**
     * Testataan p‰iv‰m‰‰r‰-luokkaa
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Pvm pvm = new Pvm();
        Pvm pvm2 = new Pvm();
        pvm.toString();
        pvm2.alusta(19,2,2012);

        pvm.parse("12.1.1995");
        System.out.println(pvm);
        pvm.parse("15.3");
        System.out.println(pvm);
        pvm.parse("14");
        System.out.println(pvm.getPv());

        Pvm tammi2005 = new Pvm(1,1,2005);		// =>  1.1.2005 
        Pvm tammi2012 = new Pvm(1,1); // =>  1.1.2012
        Pvm maalis2012 = new Pvm(1);  // =>  1.3.2012 
        Pvm tanaan = new Pvm();       // =>  5.3.2011
        System.out.println(tammi2005.toString());
        System.out.println(tammi2012.toString());
        System.out.println(maalis2012.toString());
        System.out.println(tanaan.toString());
    }
}
