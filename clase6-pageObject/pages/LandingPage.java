package clase6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    //fundamental para tener dentro de las paginas
    //el WebDriver
    //y un constructor que reciba el driver por parámetro
    public WebDriver driver;

    public LandingPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
        //agrego clausula para unicializar los elementos que usan la notacion FindBy
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath =  "//*[@href = 'http://automationpractice.com/index.php?controller=my-account']")
    public WebElement loginBtnHeader;

    //devuelve la pagina de autenticacion que es la siguiente pagina luego de darle click al boton en la landing
    public AuthenticationPage loginBtnClick(){
        loginBtnHeader.click();
        AuthenticationPage nextPage = new AuthenticationPage(driver);
        return nextPage;
    }

}
