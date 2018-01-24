package vtbStand;


/*
TODO Почему для хранения данных исплользовал интерфейс, для тестов мы можем использовать большое количество банков, то сделай Enum.
 */

public interface BankData {

	// -==| Bank Data |==-
    // -|БАЙКОНУРСКИЙ ФИЛИАЛ АО "ФОНДСЕРВИСБАНК"|-
    dictRowBIC FONDSERVICEBANK = new dictRowBIC(
		    "040037470",
		    "БАЙКОНУРСКИЙ ФИЛИАЛ АО \"ФОНДСЕРВИСБАНК\"",
		    "30101.810.3.00000000470",
		    "БАЙКОНУР"
    );
    
    // -==| Random Data |==-
	String NUMBERS_5x0 = "00000";
	String NUMBERS_9x0 = "000000000";
	String NO_DIGITS_STRING = "qazWSX~!@#$%^&*()\".,;:'[]{}-_=+`?/\\|<>увсКАМ№";
}
