/**
 * 
 */
package opintoapuri;

/**
 * @author Matias Partanen
 * @version 14.03.2012
 * Poikkeusluokka virheille, joita tietorakenteessa tulee
 */
public class ApuriException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktori, jolle tuodaan merkkijonona viesti mukana, jota k‰ytet‰‰n poikkeuksessa
	 * @param viesti viesti, jota k‰ytet‰‰n
	 */
	public ApuriException(String viesti) {
		super(viesti);
	}
}
