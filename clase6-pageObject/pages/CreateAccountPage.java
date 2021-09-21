package clase6.pages;

import clase6.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static clase6.utilities.Constants.*;

public class CreateAccountPage extends BasePage {
//creo nueva clase de pagina "CreateAccountPage" para ser devuelta al hacer click y redirigir a la proxima pagina
//y operar dentro de ella con metodos propios

    //fundamental para tener dentro de las paginas
    //el WebDriver
    //y un constructor que reciba el driver por parámetro
    public WebDriver driver;

    public CreateAccountPage(WebDriver remoteDriver) {
        this.driver = remoteDriver;
        //agrego clausula para inicializar los WebElements que usan la notacion FindBy
        PageFactory.initElements(driver, this);
    }

    //muevo todos los findby hacia la pagina especifica correspondiente, quitandolos de BasePage
    @FindBy(id = "email")
    public WebElement emailUserField;
    @FindBy (id = "id_gender1")
    public WebElement maleGenderRadioButton;
    @FindBy (id ="customer_firstname")
    public WebElement firstNameField;
    @FindBy (id ="customer_lastname")
    public WebElement lastNameField;
    @FindBy (id = "passwd")
    public WebElement passwdField;
    //agrego el boton de register en create account page
    @FindBy (id = "submitAccount")
    public WebElement registerButton;
    //agrego las ultimas modificaciones pra incluir las ubicaciones de elementos con FindBy
    @FindBy (id = "days")
    public WebElement daySelector;
    @FindBy (id = "months")
    public WebElement monthSelector;
    @FindBy (id = "years")
    public WebElement yearSelector;
    @FindBy (id = "company")
    public WebElement company;
    @FindBy (id = "address1")
    public WebElement address1;
    @FindBy (id = "address2")
    public WebElement address2;
    @FindBy (id = "city")
    public WebElement city;
    @FindBy (id = "id_state")
    public WebElement stateSelector;
    @FindBy (id = "postcode")
    public WebElement postcode;
    @FindBy (id = "id_country")
    public WebElement countrySelector;
    @FindBy (id = "other")
    public WebElement additionalInfo;
    @FindBy (id = "phone")
    public WebElement phone;
    @FindBy (id = "phone_mobile")
    public WebElement mobile;
    @FindBy (id = "alias")
    public WebElement addressAlias;

    //agrego metodo de pagina para la accion de retornar valor de email
    public String getEmailValue(){
        return emailUserField.getAttribute("value");
    }

    //método para completar todos los campos en pagina
    public void fillRegistrationFields(){

        //resta completar los campos
        //WebElement maleRadioButton = driver.findElement(By.id("id_gender1"));
        //reemplazo gracias a FindBy

        //WebElement firstName = driver.findElement(By.id("customer_firstname"));
        //WebElement lastName = driver.findElement(By.id("customer_lastname"));
        //WebElement password = driver.findElement(By.id("passwd"));
        //ya las localicé con FindBy, no hago todos a fines practicos
        //WebElement daySelector = driver.findElement(By.id("days"));
        //WebElement monthSelector = driver.findElement(By.id("months"));
        //WebElement yearSelector = driver.findElement(By.id("years"));
        //WebElement company = driver.findElement(By.id("company"));
        //WebElement address1 = driver.findElement(By.id("address1"));
        //WebElement address2 = driver.findElement(By.id("address2"));
        //WebElement city = driver.findElement(By.id("city"));
        //WebElement stateSelector = driver.findElement(By.id("id_state"));
        //WebElement postcode = driver.findElement(By.id("postcode"));
        //WebElement countrySelector = driver.findElement(By.id("id_country"));
        //WebElement additionalInfo = driver.findElement(By.id("other"));
        //WebElement phone = driver.findElement(By.id("phone"));
        //WebElement mobile = driver.findElement(By.id("phone_mobile"));
        //WebElement addressAlias = driver.findElement(By.id("alias"));
        //ya toooodo localizado con FindBY
        //WebElement registerButton = driver.findElement(By.id("submitAccount"));  localizada con FindBy


        maleGenderRadioButton.click();

        //reemplazazoo gracias a FIndBy
        firstNameField.sendKeys(Constants.FAKER_FIRST_NAME);
        lastNameField.sendKeys(Constants.FAKER_LAST_NAME);
        passwdField.sendKeys(Constants.PASSWORD);

        Select selectDays = new Select(daySelector);
        selectDays.selectByValue(Constants.BIRTH_DAY);
        Select selectMonths = new Select(monthSelector);
        selectMonths.selectByValue(Constants.BIRTH_MONTH);
        Select selectYears = new Select(yearSelector);
        selectYears.selectByValue(Constants.BIRTH_YEAR);
        company.sendKeys(CREATE_ACCOUNT_PAGE_COMPANY);
        address1.sendKeys(CREATE_ACCOUNT_PAGE_ADDRESS1);
        address2.sendKeys(CREATE_ACCOUNT_PAGE_ADDRESS2);
        city.sendKeys(Constants.CITY);
        Select selectState = new Select(stateSelector);
        selectState.selectByValue("2");
        postcode.sendKeys("10000");
        Select selectCountry = new Select(countrySelector);
        selectCountry.selectByValue("21");
        additionalInfo.sendKeys(CREATE_ACCOUNT_PAGE_ADDITIONALINFO);
        phone.sendKeys("123456");
        mobile.sendKeys("123456789");
        addressAlias.clear();
        addressAlias.sendKeys(CREATE_ACCOUNT_PAGE_ADDRESSALIAS);

    }

    //devuelve la pagina de myaccount que es la siguiente pagina luego de darle click al boton en la createaccountpage
    public MyAccountPage clickOnRegisterAccountBtn(){
        registerButton.click();
        MyAccountPage nextPage = new MyAccountPage(driver);
        return nextPage;
    }

}
