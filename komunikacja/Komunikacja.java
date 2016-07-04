package komunikacja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dane.*;
import risk.*;

public class Komunikacja {
	
	public Komunikacja()
	{
	}
	
	public void drukuj(String komunikat)	
	{
		System.out.println(komunikat);
	}

	
	private String zwrot(boolean plec, boolean tryb)
	{
		if (plec)
			return (tryb) ? "Pan" : "Pana";
		else
			return "Pani";

	}

	public void diagnozuj() throws java.lang.Exception
	{
	
		drukuj("-------------------------------------------");
		drukuj("-   Internetowa  Diagnoza  Kardiologiczna  -");
		drukuj("-------------------------------------------");
		drukuj("\nWitam, zapraszam do wzięcia udziału w internetowej diagnozie kardiologicznej. \nCelem diagnozy jest oszacowanie ryzyka wystąpienia u Pani/a choroby wieńcowej.\n");
		int wiek = wczytajInt("Proszę podać wiek: ", 15, 120);
		int plecInt = podajPlec("Proszę podać płeć - (M lub K): ");	
		boolean plec = czyMezczyzna(plecInt);
				
		int wzrost = wczytajInt("Proszę podać "+ zwrot(plec, false)+" wzrost: ", 120, 220);
		int waga = wczytajInt("Proszę wpisać "+ zwrot(plec, false)+" wagę: ", 40, 210);

		
		int cisnienieSkurczowe = wczytajInt("Proszę podać ciśnienie skurczowe: ", 50, 180);
		int cisnienieRozkurczowe = wczytajInt("Proszę podać ciśnienie rozkurczowe: ", 30, 150);
	

		drukuj("\nNastępne pytania dotyczą "+ zwrot(plec, false)+" sytuacji zdrowotnej.\nNa pytania proszę odpowiadać: T lub N. \n");
		boolean leki = wczytajBoolean("Czy regularnie bierze "+ zwrot(plec, true)+" leki obniżające ciśnienie? (T / N)");
		boolean cukrzyca = wczytajBoolean("Czy choruje "+ zwrot(plec, true) +" na cukrzyce? (T / N)");
		boolean zawalSerca = wczytajBoolean("Czy miał/a "+ zwrot(plec, true) +" zawał serca? (T / N)");
		boolean udarMozgu = wczytajBoolean("Czy miał/a "+ zwrot(plec, true) +" udar mózgu? (T / N)");
		boolean papierosy = wczytajBoolean("Czy pali "+ zwrot(plec, true) +" papierosy? (T / N)");
	
		analizaWynikow(wiek, plec, cisnienieSkurczowe, cisnienieRozkurczowe, wzrost, waga, papierosy, leki, cukrzyca, zawalSerca, udarMozgu);
		
	}

	
	public void analizaWynikow(int wiek, boolean plec, int cisnienieSkurczowe, int cisnienieRozkurczowe, int wzrost, int waga, boolean papierosy, boolean leki, boolean cukrzyca, boolean zawalSerca, boolean udarMozgu) throws java.lang.Exception
	{
		DanePodstawowe dane = new DanePodstawowe(plec, wiek, wzrost, waga);
		
		Cisnienie cisnienie = new Cisnienie(cisnienieSkurczowe, cisnienieRozkurczowe, leki);
//		Choroby choroby = new Choroby(cukrzyca, zawalSerca, udarMozgu);
		double BMI = dane.wskaznikBMI(waga, wzrost);

		drukuj("-------------------------------------------");
		drukuj("-         P O D S U M O W A N I E         -");
		drukuj("-------------------------------------------");		
		String informacja = "\nDziękuję za udział w badaniu. Z zebranych informacji wynika, że jest "+ zwrot(plec, true) +" " + podajStringiemPlec(plec,true) + " w wieku " + wiek + " lat. ";
		if (wiek <= 30)
		{
			informacja += "W tym wieku rzadko dochodzi do poważnych chorób serca. Mimo wszystko proszę pamiętać o profilaktyce zdrowotnej"; 
		}
		drukuj(informacja);
		drukuj(dane.interpretujBMI(BMI, (zwrot(plec, false))));
		System.out.print(zwrot(plec, false)+" ciśnienie wynosi: " + cisnienieSkurczowe + "/" + cisnienieRozkurczowe);
		cisnienie.uwagi();
		//operacja.drukuj(choroby.toString());
		
		Score ocenaRyzyka = new Score(wiek, plec, cisnienieSkurczowe, wzrost, papierosy, leki, cukrzyca, zawalSerca, udarMozgu);
		String ryzyko = ocenaRyzyka.analizuj(ocenaRyzyka.getRiskScore(leki));
		drukuj("Ryzyko wystąpienia u "+zwrot(plec, false)+" choroby wieńcowej jest "+ ryzyko);
		drukuj("To "+ zwrot(plec, true) +" ma największy wpływ na swoje zdrowie, polecam zatem: ");

		if (cukrzyca || leki)
		{
			String lekiCukrzyca = cukrzyca ? " na cukrzycę," : "";
			String lekiCisnienie = leki ? " obniżających ciśnienie," : "";
			
			drukuj("- regularne zażywanie leków:"+ lekiCukrzyca + lekiCisnienie + " zgodnie z wskazówkami lekarza,");
		}
		
		if (papierosy)
			drukuj("- ograniczenie palenia papierosów aż do jego zaniechania,");

		String udarMozguTekst = "", zawalSercaTekst = "";
		if (udarMozgu)
			udarMozguTekst = "udarze mózgu, ";
		if (zawalSerca)
			zawalSercaTekst = "zawale serca, ";
		
		if (zawalSerca || udarMozgu)
			drukuj("- stałą opiekę lekarza w zakresie rekonwalescencji po przebytym: "+ udarMozguTekst + zawalSercaTekst);
		
		if (!cisnienie.poprawneCisnienie(cisnienieSkurczowe, cisnienieRozkurczowe))
			drukuj("- regularną kontrolę ciśnienia, natomiast w przypadku niepokojących sygnałów proszę skontaktować się z lekarzem, ");
		
	}
	
	
	public int wczytajInt(String komunikat, int valMin, int valMax) throws IOException
	{
		int daneInt = -1;

		while (daneInt < 0)
		{
			drukuj("-> " + komunikat);

			BufferedReader bufor = new BufferedReader(new InputStreamReader(System.in));
	
			try {
				daneInt = Integer.parseInt(bufor.readLine());
			}

			catch (NumberFormatException e) 
			{
				drukuj("(!) Błąd: wprowadź liczbę całkowitą!");
			}

			if ((daneInt != -1) && ((daneInt < valMin) || (daneInt > valMax)))
			{
				drukuj("(!) Błąd: wpisz wartość od " + valMin + " do " + valMax);
				daneInt = -1;
			}
		}
		
		return daneInt;
	}
	
