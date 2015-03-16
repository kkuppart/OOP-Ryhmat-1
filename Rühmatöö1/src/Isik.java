public class Isik {

	int xkoord;					//koordinaadid
	int ykoord;
	boolean on_nakatanud;
	Populatsioon populatsioon;			//field populatsioon, mis antakse isikule kaasa

	public Isik(int xkoord, int ykoord, boolean on_nakatanud,
			Populatsioon populatsioon) {

		this.xkoord = xkoord;
		this.ykoord = ykoord;
		this.on_nakatanud = on_nakatanud;
		this.populatsioon = populatsioon;

	}

	public void tapa() {				//meetod nakatunud inimestest vabanemiseks
		populatsioon.inimesed[xkoord][ykoord] = null;
		populatsioon.inimeste_arv--;
	}
}
