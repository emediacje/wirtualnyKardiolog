package dane;

public class Cisnienie {

	private int  cisnienieSkurczowe;
	private int  cisnienieRozkurczowe;
	private boolean leki = false;
	
	public Cisnienie(int skurczowe, int rozkurczowe, boolean lekiObnizaj¹ceCisnienie)
	{
		if (lekiObnizaj¹ceCisnienie)
			this.leki = true;
		
		if (dobreDaneCisnienia(skurczowe, rozkurczowe))
		{
			this.cisnienieRozkurczowe = rozkurczowe;
			this.cisnienieSkurczowe = skurczowe;
		}
		else
		{
			drukuj("(!) B³¹d: nieprawid³owe dane ciœnienie");
		}
	}
	
	private boolean dobreDaneCisnienia(int skurczowe, int rozkurczowe)
	{
		if ((skurczowe < 30) || (skurczowe > 190) || (rozkurczowe < 25) || (rozkurczowe > 160))
			return false;
		else
			return true;
	}

	public void uwagi()	
	{
		if (!poprawneCisnienie(cisnienieSkurczowe, cisnienieRozkurczowe))
		{
			String uwagiDotCisnienia = dlaczegoZleCisnienie(cisnienieSkurczowe, cisnienieRozkurczowe);
			if (uwagiDotCisnienia != "")
			{
				drukuj("\nUwagi dot. ciœnienia: ");
				drukuj(uwagiDotCisnienia);
			}
		}
		else
			System.out.print(", jest ono prawid³owe. \n");
		
		if (this.leki)
		{
			drukuj("Stosujesz leki obni¿aj¹ce ciœnienie, które znacz¹co obni¿aj¹ ryzyko wyst¹pienia choroby wieñcowej.\nPamiêtaj o regularnym ich przyjmowaniu zgodnie z zaleceniami lekarza!");				
		}

	}

	private void drukuj(String komunikat)	
	{
		System.out.println(komunikat);
	}

	public boolean poprawneCisnienie(int skurczowe, int rozkurczowe)
	{
		if ((skurczowe < 80) || (skurczowe > 150) || (rozkurczowe < 45) || (rozkurczowe > 120))
			return false;
		else
			return true;
	}

	private String dlaczegoZleCisnienie(int skurczowe, int rozkurczowe)
	{
	String wynik = "";
		if (skurczowe < 80) 
			wynik += "- niskie ciœnienie skurczowe: " + skurczowe + " (norma: od 80) \n";
		
		if (skurczowe > 150) 
			wynik += "- wysokie ciœnienie skurczowe: " + skurczowe + " (norma: do 150) \n";

		if (rozkurczowe < 45) 
			wynik += "- niskie ciœnienie rozkurczowe: " + rozkurczowe + " (norma: od 45) \n";

		if (rozkurczowe > 120)
			wynik += "- wysokie ciœnienie rozkurczowe: " + rozkurczowe + " (norma: do 120) \n";

	return wynik;
		
	}	
}