	public boolean czyMezczyzna(int plec) throws IOException
	{
		if (plec == 0)
			return true;
		else
			return false;
	}
	
	public int wczytajPlec(String komunikat) throws IOException
	{
		drukuj(komunikat);
		
		BufferedReader bufor = new BufferedReader(new InputStreamReader(System.in));
		String mezczyzna = null;
	
		try {
			mezczyzna = bufor.readLine();
		}

		catch (NumberFormatException e) 
		{
			drukuj("(!) Błąd: nie pobrano danych dot. płci!");
			return -1;
		}

		switch(mezczyzna)
		{
			case "M":
				return 0;

			case "m":
				return 0;
								
			case "K":
				return 1;

			case "k":
				return 1;
				
			default:
				drukuj("(!) Błąd: nieprawidłowa wartość, wpisz literę M lub K");
				return -1;
		}
	}

	public boolean wczytajBoolean(String komunikat) throws IOException
	{
		drukuj(komunikat);
		
		BufferedReader bufor = new BufferedReader(new InputStreamReader(System.in));
		String wartosc = null;
	
		try {
			wartosc = bufor.readLine();
		}

		catch (NumberFormatException e) 
		{
			drukuj("(!) Błąd: niepoprawna odpowiedź! Przyjęto wartość domyślną: N");
			return false;
		}

		switch(wartosc)
		{
			case "T":
				return true;

			case "t":
				return true;

			case "N":
				return false;

			case "n":
				return false;
				
			default:
				drukuj("(!) Błąd: nieprawidłowa wartość, wpisz literę T lub N");
				return false;
			
		}

	}

	public String podajStringiemPlec(boolean mezczyzna, boolean czyOdmieniac) throws IOException
	{
		if (mezczyzna)
		{
			if (czyOdmieniac)
				return "mężczyzną";
			else
				return "mężczyzna";			
		}
		else
		{
			if (czyOdmieniac)
				return "kobietą";
			else
				return "kobieta";
		}
	}

	
	public int podajPlec(String komunikat) throws IOException
	{
		int wynik = -1;
		wynik = wczytajPlec(komunikat);
		
		while (wynik < 0)
			wynik = wczytajPlec(komunikat);
		
		return wynik;
	}
}
