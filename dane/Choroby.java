package dane;

public class Choroby {

	private boolean cukrzyca;
	private boolean zawalSerca;
	private boolean udarMozgu;

	public Choroby(boolean cukrzyca, boolean zawalSerca, boolean udarMozgu)
	{
		this.cukrzyca = cukrzyca;
		this.zawalSerca = zawalSerca;
		this.udarMozgu = udarMozgu;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String wynik = "";
		
		if (this.cukrzyca)
			wynik += "\n- cukrzyca";

		if (this.zawalSerca)
			wynik += "\n- przebyty zawa³ serca";

		if (this.udarMozgu)
			wynik += "\n- przebyty udar mózgu";
		
		if (wynik != "")
			return "Twoja historia chorobowa: " + wynik + ".";
		else
			return "Jesteœ zdrowy.";
	}
}
