package Model.Filtracja.Okna;

public class OknoProstokatne extends Okno {

	public OknoProstokatne(int M) {
		super(M);
	}

	@Override
	public double getValue(double n) {
		if(n > 0 && n < M){
			return 1;
		}else{
			return 0;
		}
	}

}
