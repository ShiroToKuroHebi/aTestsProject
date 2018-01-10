package com.test.webdriver;

public interface Data {
    // Authentication
    String CLIENT_LOGIN = "1111111111";
    String CLIENT_PSWRD = "1111111111";
	
    // Bank Data
    String FONSERVICEBANK_BAIKONUR_BIC = "040037470";
    
    // Random Data
	String NUMBERS_5x0 = "00000";
	String NUMBERS_9x0 = "000000000";
	String NO_DIGITS_STRING = "qazWSX~!@#$%^&*()\".,;:'[]{}-_=+`?/\\|<>увсКАМ№";
 
	// Control messages
	String BIC_UNKNOWN = "Указанный БИК не найден в справочнике российских банков, выполнение проверки ключа счета не доступно";
}
