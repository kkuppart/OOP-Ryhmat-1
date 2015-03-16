import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
//suvatekst, et vaadata kuidas git töötab1
public class Levik {
	public static void main(String[] argv) throws Exception { 
	boolean kasveel=true;
	while (kasveel) {
		System.out
				.println("tere! See on lihtne programm viiruste leviku simuleerimiseks."
						+ " Populatsiooni kujutatakse ette ruuduna, millel asetsevad mingi"
						+ "\n"
						+ "tihedusega "
						+ "inimesed."
						+ "\n"
						+ "Alguses luuakse populatsioon ning mingi hulk viirusega nakatunud inimesi "
						+ "kellelt see hakkab mingi tõenäosusega nendega"
						+ "\n"
						+ "kokkupuutuvatele"
						+ " inimestele levima. Viirus tapab kõik nakatunud, aga on võimalik jälgida, "
						+ "kuidas inimeste tihedus ja viiruse nakatuvus"
						+ "\n"
						+ "mõjutab suremust. ");
		System.out
				.println("----------------------------------------------------------------------------------------------------------------------");
		String pwd= System.getProperty("user.dir");
		System.out.println(pwd);
		File file =new File(pwd+"/logi.txt");
		PrintWriter pw= new PrintWriter("logi.txt");
		pw.println("tere");
		//pw.close();
		
		Scanner scan = new Scanner(System.in);

		System.out
				.println("Loome uue populatsiooni. Mis peaks olema populatsiooni ulatus?(täisarv)"
						+ " Ulatus^2 on inimeste arv juhul, kui inimeste tihedus = 1.");

		int ulatus = scan.nextInt();

		System.out
				.println("mis peaks olema inimeste paiknemise tihedus?(ujukomaarv 0 ja 1.0 vahel)");
		double tihedus = scan.nextDouble();

		Populatsioon pop = new Populatsioon(ulatus, tihedus); // Loome uue
																// populatsiooni pop

		System.out.println("inimeste arv on " + pop.inimeste_arv + "\n");
		int alguses = pop.inimeste_arv;

		System.out
				.println("mis peaks olema viirusega nakatumise tõenäosus kokkupuutel?(ujukomaarv 0 ja 1.0 vahel)"
						+ "\n");

		double nakatuvus = scan.nextDouble();
		Viirus V = new Viirus(1.0, nakatuvus); // loome uue viiruse

		System.out.println("inimeste arv alguses on " + pop.inimeste_arv);

		int nakatunute_arv;
		if (alguses/10>1)
				 nakatunute_arv=alguses/10;
		else  	
			nakatunute_arv=1;		// võtame alguses mingi arvu nakatunuid
				
		for (int i = 0; i < nakatunute_arv; i++) { // ja märgime suvalises kohas
													// asetsevate inimeste välja
													// on_nakatunud Tõeseks.
			int a = (int) Math.floor(Math.random() * pop.ulatus);
			int b = (int) Math.floor(Math.random() * pop.ulatus);
			if (pop.inimesed[a][b] != null) {
				pop.inimesed[a][b].on_nakatanud = true;
			}
		}

		// Pop.inimesed[Math.round(int)(Math.random()*Pop.ulatus)-1][Math.random()*Pop.ulatus-1].on_nakatanud
		// = true;

		int s =0;

		for (int k = 0; pop.inimeste_arv!=s; k++) { // k loeb mitu tsüklit me teeme
			s=pop.inimeste_arv;
			ArrayList<ArrayList<Integer>> nakatunud = new ArrayList<ArrayList<Integer>>();

			for (int i = 0; i < pop.ulatus; i++) { // i ja j on rea - ja
													// veeruindeksid

				for (int j = 0; j < pop.ulatus; j++) {

					if (pop.inimesed[i][j] != null
							&& pop.inimesed[i][j].on_nakatanud == true) {

						if (i + 1 < pop.ulatus // proovime nakatada asukohal
												// i+1, j asetsevat inimest
								&& pop.inimesed[i + 1][j] != null) {
							if (V.nakatumus > Math.random()) {
								ArrayList<Integer> koords = new ArrayList<Integer>();
								koords.add(pop.inimesed[i + 1][j].xkoord);
								koords.add(pop.inimesed[i + 1][j].ykoord);
								nakatunud.add(koords); // lisame nakatunud
														// inimese koordinaadid
														// Arraylisti, et hiljem
														// muuta nende omadus
														// on_nakatunud true-ks.
							}
						}

						if (j + 1 < pop.ulatus // teeme sama kõikide nakatunud
												// inimese lähedal olevate(mitte
												// diagonaalis paiknevate)
												// inimestega.
								&& pop.inimesed[i][j + 1] != null) {
							if (V.nakatumus > Math.random()) {
								ArrayList<Integer> koords = new ArrayList<Integer>();
								koords.add(pop.inimesed[i][j + 1].xkoord);
								koords.add(pop.inimesed[i][j + 1].ykoord);
								nakatunud.add(koords);
							}
						}

						if (i > 0 && pop.inimesed[i - 1][j] != null) {
							if (V.nakatumus > Math.random()) {
								ArrayList<Integer> koords = new ArrayList<Integer>();
								koords.add(pop.inimesed[i - 1][j].xkoord);
								koords.add(pop.inimesed[i - 1][j].ykoord);
								nakatunud.add(koords);
							}
						}

						if (j > 0 && pop.inimesed[i][j - 1] != null) {
							if (V.nakatumus > Math.random()) {
								ArrayList<Integer> koords = new ArrayList<Integer>();
								koords.add(pop.inimesed[i][j - 1].xkoord);
								koords.add(pop.inimesed[i][j - 1].ykoord);
								nakatunud.add(koords);
							}
						}

					}
				}

			}

			for (int i = 0; i < pop.ulatus; i++) {
				for (int j = 0; j < pop.ulatus; j++) {
					if (pop.inimesed[i][j] != null
							&& pop.inimesed[i][j].on_nakatanud == true) {
						pop.inimesed[i][j].tapa();
						
					}
				}
			}

			for (int i = 0; i < nakatunud.size(); i++) {
				if (pop.inimesed[nakatunud.get(i).get(0)][nakatunud.get(i).get(
						1)] != null) {
					pop.inimesed[nakatunud.get(i).get(0)][nakatunud.get(i).get(
							1)].on_nakatanud = true;
				}
			}
			pw.print(k);
			pw.print(" ");
			pw.print("surnud on");
			pw.print(" ");
			pw.println(alguses - pop.inimeste_arv );
		}
		System.out.println("inimeste arv lõpus on " + pop.inimeste_arv);
		System.out.println("suremus on " + ((float) alguses - pop.inimeste_arv)
				* 100 / alguses + "%");
		pw.close();
		System.out.println("Kas soovid simulatsiooni korrata?"+"\n"+"Kirjuta jah kui soovid")
			;
			String kastahab= scan.next();
					if  (kastahab.equals("jah")) 
						kasveel=true;
					else
						break;
												{
						
					
}

}
	}
}


	


