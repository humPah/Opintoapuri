package opintoapuri;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;

import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import fi.jyu.mit.gui.ListChooser;
import fi.jyu.mit.gui.StringTable;
import java.awt.Dimension;
import fi.jyu.mit.gui.EditPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import fi.jyu.mit.gui.SelectionChangeListener;
import fi.jyu.mit.gui.IStringListChooser;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 * @author Matias Partanen
 * @version 20.03.2012
 * Opintoapurin k‰yttˆliittym‰ Window-builderilla tehtyn‰. Pit‰‰ sis‰ll‰‰n pelk‰n GUI:n,
 * toiminnallinen toteutus on p‰‰asiallisesti OpintoapuriSwing.java-tiedostossa.
 *
 */
public class OpintoapuriGUI extends JFrame {

	/**
	 * 
	 */
	
	private String henkNimi = "matias";
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel alaPaneeli = new JPanel();
	private final JPanel alaOikeaPaneeli = new JPanel();
	private final JLabel lblKeskiarvo = new JLabel("Keskiarvo");
	private final JLabel lblPainotettuKeskiarvo = new JLabel("Painotettu keskiarvo");
	private final JTextField txtPainotettuKeskiarvo = new JTextField();
	private final JTextField txtKeskiarvo = new JTextField();
	private final JMenuBar menuPaneeli = new JMenuBar();
	private final JSplitPane vasenHaku = new JSplitPane();
	private final JSplitPane splitPanelTutkintoTiedot = new JSplitPane();
	private final JPanel panelTutkinnotNimet = new JPanel();
	private final JLabel lblTutkinnot = new JLabel("Opintokokonaisuus");
	private final JSplitPane splitPanelYlaTiedot = new JSplitPane();
	private final JPanel panelTutkintoHaku = new JPanel();
	private final JPanel panelOikeaYla = new JPanel();
	private final JScrollPane scrollPanelKurssiTiedot = new JScrollPane();
	private final JPanel panelTutkintoLista = new JPanel();
	private final ListChooser listTutkinnot = new ListChooser();
	private final StringTable tableKurssiTaulu = new StringTable();
	private final EditPanel editPanelHakuKentta = new EditPanel();
	private final JLabel lblTutkinnonNimi = new JLabel("Kauppatieteiden kandidaatti");
	/**
	 * @wbp.nonvisual location=59,169
	 */
	private final OpintoapuriSwing opintoapuriSwing = new OpintoapuriSwing();
	private final JMenu mnTiedosto = new JMenu("Tiedosto");
	private final JMenu mnMuokkaa =  new JMenu("Muokkaa");
	private final JMenu mnApua = new JMenu("Apua");
	private final JMenuItem mntmVaihdaKayttaja = new JMenuItem("Vaihda k\u00E4ytt\u00E4j\u00E4");
	private final JMenuItem mntmTallenna = new JMenuItem("Tallenna");
	private final JMenuItem mntmLopeta = new JMenuItem("Lopeta");
	private final JMenuItem mntmLisOpintokokonaisuus = new JMenuItem("Lis\u00E4\u00E4 opintokokonaisuus");
	private final JMenuItem mntmPoistaOpintokokonaisuus = new JMenuItem("Poista opintokokonaisuus");
	private final JMenuItem mntmLisKurssi = new JMenuItem("Lis\u00E4\u00E4 kurssi");
	private final JMenuItem mntmPoistaKurssi = new JMenuItem("Poista kurssi");
	private final JMenuItem mntmApua = new JMenuItem("Apua");
	private final JMenuItem mntmTietoja = new JMenuItem("Tietoja..");
	private final JMenuItem mntmMuokkaaKurssia = new JMenuItem("Muokkaa kurssia");
	private final JMenuItem mntmPoistaKayttaja = new JMenuItem("Poista k\u00E4ytt\u00E4j\u00E4");

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpintoapuriGUI frame = new OpintoapuriGUI();
					frame.setVisible(true);
                    if (args.length == 0) {
                        if (!frame.avaa())
                            System.exit(0);
                    } else
                        frame.lueTiedosto(args[0]);
                    	frame.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}

    /**
     * Tallettaa vanhat tiedot ja kysyy uuden nimen kerholla 
     * ja avaa t‰m‰n tiedoston.
     * @return false jos nime‰ ei anneta
     */
    protected boolean avaa() {
        // opintoapuriSwing.talleta();
        String uusinimi = OpintoapuriAlkuMenu.askName();
        if (uusinimi == null)
            return false;
        lueTiedosto(uusinimi);
        return true;
    }	
    


    /**
     * Alustaa kerhon lukemalla sen valitun nimisest‰ tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     */
    protected void lueTiedosto(String nimi) {
        henkNimi = nimi;
        setTitle("Opintoapuri - " + henkNimi);
        String virhe = opintoapuriSwing.lueTiedosto(nimi);
        if (virhe != null)
            JOptionPane.showMessageDialog(null, virhe);
    }
    
	/**
	 * Create the frame.
	 */
	public OpintoapuriGUI() {
		setTitle("Opintoapuri");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				opintoapuriSwing.lopetaOhjelma();
			}
		});
		

		opintoapuriSwing.setLblTutkinnonNimi(lblTutkinnonNimi);
		opintoapuriSwing.setEditPanelHakuKentta(editPanelHakuKentta);
		opintoapuriSwing.setTxtPainotettuKeskiarvo(txtKeskiarvo);
		opintoapuriSwing.setTxtKeskiarvo(txtPainotettuKeskiarvo);
		opintoapuriSwing.setTableKurssiTaulu(tableKurssiTaulu);
		opintoapuriSwing.setListTutkinnot(listTutkinnot);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 510);
		
		setJMenuBar(menuPaneeli);
		
		menuPaneeli.add(mnTiedosto);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		mnTiedosto.add(mntmVaihdaKayttaja);
		mntmVaihdaKayttaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				avaa();
			}
		});
		mntmPoistaKayttaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				opintoapuriSwing.poistaKayttaja();
			}
		});
		
		mnTiedosto.add(mntmPoistaKayttaja);
		
		mnTiedosto.add(mntmTallenna);
		mntmTallenna.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				opintoapuriSwing.talletaTutkinto();
			}
		});

		
		mnTiedosto.add(mntmLopeta);
		mntmLopeta.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				opintoapuriSwing.lopetaOhjelma();
			}
		});
		
		
	
		menuPaneeli.add(mnMuokkaa);
		mntmLisOpintokokonaisuus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				opintoapuriSwing.lisaaOpintokokonaisuus();
			}
		});
		
		mnMuokkaa.add(mntmLisOpintokokonaisuus);
		mntmPoistaOpintokokonaisuus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				opintoapuriSwing.poistaOpintokokonaisuus();
			}
		});
		
		mnMuokkaa.add(mntmPoistaOpintokokonaisuus);
		mntmLisKurssi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				opintoapuriSwing.lisaaKurssi();
			}
		});
		
		mnMuokkaa.add(mntmLisKurssi);
		mntmMuokkaaKurssia.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				opintoapuriSwing.muokkaaKurssinTietoja();
			}
		});
		
		mnMuokkaa.add(mntmMuokkaaKurssia);
		mntmPoistaKurssi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				opintoapuriSwing.poistaKurssi();
			}
		});
		
		mnMuokkaa.add(mntmPoistaKurssi);
		
		menuPaneeli.add(mnApua);
		
		mnApua.add(mntmApua);
		mntmApua.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				opintoapuriSwing.naytaApua();
			}
		});
		
		mnApua.add(mntmTietoja);
		mntmTietoja.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				opintoapuriSwing.naytaTietoja();
			}
		});
		
			
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(alaPaneeli, BorderLayout.SOUTH);
		alaPaneeli.setLayout(new BorderLayout(0, 0));
		
		alaPaneeli.add(alaOikeaPaneeli, BorderLayout.EAST);
		alaOikeaPaneeli.setLayout(new BoxLayout(alaOikeaPaneeli, BoxLayout.Y_AXIS));
		vasenHaku.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		contentPane.add(vasenHaku, BorderLayout.WEST);
		
		vasenHaku.setLeftComponent(panelTutkinnotNimet);
		
		panelTutkinnotNimet.add(lblTutkinnot);
		panelTutkintoLista.setMaximumSize(new Dimension(10, 32767));
		
		vasenHaku.setRightComponent(panelTutkintoLista);
		listTutkinnot.addSelectionChangeListener(new SelectionChangeListener<String>() {
			public void selectionChange(IStringListChooser<String> arg0) {
				opintoapuriSwing.haeTiedot();
			}
		});
	
		listTutkinnot.setSelectedIndex(0);
		listTutkinnot.setKohteet(new String[] {"Kauppatieteiden kandidaatti", "Markkinoinnin aineopinnot"});
		
		
		panelTutkintoLista.add(listTutkinnot);
		listTutkinnot.setLayout(new BoxLayout(listTutkinnot, BoxLayout.X_AXIS));
		splitPanelTutkintoTiedot.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		contentPane.add(splitPanelTutkintoTiedot, BorderLayout.CENTER);
		
		splitPanelTutkintoTiedot.setLeftComponent(splitPanelYlaTiedot);
		
		splitPanelYlaTiedot.setLeftComponent(panelTutkintoHaku);
		panelTutkintoHaku.setLayout(new BoxLayout(panelTutkintoHaku, BoxLayout.Y_AXIS));
		
		panelTutkintoHaku.add(lblTutkinnonNimi);
		editPanelHakuKentta.getLabel().setMaximumSize(new Dimension(9, 9));
		editPanelHakuKentta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				opintoapuriSwing.hae();
				editPanelHakuKentta.setText("");
			}
		});
		editPanelHakuKentta.setMaximumSize(new Dimension(2147483647, 25));
		editPanelHakuKentta.setCaption("Haku");
		
		panelTutkintoHaku.add(editPanelHakuKentta);
		panelOikeaYla.setMaximumSize(new Dimension(2500, 32767));
		
		splitPanelYlaTiedot.setRightComponent(panelOikeaYla);
		panelOikeaYla.setLayout(new BoxLayout(panelOikeaYla, BoxLayout.Y_AXIS));
		panelOikeaYla.add(lblKeskiarvo);
		lblKeskiarvo.setEnabled(false);
		lblKeskiarvo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblKeskiarvo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtKeskiarvo.setEditable(false);
		txtKeskiarvo.setMaximumSize(new Dimension(125, 2147483647));
		panelOikeaYla.add(txtKeskiarvo);
		txtKeskiarvo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtKeskiarvo.setText("2.75");
		txtKeskiarvo.setColumns(10);
		panelOikeaYla.add(lblPainotettuKeskiarvo);
		lblPainotettuKeskiarvo.setEnabled(false);
		lblPainotettuKeskiarvo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txtPainotettuKeskiarvo.setEditable(false);
		txtPainotettuKeskiarvo.setMaximumSize(new Dimension(125, 2147483647));
		panelOikeaYla.add(txtPainotettuKeskiarvo);
		txtPainotettuKeskiarvo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPainotettuKeskiarvo.setText("4.25");
		txtPainotettuKeskiarvo.setColumns(10);
		
		splitPanelTutkintoTiedot.setRightComponent(scrollPanelKurssiTiedot);
		tableKurssiTaulu.getTable().setModel(new DefaultTableModel(
			new Object[][] {
				{"Ohjelmointi 1", "6", "21.12.2011", "5", "ITKP102"},
				{"Johdatus ohjelmistotekniikkaan", "3", "21.12.2011", "4", "ITKA202"},
				{"Algoritmit 1", "4", null, null, ""},
				{"Markkinointitutkimus ja informaatioj\u00E4rjestelm\u00E4t", "6", "09.12.2011", "5", "YMAA120"},
			},
			new String[] {
				"Kurssin nimi", "Laajuus (OP)", "Suorituspvm", "Arvolause", "Kurssikoodi"
			}
		));
		tableKurssiTaulu.getTable().getColumnModel().getColumn(0).setPreferredWidth(229);
		tableKurssiTaulu.getTable().getColumnModel().getColumn(1).setPreferredWidth(79);
		tableKurssiTaulu.getTable().getColumnModel().getColumn(3).setPreferredWidth(69);
		tableKurssiTaulu.setPreferredSize(new Dimension(452, 332));
		
		scrollPanelKurssiTiedot.setViewportView(tableKurssiTaulu);
	}

}
