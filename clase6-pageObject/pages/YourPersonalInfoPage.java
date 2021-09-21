package clase6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourPersonalInfoPage {
//creo nueva clase de pagina "YourPersonalInfo" para ser devuelta al hacer click y redirigir a la proxima pagina
//y operar dentro de ella con metodos propios

    //fundamental para tener dentro de las paginas
    //el WebDriver
    //y un constructor que reciba el driver por parámetro
    public WebDriver driver;

    public YourPersonalInfoPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
        //agrego clausula para utilizar los WebElements que utilizan FindBy
        PageFactory.initElements(driver,this);
    }

    //agrego campo utilizado en registrationtest
    @FindBy(tagName = "h1")
    public WebElement myPersonalInformationElement;
    @FindBy (id = "firstname")
    public WebElement firstNameElement;
    @FindBy (id = "lastname")
    public WebElement lastNameElement;


    //agrego metodo para obtener texto del elemento
    public String getMyPersonalInformationElementText(){
        return myPersonalInformationElement.getText();
    }

    public String getFirstNameElementValue(){

        return firstNameElement.getAttribute("value");

    }

    public String getLastNameElementValue(){

        return lastNameElement.getAttribute("value");

    }



}
