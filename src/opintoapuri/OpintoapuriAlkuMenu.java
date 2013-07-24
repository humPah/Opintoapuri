package opintoapuri;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

/**
 * @author Matias Partanen
 * @version 20.03.2012
 * Opintoapurin alkumenu, jossa kysyt‰‰n k‰ytt‰j‰lt‰ k‰ytt‰j‰, kenen tietoja h‰n haluaa k‰sitell‰.
 * Mik‰li k‰ytt‰j‰‰ ei ole olemassa, luodaan sellainen
 */
public class OpintoapuriAlkuMenu extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtEtunimi;
    private String result = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OpintoapuriAlkuMenu dialog = new OpintoapuriAlkuMenu(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * K‰ynnist‰ dialogi ja kysy kerhon nimi
     * @return k‰ytt‰j‰n antama nimi
     */
    public static String askName() {
        String ret = null;
        try {
            OpintoapuriAlkuMenu dialog = new OpintoapuriAlkuMenu(null);
            dialog.setVisible(true);
            ret = dialog.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    
    /**
     * Palautetana dialogista saatu tulos. null jos poistuttiin
     * @return dialogiin kirjoitettu jono, null jos lopetettiin
     */
    public String getResult() {
        return result;
    }
    
    
    /**
	 * laitetaan dialogin tuloksen arvoksi jotain
	 * @param result pistet‰‰n jotain arvoksi
     */
    public void setResult(String result) {
        this.result = result;
    }
    
    
	/**
	 * Create the dialog.
	 * @param parent mink‰ alta k‰ynnistettiin
	 */
	public OpintoapuriAlkuMenu(Frame parent) {
		super(parent, "frame", true);
		
		setTitle("Opintoapuri");		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			getContentPane().add(splitPane);
			{
				JPanel alaPaneeli = new JPanel();
				splitPane.setRightComponent(alaPaneeli);
				{
					JSplitPane splitPane_1 = new JSplitPane();
					alaPaneeli.add(splitPane_1);
					{
						JLabel lblNimi = new JLabel("Anna etunimesi :");
						splitPane_1.setLeftComponent(lblNimi);
					}
					{
						txtEtunimi = new JTextField();
						txtEtunimi.addKeyListener(new KeyAdapter() {
							@Override
							public void keyTyped(KeyEvent arg0) {
							}
						});
						splitPane_1.setRightComponent(txtEtunimi);
						txtEtunimi.setColumns(10);
					}
				}
				{
					JButton btnOk = new JButton("OK");
					btnOk.setVerticalAlignment(SwingConstants.BOTTOM);
					btnOk.setHorizontalAlignment(SwingConstants.RIGHT);
					btnOk.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							setResult(txtEtunimi.getText());
							setVisible(false);												
						}
					});
					alaPaneeli.add(btnOk);
					getRootPane().setDefaultButton(btnOk);
				}
				{
					JButton btnLopeta = new JButton("Lopeta");
					btnLopeta.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							setResult(null);
							setVisible(false);
						}
					});
					alaPaneeli.add(btnLopeta);
				}
			}
			{
				JPanel ylaPaneeli = new JPanel();
				splitPane.setLeftComponent(ylaPaneeli);
				{
					JLabel lblOpintoapuri = new JLabel("Opintoapuri");
					lblOpintoapuri.setFont(new Font("Tahoma", Font.PLAIN, 28));
					ylaPaneeli.add(lblOpintoapuri);
				}
			}
		}
	}

}
