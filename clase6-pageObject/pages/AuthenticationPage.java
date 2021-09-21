package clase6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage extends BasePage {

    //fundamental para tener dentro de las paginas
    //el WebDriver
    //y un constructor que reciba el driver por parámetro
    public WebDriver driver;

    public AuthenticationPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
        //agrego clausula para unicializar los elementos que usan la notacion FindBy
        PageFactory.initElements(driver,this);
    }

    @FindBy(tagName = "h1")
    public WebElement autenticationH1;
    @FindBy (name = "email_create")
    public WebElement emailNewAccountField;
    //agrego el otro campo de email que no es el mismo que el de arriba
    @FindBy (id = "email")
    public WebElement emailAddressLoginElement;
    @FindBy (id = "passwd")
    public WebElement passwordElement;
    @FindBy (id = "SubmitCreate")
    public WebElement createAccountBtn;
    @FindBy(xpath =  "//*[@id = 'SubmitLogin']")
    public WebElement loginBtnBody;
    @FindBy(xpath =  "//*[@href = 'http://automationpractice.com/index.php?controller=my-account']")
    public WebElement loginBtnHeader;

    //agrego los demas metodos que me voy encontrando en los tests
    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getPageURL(){
        return driver.getCurrentUrl();
    }

    public String getAutenticationH1Text(){
        return autenticationH1.getText();
    }


    public void fillEmailAddress(String anEmail){
        emailNewAccountField.sendKeys(anEmail);
    }

    //agrego metodo para el otro campo email en pagina
    public void fillEmailAddressLoginElement(String anEmail){

        emailAddressLoginElement.sendKeys(anEmail);

    }

    public void fillPasswordElement(String aPasswd) {
        passwordElement.sendKeys(aPasswd);
    }

    //public void clickOnCreateAccountBtn(){
    //    createAccountBtn.click();
    //}
    //refactorizo este metodo, hago lo mismo pero devuelvo un objeto de pagina de la siguiente pagina
    //devuelve la pagina de createaccount que es la siguiente pagina luego de darle click al boton en la authentication
    public CreateAccountPage clickOnCreateAccountBtn(){
        createAccountBtn.click();
        CreateAccountPage nextPage = new CreateAccountPage(driver);
        return nextPage;
    }

    //public void clickOnLogInBtn(){
    //    loginBtnBody.click();
    //}
    //refactorizo este metodo, hago lo mismo pero devuelvo un objeto de pagina de la siguiente pagina
    //devuelve la pagina de myaccount que es la siguiente pagina luego de darle click al boton en la authentication
    public MyAccountPage clickOnLogInBtn(){
        loginBtnBody.click();
        MyAccountPage nextPage = new MyAccountPage(driver);
        return nextPage;
    }

    //agrego metodo para obtener texto del elemento
    public String getLoginBtnHeaderText(){
        return loginBtnHeader.getText();
    }

}
