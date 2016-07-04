package risk;
public class Score {
	
private int riskScore = 0;

public Score(int wiek, boolean mezczyzna, int cisnienieSkurczowe, int wzrost, boolean papierosy, boolean leki, boolean cukrzyca, boolean zawalSerca, boolean udarMozgu)
	{
		int intPapierosy = 0;
		if (papierosy)
			intPapierosy = 1;	
		
		ryzykoWiekPlecPapierosy(wiek, mezczyzna, intPapierosy);
		ryzykoCisnienie(cisnienieSkurczowe);
		ryzykoWzrost(wzrost, mezczyzna);
		ryzykoChoroby(mezczyzna, cukrzyca, zawalSerca, udarMozgu);
	}

	public String analizuj(int risk)
	{
		String analiza = "(brak danych)";
		
		if (risk < 15)
			analiza = "ma³e";

		if ((risk >= 15) && (risk <= 25))
			analiza = "œrednie";

		if ((risk > 20) && (risk >= 30))
			analiza = "znaczne";

		if ((risk > 30) && (risk >= 45))
			analiza = "wysokie";

		if (risk > 35)
			analiza = "bardzo wysokie";
		
		return analiza;
	
	}


	public int getRiskScore(boolean leki)
	{
		if (leki)
			return (this.riskScore / 2);
		else
			return this.riskScore;
	}
	
	// ryzyko zwi¹zane z wiekiem, p³ci¹ oraz paleniem papierosów
	private void ryzykoWiekPlecPapierosy(int wiek, boolean czyMezczyzna, int papierosy)
	{
		boolean mezczyzna = czyMezczyzna;
		if (mezczyzna)
		{
			this.riskScore += 12; 	
			
			if ((wiek > 34) && (wiek < 40))
				this.riskScore += (papierosy * 9);

			if ((wiek > 39) && (wiek < 45))
				this.riskScore += 4 + (papierosy * 7);
				
			if ((wiek > 44) && (wiek < 50))
				this.riskScore += 7 + (papierosy * 7);

			if ((wiek > 49) && (wiek < 55))
				this.riskScore += 11 + (papierosy * 6);

			if ((wiek > 54) && (wiek < 60))
				this.riskScore += 14 + (papierosy * 6);

			if ((wiek > 59) && (wiek < 65))
				this.riskScore += 18 + (papierosy * 5);

			if ((wiek > 64) && (wiek < 70))
				this.riskScore += 22 + (papierosy * 4);
					
			if ((wiek > 69) && (wiek < 75))
				this.riskScore += 25 + (papierosy * 4);
		}

		if (!mezczyzna) 		//kobieta
		{
			if ((wiek > 34) && (wiek < 40))
				this.riskScore += (papierosy * 13);
				
			if ((wiek > 39) && (wiek < 45))
				this.riskScore += 5 + (papierosy * 12);
				
			if ((wiek > 44) && (wiek < 50))
				this.riskScore += 9 + (papierosy * 11);

			if ((wiek > 49) && (wiek < 55))
				this.riskScore += 14 + (papierosy * 10);

			if ((wiek > 54) && (wiek < 60))
				this.riskScore += 18 + (papierosy * 10);

			if ((wiek > 59) && (wiek < 65))
				this.riskScore += 23 + (papierosy * 9);

			if ((wiek > 64) && (wiek < 70))
				this.riskScore += 27 + (papierosy * 9);

			if ((wiek > 69) && (wiek < 75))
				this.riskScore += 32 + (papierosy * 8);
		}
	}
	//end Wiek, P³eæ, Papierosy
	
	// ryzyko zwi¹zane z ciœnieniem
	private void ryzykoCisnienie(int cisnienie)
	{
		if ((cisnienie >= 120) && (cisnienie <= 129))
			this.riskScore += 1;

		if ((cisnienie >= 130) && (cisnienie <= 139))
			this.riskScore += 2;
				
		if ((cisnienie >= 140) && (cisnienie <= 149))
			this.riskScore += 3;
				
		if ((cisnienie >= 150) && (cisnienie <= 159))
			this.riskScore += 4;
				
		if ((cisnienie >= 160) && (cisnienie <= 169))
			this.riskScore += 5;
				
		if ((cisnienie >= 170) && (cisnienie <= 179))
			this.riskScore += 6;

		if ((cisnienie >= 180) && (cisnienie <= 189))
			this.riskScore += 8;
				
		if ((cisnienie >= 190) && (cisnienie <= 199))
			this.riskScore += 9;
			
		if ((cisnienie >= 200) && (cisnienie <= 209))
			this.riskScore += 10;

		if (cisnienie >= 210)
			this.riskScore += 11;
	}
	//end Ciœnienie

	// ryzyko zwi¹zane z wzrostem
	private void ryzykoWzrost(int wzrost, boolean mezczyzna)
	{
		if (!mezczyzna) 		//kobieta
		{
			if (wzrost < 145)
				this.riskScore += 6;
	
			if ((wzrost >= 145) && (wzrost <= 154))
				this.riskScore += 4;
				
			if ((wzrost >= 155) && (wzrost <= 164))
				this.riskScore += 3;
					
			if ((wzrost >= 165) && (wzrost <= 174))
				this.riskScore += 2;
		}
		
		if (mezczyzna) 		//mezczyzna
		{
			if (wzrost < 160)
				this.riskScore += 6;
	
			if ((wzrost >= 160) && (wzrost <= 169))
				this.riskScore += 4;
				
			if ((wzrost >= 170) && (wzrost <= 179))
				this.riskScore += 3;
					
			if ((wzrost >= 180) && (wzrost <= 189))
				this.riskScore += 2;
		}
	}
	//end Wzrost

	// ryzyko zwi¹zane z chorobami
	private void ryzykoChoroby(boolean mezczyzna, boolean cukrzyca, boolean zawalSerca, boolean udarMozgu)
	{
		if (cukrzyca)
		{	
			if (mezczyzna)
				this.riskScore += 2;
			else
				this.riskScore += 9;
		}
					
		if (zawalSerca)
			this.riskScore += 8;

		if (udarMozgu)
			this.riskScore += 8;	
	}
}
