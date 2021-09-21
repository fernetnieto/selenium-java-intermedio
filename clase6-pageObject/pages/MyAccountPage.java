package clase6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends BasePage {
//creo nueva clase de pagina "MyAccountPage" para ser devuelta al hacer click y redirigir a la proxima pagina
//y operar dentro de ella con metodos propios

    //fundamental para tener dentro de las paginas
    //el WebDriver
    //y un constructor que reciba el driver por parámetro
    public WebDriver driver;

    public MyAccountPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
        //agrego clausula para utilizar FindBy
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath =  "//*[@href = 'http://automationpractice.com/index.php?controller=my-account']")
    public WebElement loginBtnHeader;
    //agrego el boton de logout en my account page
    @FindBy (className = "logout")
    public WebElement logOutElement;
    //agrego el elemento donde se encuentra el nombre del usuario logueado
    @FindBy (xpath = "(//*[@href='http://automationpractice.com/index.php?controller=my-account']/span)[1]")
    public WebElement nameElement;
    //agrego campo utilizado en registrationtest
    @FindBy (xpath = "//*[@href='http://automationpractice.com/index.php?controller=identity']")
    public WebElement myPersonalInformationBtn;

    //agrego metodo para hacer click en el boton de delsogueo
    //public void clickOnLogOutBtn(){
    //    logOutElement.click();
    //}
    //refactorizo este metodo, hago lo mismo pero devuelvo un objeto de pagina de la siguiente pagina
    //devuelve la pagina de authenticationPage que es la siguiente pagina luego de logout en Myaccountpage
    public AuthenticationPage clickOnLogOutBtn(){
        logOutElement.click();
        AuthenticationPage nextPage = new AuthenticationPage(driver);
        return nextPage;
    }

    //agrego metodo para obtener texto del boton de logout
    public String getLogoutBtnText(){
        return logOutElement.getText();
    }

    //agrego metodo para obtener texto del elemento de nombre de usuario logueado
    public String getAccountName(){
        return nameElement.getText();
    }

    //metodo para clickear el nombre del usuario logueado y cambia de pagina
    public YourPersonalInfoPage clickOnMyPersonalInformationBtn(){

        myPersonalInformationBtn.click();
        YourPersonalInfoPage nextPage = new YourPersonalInfoPage(driver);
        return nextPage;

    }

    //agrego metodo para obtener texto del elemento
    public String getLoginBtnHeaderText(){
        return loginBtnHeader.getText();
    }

}
