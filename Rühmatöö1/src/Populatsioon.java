public class Populatsioon {

	int ulatus; // näitab, kui suur on ruudu pikkus, milles populatsioon asetseb
	double tihedus; // näitab, kui suure tõenäosusega luuakse mingisse
					// konkreetsesse ruudustikku ruutu inimene

	Isik[][] inimesed; // loob objektidest Isik kahemõõtmelise järjendi

	int inimeste_arv;

	public Populatsioon(int ulatus, double tihedus) {

		inimesed = new Isik[ulatus][ulatus];
		this.ulatus = ulatus;
		this.tihedus = tihedus;
		for (int i = 0; i < ulatus; i++) {

			for (int j = 0; j < ulatus; j++) {

				if (tihedus > Math.random()) {
					inimesed[i][j] = new Isik(i, j, false, this);

				}
			}
		}
		for (int i = 0; i < ulatus; i++) {				//tagastab inimeste arvu konkreetsel hetkel
			for (int j = 0; j < ulatus; j++) {
				if (inimesed[i][j] != null) {
					inimeste_arv++;
				}
			}
		}
	}
}
