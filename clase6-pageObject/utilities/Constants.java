package clase6.utilities;

import com.github.javafaker.Faker;

public class Constants {


    public static final String PASSWORD = "helloworld";
    public static final String BIRTH_DAY = "3";
    public static final String BIRTH_MONTH = "10";
    public static final String BIRTH_YEAR = "1990";
    public static final String CITY = "New York";

    //agrego la clase Faker y las constantes que trabajan con Faker para datos aleatorios
    private static Faker FAKER = new Faker();
    public static String FAKER_FIRST_NAME = FAKER.name().firstName();
    public static String FAKER_LAST_NAME = FAKER.name().lastName();

    //creo la variable email estática vacía
    public static String EMAIL_ADDRESS = "";

    //el problema que existia es que cada vez que se invocaba a la variable
    //se me generaba un nuevo email, entonces siempre el email era distinto
    //de esta forma si está vacia la variable genero un nuevo mail. Si no esta vacia no genero nuevo mail
    //simplemente lo retorno. De esta formo logro que el email no se actualice
    public static String getEmailAddress() {

        if (EMAIL_ADDRESS.isEmpty()) {
            EMAIL_ADDRESS = "seleniumintermedio" + Math.random() + "test@gmail.com";
        }
        return EMAIL_ADDRESS;
    }

    //continuo agregando constantes y paso esta clase a la carpte de utilities
    //final para que no se pueda modificar
    public static final String AUTH_PAGE_TITLE = "Login - My Store";
    public static final String AUTH_PAGE_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    public static final String MY_ACC_PAGE_LOGOUTBTNTEXT= "Sign out";
    public static final String AUTH_PAGE_LOGINBTNHEADERTEXT= "Sign in";
    public static final String CREATE_ACCOUNT_PAGE_COMPANY  = "MyCompany";
    public static final String CREATE_ACCOUNT_PAGE_ADDRESS1 = "My address Nr1";
    public static final String CREATE_ACCOUNT_PAGE_ADDRESS2 = "My address Nr2";
    public static final String CREATE_ACCOUNT_PAGE_ADDITIONALINFO = "Additional information";
    public static final String CREATE_ACCOUNT_PAGE_ADDRESSALIAS = "MyAlias";
    public static final String YOUR_PERSONAL_INFO_PAGE_MYPERSONALINFOELEMENT = "YOUR PERSONAL INFORMATION";

}