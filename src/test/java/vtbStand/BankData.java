package vtbStand;

/*
TODO Почему для хранения данных исплользовал интерфейс, для тестов мы можем использовать большое количество банков, то сделай Enum.
Сделано
TODO (2) Вынести Random Data в другое место
 */

public enum BankData {
	// -==| Bank Data |==-
	// -|БАЙКОНУРСКИЙ ФИЛИАЛ АО "ФОНДСЕРВИСБАНК"|-
	FONDSERVICEBANK(
			"040037470",
			"БАЙКОНУРСКИЙ ФИЛИАЛ АО \"ФОНДСЕРВИСБАНК\"",
			"30101.810.3.00000000470",
			"БАЙКОНУР"
	);

	private final String bic ;
	private final String name;
	private final String acc ;
	private final String town;
	
	BankData(String bic, String name, String acc, String town) {
		this.bic  = bic;
		this.name = name;
		this.acc  = acc;
		this.town = town;
	}
	
	public String getBIC()     {	return bic ;	}
	public String getName()    {	return name;	}
	public String getAcc()     {	return acc ;	}
	public String getTown()    {	return town;	}
	
	
	
	// -==| Random Data |==-
	public static String NUMBERS_5x0 = "00000";
	public static String NUMBERS_9x0 = "000000000";
	public static String NO_DIGITS_STRING = "qazWSX~!@#$%^&*()\".,;:'[]{}-_=+`?/\\|<>увсКАМ№";
}
