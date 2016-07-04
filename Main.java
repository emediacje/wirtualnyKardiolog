import komunikacja.Komunikacja;

public class Main {

	static void test(int liczba, Komunikacja komunikacja) throws java.lang.Exception
	{
		switch(liczba)
		{
		case 1:
			{
				komunikacja.drukuj("(19, true, 60, 90, 150, 58, true, false, false, false, true)");
				komunikacja.analizaWynikow(19, true, 60, 90, 150, 58, true, false, false, false, true);
				break;
			}
		
		case 2:
		{
			komunikacja.drukuj("(19, false, 60, 90, 150, 58, true, false, false, false, true)");
			komunikacja.analizaWynikow(19, false, 60, 90, 150, 58, true, false, false, false, true);
			break;
		}

			
		}
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{	
		Komunikacja komunikacja = new Komunikacja();
		komunikacja.diagnozuj();

//Test programu
//		test(1,komunikacja);
//		test(2,komunikacja);

		
	}
}
