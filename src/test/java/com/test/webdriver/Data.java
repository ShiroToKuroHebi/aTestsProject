package com.test.webdriver;

/** Just general stuff
 */
public interface Data {
	
    // Bank Data
    String FONSERVICEBANK_BAIKONUR_BIC = "040037470";
    
    // Random Data
	String NUMBERS_5x0 = "00000";
	String NUMBERS_9x0 = "000000000";
	String NO_DIGITS_STRING = "qazWSX~!@#$%^&*()\".,;:'[]{}-_=+`?/\\|<>увсКАМ№";
 
	// Control messages
	String BIC_UNKNOWN = "Указанный БИК не найден в справочнике российских банков, выполнение проверки ключа счета не доступно";
	String BIC_TOO_SHORT = "БИК должен состоять из 9 цифр";
}
