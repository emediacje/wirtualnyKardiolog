package dane;
public class DanePodstawowe {

	private int wiek;
	private int wzrost;
	private int waga;
	private boolean mezczyzna;

	public DanePodstawowe()
	{
		// inicjalizajca wartoœci domyœlnych
		this.wiek = 20;
		this.wzrost = 160;
		this.waga = 70;
		this.mezczyzna = true;
	}

	
	public DanePodstawowe(boolean plecMezczyzna, int wiek, int wzrost, int waga)
	{
		this.mezczyzna = plecMezczyzna;
		
		if ((wiek > 15) && (wiek < 120))
			this.wiek = wiek;
		else
			System.out.println("Proszê podaæ wiek w przedziale 15 - 120 lat"); 
			
		this.wzrost = wzrost;
		this.waga = waga;
	}
		
	//oblicz BMI
	public double wskaznikBMI(int waga, int wzrost)
	{
		if (sprawdzDoBmi(waga, wzrost))
			return (waga / ((wzrost * wzrost)/10000));
		else
			return -1.0;
	}
	
	public String bmiTekst(double bmi)
	{
		if (bmi == -1.0)
			return "<brak mo¿liwoœci obliczenia>";
		else
			return "" + bmi;
	}

	// czy waga i wzrost jest dobra
	private boolean sprawdzDoBmi(int waga, int wzrost)
	{
		return ((waga > 35) && (waga < 250) && (wzrost > 150) && (wzrost < 280));
	}
	
	public String interpretujBMI(double BMI, String zwrot)
	{
		if (BMI != -1.0)
		{
			String wynik = "Zgodnie z moimi wyliczeniami, wska¿nik masy cia³a - BMI u "+ zwrot +" wynosi: " + BMI +".";
			
			if (BMI < 16.0)
				wynik += "\nWartoœæ ta jest zdecydowanie za niska. Proszê nie zbagaetlizowaæ tego, lecz skonsultowaæ to z lekarzem!";	
	
			if ((BMI >= 16.0) && (BMI <= 16.99))
				wynik += "\nWartoœæ ta jest niska. \nProszê skonsultowaæ to z lekarzem!";	
	
			if ((BMI >= 17.0) && (BMI <= 18.49))
				wynik += "\nWartoœæ ta wskazuje na niedowagê. \nWarto skonsultowaæ ten wynik z lekarzem!";
		
			if ((BMI >= 18.5) && (BMI <= 24.99))
				wynik += "\nWartoœæ ta jest prawid³owa. ";
			
			if ((BMI >= 25.0) && (BMI <= 29.99))
				wynik += "\nWartoœæ ta oznacza nadwagê. \n Zachêam do wprowadzenia zdrowej diety oraz zwiêkszonej aktywoœci fizycznej.";
			
			if ((BMI >= 30.0) && (BMI <= 34.99))
				wynik += "\nWartoœæ ta oznacza I stopieñ oty³oœci. \nKoniecznie skonsultuj ten fakt z lekarzem, nale¿y tak¿e pamiêtaæ o zdrowej diecie oraz regularnej aktywoœci fizycznej.";
			
			if ((BMI >= 35.0) && (BMI <= 39.99))
				wynik += "\nWartoœæ ta oznacza II stopieñ oty³oœci. \n Nie wolno tego zbagaetlizowaæ, proszê skonsultowaæ siê z lekarzem. \nNale¿y tak¿e pamiêtaæ o zdrowej diecie oraz regularnej aktywoœci fizycznej.";
			
			if ((BMI >= 40.0))
				wynik += "\nWartoœæ ta oznacza III stopieñ oty³oœci. \nKoniecznie odwiedŸ lekarza. \nZadbaj tak¿e o zdrow¹ dietê oraz aktywnie spêdzaj czas.";
			
//			wynik += "\nPrawid³owa wartoœæ BMI powinna wynosiæ pomiêdzy 18,50 a 24,99.";
			
			return wynik;
		}
		else
			return "";	
	}

	@Override
	public String toString()
	{
		double BMI = wskaznikBMI(waga, wzrost);
		return "Dane podstawowe: wiek: "+ this.wiek + " waga " + this.waga + ", wzrost " + this.wzrost + ", BMI " + this.bmiTekst(BMI);
	}
}
