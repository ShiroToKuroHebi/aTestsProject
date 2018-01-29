package vtbStand.tests;

import org.junit.Before;
import org.junit.Test;
import vtbStand.pages.*;

import java.io.IOException;


public class Msg4BankFieldTests extends Tests{
	private static LogInPage login;
	private static MainPage main;
	private static NewRPForm_MT newRPForm_MT;
	private static Msg4BankForm msg4BankForm;
	
	
	@Before
	public void setUp() throws IOException {
		super.setUp();
		
		login = Page.logInPage();
		//		login.typeUsername(ClientData.BILALOVA_L_R.getPassword());
		//		login.typePassword(ClientData.BILALOVA_L_R.getUsername());
		
		main = login.clickLoginButton();
		logger.info("Logged in as default user");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		newRPForm_MT.showMsg4BankBlock();
		
		msg4BankForm = newRPForm_MT.openMsg4BankDictionary();
	}
	
	
	
	
	/** Проверить заполнение полей по умолчанию
	 *  Ожидается:
	 *  Код - "D1"; имеет расшифровку (?..). Тип документа "Платежное поручение". Сообщение - не заполнено.
	 */
	@Test
	public void vtbdbotlab5357_01() {
	
		Msg4BankForm msg4BankForm = new Msg4BankForm();
		
		
	}
	
	/** Заполнить все поля (Код, Тип документа(?..), Сообщение, < 255 символов) корректно, нажать "Сохранить".
	 *  Ожидается:
	 *  Новая запись добавлена в Справочник
	 *  (+ добавить проверку создания новой записи)
	 *  (+ добавить удаление записи)
	 */
	@Test
	public void vtbdbotlab5357_02() {
	
	}
	
	/** Заполнить поле "Сообщение" длиной = 255 символов, нажать "Сохранить"
	 *  Ожидается:
	 *  Новая запись добавлена в Справочник
	 *  (+ добавить проверку создания новой записи)
	 *  (+ добавить удаление записи)
	 */
	@Test
	public void vtbdbotlab5357_03() {
	
	}
	
	/** Заполнить поле "Сообщение" длиной > 255 символов, нажать "Сохранить"
	 *  Ожидается:
	 *  Не удаётся ввести >255 символов
	 *  (но запись-то сохранится?)
	 *  (* отбросить сохранение записи)
	 */
	@Test
	public void vtbdbotlab5357_04() {
	
	}
	
	/** Изменить значение поля "Тип сообщения"
	 *  Ожидается:
	 *  Изменить значение по умолчанию в пилотной версии невозможно (окай)
	 */
	@Test
	public void vtbdbotlab5357_05() {
	
	}
	
	/** Заполнить поле "Сообщение" НЕдопустимыми символами (Допустимые символы: символы с кодами 32-126, А-Я а-я, №.),
	 *  длиной < 255 символов, нажать "Сохранить"
	 *  Ожидается:
	 *  Символы ввести нельзя
	 */
	@Test
	public void vtbdbotlab5357_06() {
	
	}
	
	/** Заполнить поле "Код" вручную, нажать "Сохранить"
	 *  Ожидается:
	 *  Заполнить вручную невозможно
	 */
	@Test
	public void vtbdbotlab5357_07() {
	
	}
	
	/** Не заполнять поле "Сообщение", нажать "Сохранить"
	 *  Ожидается:
	 *  Новыя запись НЕ добавлена в Справочник.
	 *  Ошибка "Поле сообщение обязательно для заполнения"
	 */
	@Test
	public void vtbdbotlab5357_08() {
	
	}
	
}
