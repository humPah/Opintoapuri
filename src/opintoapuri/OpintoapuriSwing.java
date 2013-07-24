package opintoapuri;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fi.jyu.mit.gui.AbstractChooser;
import fi.jyu.mit.gui.EditPanel;

import fi.jyu.mit.gui.StringTable;
import fi.jyu.mit.gui.TextAreaOutputStream;


/**
 * 
 * @author Matias Partanen
 * @version 20.03.2012
 * Opintoapurin toiminnat, jotka ovat graafisesta k‰yttˆliittym‰st‰ erill‰‰n erillisess‰
 * tiedostossa
 */
public class OpintoapuriSwing {

	private AbstractChooser<Opintokokonaisuus> listTutkinnot;
	private StringTable tableKurssiTaulu;
	private JTextField txtKeskiarvo;
	private JTextField txtPainotettuKeskiarvo;
	private EditPanel editPanelHakuKentta;
	private JLabel lblTutkinnonNimi;
	private Opintoapuri opintoapuri;
	private JTextArea areaKurssit = new JTextArea(); //
	private Opintokokonaisuus opintokKohdalla;
	private int opintokLkm = 0;
	
	
	/**
	 * Luodaan tulevaisuudessa jotain k‰yttˆ‰ varten
	 */
	public OpintoapuriSwing() {	
		opintoapuri = new Opintoapuri();
	}
		
	
	/**
	 * @return Tutkinnon nimi, joka on t‰ll‰ hetkell‰ auki
	 */
	public JLabel getLblTutkinnonNimi() {
		return lblTutkinnonNimi;
	}


	/**
	 * @param lblTutkinnonNimi Tutkinnon nimi, joka on t‰ll‰ hetkell‰ auki
	 */
	public void setLblTutkinnonNimi(JLabel lblTutkinnonNimi) {
		this.lblTutkinnonNimi = lblTutkinnonNimi;
	}


	/**
	 * @return Hakukent‰n editpanel
	 */
	public EditPanel getEditPanelHakuKentta() {
		return editPanelHakuKentta;
	}

	
	/**
	 * @param editPanelHakuKentta Hakukent‰n editapanel, to set
	 */
	public void setEditPanelHakuKentta(EditPanel editPanelHakuKentta) {
		this.editPanelHakuKentta = editPanelHakuKentta;
	}

	
	/**
	 * @return tekstikentt‰ keskiarvolle
	 */
	public JTextField getTxtKeskiarvo() {
		return txtKeskiarvo;
	}
	

	/**
	 * @param txtKeskiarvo tekstikentt‰ keskiarvolle, to set
	 */
	public void setTxtKeskiarvo(JTextField txtKeskiarvo) {
		this.txtKeskiarvo = txtKeskiarvo;
	}
	

	/**
	 * @return tekstikentt‰ painotetulle keskiarvolle
	 */
	public JTextField getTxtPainotettuKeskiarvo() {
		return txtPainotettuKeskiarvo;
	}
	
	
	/**
	 * @param txtPainotettuKeskiarvo tekstikentt‰ painotetulle keskiarvolle, to set
	 */
	public void setTxtPainotettuKeskiarvo(JTextField txtPainotettuKeskiarvo) {
		this.txtPainotettuKeskiarvo = txtPainotettuKeskiarvo;
	}

	
	/**
	 * @return Kurssitaulu, johon kursseja listataan
	 */
	public StringTable getTableKurssiTaulu() {
		return tableKurssiTaulu;
	}
	

	/**
	 * @param tableKurssiTaulu Kurssitaulu, johon kursseja listataan, to set
	 */
	public void setTableKurssiTaulu(StringTable tableKurssiTaulu) {
		this.tableKurssiTaulu = tableKurssiTaulu;
	}
	

