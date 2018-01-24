package vtbStand;


/**
 * Опять же пользователей может быть много, не нужно тестовые данные забивать в интерфейсы
 */
public enum ClientData {
	BILALOVA_L_R ("1111111111", "1111111111");
	
	private final String username;
	private final String password;
	
	ClientData(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String password() {	return password;    }
	public String username() {	return username;	}
	
}
