package clase6.tests;

import clase6.pages.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    //Inicializo driver y setup
    public WebDriver driver;

    //genero string estático para fines ilustrativos que usaremos mas abajo
    public static String H1_AUTHENTICATION = "AUTHENTICATION";

    //Luego de la estructuración en carpetas (tests y pages)
    //Paso 1 para aplicar Page Object -> dirigirse a la clase base y
    //crear objeto del mismo nombre de la clase de la pagina
    public LandingPage landingPage;


    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.automationpractice.com/index.php");
        //agregamos espera implícita para todos los elementos del driver
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //seteamos la pantalla maximizada
        driver.manage().window().maximize();
        //agrego clausula para utilizar FindBy
        PageFactory.initElements(driver,this);
        //Paso 2 para aplicar Page Object
        //inicializo el Page Object ya declarado previamente de la landing page
        //le doy vida y le paso el driver
        landingPage = new LandingPage(driver);
    }



    //agrego mmetodo para cerrar driver despues de la ejecucion
    @AfterMethod
    public void closeDriver(){
        driver.close();
    }

}