	/**
	 * @return Lista tutkinnoista, joita k‰ytt‰j‰ll‰ on
	 */
	public AbstractChooser<Opintokokonaisuus> getListTutkinnot() {
		return listTutkinnot;
	}
	
	
	/**
	 * @param listTutkinnot Lista tutkinnoista, joita k‰ytt‰j‰ll‰ on, to set
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setListTutkinnot(AbstractChooser listTutkinnot) {
		this.listTutkinnot = listTutkinnot;
	}
	
	/**
	 * Haetaan kursseja hakulaatikkoon kirjoitetun hakuehdon perusteella
	 */
	public void hae() {
	   JOptionPane.showMessageDialog(null, "Viel‰ ei osata hakea kursseja ;[");
	   	
	}

	
	/**
	 * Kysyt‰‰n k‰ytt‰j‰lt‰ mink‰ nimisen opintokokonaisuuden h‰n haluaa luoda, ja 
	 * luodaan sen niminen uusi kokonaisuus. Toistaiseksi luodaan oletus
	 */
	public void lisaaOpintokokonaisuus() {
		Opintokokonaisuus opintok = new Opintokokonaisuus();
		opintok.annaKaupKand(); //antaa oletuskokonaisuuden
		opintok.rekisteroi();
		try {
			opintoapuri.lisaa(opintok);
			listTutkinnot.add(opintok.getNimi(), opintok);
			opintokKohdalla = opintok;
			paivitaKeskiarvot();
			opintokLkm++;
			listTutkinnot.setSelectedIndex(opintokLkm-1);
		} catch (ApuriException e) {
			JOptionPane.showMessageDialog(null, "Ei voi lis‰t‰ ! " + e.getMessage());
		}
	}

	
	/**
	 * haetaan tiedot auki olevasta opintokokonaisuudesta ja tulostetaan tiedot tekstialueeseen
	 * 
	 */
	public void haeTiedot() {
		areaKurssit.setText("");
		if(listTutkinnot.getSelectedObject() == null) return;
		opintokKohdalla = listTutkinnot.getSelectedObject();
		lblTutkinnonNimi.setText(opintokKohdalla.getNimi());
		paivitaKeskiarvot();
		PrintStream os = TextAreaOutputStream.getTextPrintStream(areaKurssit);
		tulosta(os, opintokKohdalla);		
	}
	

	/**
	 * Kysyt‰‰n k‰ytt‰j‰lt‰ mink‰ kurssin haluaa syˆtt‰‰. Toistaiseksi lis‰t‰‰n oletus.
	 */
	public void lisaaKurssi() { 
		if (opintokKohdalla == null) {
			   JOptionPane.showMessageDialog(null, "Ei opintokokonaisuutta olemassa!");
			   return;
		}
		
		Kurssi kurssi = new Kurssi(opintokKohdalla.getTunnusNro());
		kurssi.annaOhj2(); // antaa oletuskurssin
		kurssi.rekisteroiNro();
		try {
			opintoapuri.lisaa(kurssi);
			paivitaKeskiarvot();
			PrintStream os = TextAreaOutputStream.getTextPrintStream(areaKurssit);
			tulosta(os, kurssi);
			paivitaKeskiarvot();
		} catch (ApuriException e) {
			JOptionPane.showMessageDialog(null, "Ei voi lis‰t‰ ! " + e.getMessage());
		} 
	}	
	
	
	/**
	 * P‰ivitet‰‰n kenttiin auki olevan opintokokonaisuuden keskiarvot
	 */
	private void paivitaKeskiarvot() {
		try{
			opintoapuri.laskeKeskiarvot(opintokKohdalla.getTunnusNro());
		} catch ( ApuriException ex ){
			JOptionPane.showMessageDialog(null, "Ei voi p‰ivitt‰‰ ! " + ex.getMessage());
		}
	
		double keskiarvo = opintokKohdalla.getKeskiarvo();
		double painotettuKA = opintokKohdalla.getPainotettuKA();
		
		txtKeskiarvo.setText(String.format("%.2f ", keskiarvo));
		txtPainotettuKeskiarvo.setText(String.format("%.2f ", painotettuKA));
	}
	
	
    /**
     * Tulostetaan kurssin tiedot
     * @param os Tietovirta mihin tulostetaan
     * @param kurssi kurssi, jonka tiedot tulostetaan
     */
	private void tulosta(PrintStream os, Kurssi kurssi) {
		os.println("---------------------");
		kurssi.tulosta(os);
		os.println("---------------------");
	}


	/**
	 * Kysyt‰‰n k‰ytt‰j‰lt‰ haluaako h‰n varmasti poistaa auki olevan opintokokonaisuuden,
	 * ja mik‰li kyll‰, poistetaan se
	 * 
	 */
	public void poistaOpintokokonaisuus() {
		JOptionPane.showMessageDialog(null, "Viel‰ ei osata poistakaan opintokokonaisuuksia ! hups"); 
	}
	
	
	
	/**
	 * Annetaan ikkuna, jossa k‰ytt‰j‰ voi muokata valitun kurssin tietoja
	 */
	public void muokkaaKurssinTietoja() {
		JOptionPane.showMessageDialog(null, "Viel‰ ei osata muokata opintokokonaisuuksia ! hupsishei"); 
	}	
	
	
	/**
	 * Kysyt‰‰n k‰ytt‰j‰lt‰ haluaako h‰n varmasti poistaa kurssin, ja mik‰li
	 * kyll‰, poistetaan valittu kurssi auki olevasta tutkinnosta
	 */
	public void poistaKurssi() {
		JOptionPane.showMessageDialog(null, "No ei onnistu poistaminenkaan ;'("); 
	}

	
	/**
	 * Tallennetaan auki oleva tutkinto talteen
	 */
	public void talletaTutkinto() {
		JOptionPane.showMessageDialog(null, "Ei tallennu ei!");
	}
	

	/**
	 * Talletaan tallettamattomat tiedot ja lopetetaan ohjelman suoritus
	 */
	public void lopetaOhjelma() {
		talletaTutkinto();
		System.exit(0);
	}
	
	
	/**
	 * Avaitaan k‰ytt‰j‰lle selain, jossa avustus k‰yttˆˆn. Ei olemassa viel‰
	 */
	public void naytaApua() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("http://users.jyu.fi/~maemanpa/twitter.html");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }	
    }
	
	
	/**
	 * N‰ytet‰‰n k‰ytt‰j‰lle ikkuna, jossa on yleisi‰ tietoja ohjelmasta, tekovuodesta jne
	 */
	public void naytaTietoja() {
		JOptionPane.showMessageDialog(null, "Paraikaa tekeill‰ Ohjelmointi 2-kurssin harjoitustyˆn‰. Ei toimi, mutta en usko, ett‰ uskoit toimivankaan viel‰ :]");
	}
 

    /**
     * tulostetaan opintokokonaisuuden tiedot textareaan
     * @param os tekstiarea, johon pistet‰‰n
     * @param opintokKohdalla opintokokonaisuudet, joiden tiedot(kurssit) halutaan lis‰t‰
     */
    private void tulosta(PrintStream os, Opintokokonaisuus opintokKohdalla) {
    	os.println("************************");
    	os.println(opintokKohdalla.getNimi());
    	os.println("************************");
    	List<Kurssi> kurssit = opintoapuri.annaKurssit(opintokKohdalla);
    	for(int i = 0; i < kurssit.size(); i++) {
        	 os.println("-----------------------");
    		 kurssit.get(i).tulosta(os);
    	}
    }

	/**
	 * Kysyt‰‰n k‰ytt‰j‰lt‰ haluaako h‰n poistaa k‰ytt‰j‰n ja mik‰li h‰n haluaa, poistetaan k‰ytt‰j‰ ja 
	 * palataan alkumenuun
	 */
	public void poistaKayttaja() {
		JOptionPane.showMessageDialog(null, "Ei voi poistaa k‰ytt‰j‰‰ toistaiseksi !");		
	}

	
	
	/**
	 * Lukee opintoapurin tiedot tiedostosta. Tarkistetaan alkuun, ett‰ kaikki kent‰t on alustettu paikalleen
	 * 
	 * @param nimi tiedoston nimi
	 * @return null, mik‰li luku onnistuu, muuten virheilmoitus
	 */
	public String lueTiedosto(String nimi) {
		try {
			if( listTutkinnot == null) return "listTutkinnot alustamatta !";
			if( tableKurssiTaulu == null) return "tableKurssitaulu alustamatta !";
			if( txtKeskiarvo == null) return "txtKeskiarvo alustamatta !";
			if( txtPainotettuKeskiarvo == null) return "txtPainotettuKeskiarvo alustamatta !";
			if( editPanelHakuKentta == null) return "editPanelHakuKentta alustamatta !";
			if( lblTutkinnonNimi == null) return "lblTutkinnonNimi alustamatta !";
			alustaApuri();
			opintoapuri.lueTiedostosta(nimi);
			listTutkinnot.setSelectedIndex(0);
			return null;
		} catch (ApuriException ex) {
			return ex.getMessage();
		}
	}


	/**
	 * Alustetaan valitut alueet
	 */
	private void alustaApuri() {
		luoNaytto();
	}


	/**
	 * Poistaa kurssien paikan ja tekee tilalle textarean
	 */
	private void luoNaytto() {
		tableKurssiTaulu.removeAll();
		listTutkinnot.clear();
		lblTutkinnonNimi.setText(" ");
		txtKeskiarvo.setText(" ");
		txtPainotettuKeskiarvo.setText(" ");
		tableKurssiTaulu.add(areaKurssit);
	}
	 
}
