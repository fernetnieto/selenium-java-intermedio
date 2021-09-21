package clase6.tests;

import clase6.pages.AuthenticationPage;
import clase6.pages.CreateAccountPage;
import clase6.pages.LandingPage;
import clase6.pages.MyAccountPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import clase6.utilities.Constants;
import java.util.List;

public class EcommerceHelper extends BaseTest {
//extiendo tambien de BaseTest y tengo acceso a t odo

    //creo variable para el driver
    public WebDriver driver;

    //creo el constructor
    public EcommerceHelper(WebDriver remoteDriver){
        //utilizo la variable que cree por fuera del constructor
        //se parece a patron PageObject
        driver = remoteDriver;
        //necesito inicializar los elementos con PageFactory también en este helper
        //en realidad solo todas las paginas deberian tener la linea de pagefactory para pder utilizar los webelements que utiliza valga la redundancia la notacion FindBy
        PageFactory.initElements(driver, this);
    }

    //saco de la clase de test este metodo que no es parte de las pruebas
    //la cambio de private a public porque lo vamos a invocar desde la clase de test
    //modifico de void a MyAccountPage xq retorna finalmente donde estoy parado con un elemento de tipo pagina, en este caso myAccountPage
    public MyAccountPage completeRegistration(String email) throws InterruptedException {
    //cambio la firma del metodo para agregar el parametro del email que va allegar de todos los otros metodos que lo requieran

        //driver.findElement(By.xpath("//*[@href = 'http://automationpractice.com/index.php?controller=my-account']")).click();
        //loginBtnHeader.click();  //reemplazo gracias a FindBy
        //pasado a Page Object sería
        //para esto tuve que crear un metodo en la clase LandingPage para enmascarar ese loginBtnHeader.click()
        //xq en definitiva es una accion que es hacer click que se hace dentro de la landing
        //y de esta manera realice la transicion entre pagina de landing y authentication

        //no defino, sino que solo instancio el driver nuevamente xq no lo esta tomando en la ejecucion
        //xq EcommerceHelper no es un metodo de test entonces la declaracion del driver en la landingPage en el @BeforeMethod no hace efecto en este metodo que no tiene la notacion @test
        landingPage = new LandingPage(driver);

        AuthenticationPage authenticationPage = landingPage.loginBtnClick();

        //Checking the account is created...
        //Assert.assertEquals(driver.getTitle(),"Login - My Store","Se esperaba otro titlo!!");
        //reemplazo para eliminar el driver y queda como accion de pagina
        //Assert.assertEquals(authenticationPage.getPageTitle(),"Login - My Store","Se esperaba otro titlo!!");
        //reemplazo una constante a modo ejemplo pero habria q hacerlo con todos los strings que queden sueltos
        Assert.assertEquals(authenticationPage.getPageTitle(),Constants.AUTH_PAGE_TITLE,"Se esperaba otro titlo!!");
        //Assert.assertEquals(driver.getCurrentUrl(),"http://automationpractice.com/index.php?controller=authentication&back=my-account", "Se esperaba otra URL!!");
        //reemplazo para eliminar el driver y queda como accion de pagina
        //Assert.assertEquals(authenticationPage.getPageURL(),"http://automationpractice.com/index.php?controller=authentication&back=my-account", "Se esperaba otra URL!!");
        //reemplazo una constante a modo ejemplo pero habria q hacerlo con todos los strings que queden sueltos
        Assert.assertEquals(authenticationPage.getPageURL(),Constants.AUTH_PAGE_URL, "Se esperaba otra URL!!");
        //Assert.assertTrue(driver.getCurrentUrl().contains("authentication"));
        //reemplazo para eliminar el driver y queda como accion de pagina
        Assert.assertTrue(authenticationPage.getPageURL().contains("authentication"));
        //esto funciona pero podria refactoriarse para utilizar una lista
        //WebElement h1Element = driver.findElement(By.tagName("h1"));
        //Assert.assertEquals(h1Element.getText(),H1_AUTHENTICATION,"Se esperaba el h1: "+H1_AUTHENTICATION);
        //Assert.assertEquals(autenticationH1.getText(),H1_AUTHENTICATION,"Se esperaba el h1: "+H1_AUTHENTICATION);
        //reemplazo gracias a FindBy
        //reemplazo para eliminar el driver y queda como accion de pagina
        Assert.assertEquals(authenticationPage.getAutenticationH1Text(),H1_AUTHENTICATION,"Se esperaba el h1: "+H1_AUTHENTICATION);
        //con bandera booleana y assert refactorizado como List
        boolean encontroElemento = false;
        List<WebElement> elementoList = driver.findElements(By.tagName("h1"));  //pendiente ver como transformar a accion de pagina sin utilizar el driver directamente
        for (WebElement e: elementoList){
            if (e.getText().equals(H1_AUTHENTICATION)){
                encontroElemento = true;
                break;  //corta la ejecución del for para no tener que repetir de más
            }
        }
        Assert.assertTrue(encontroElemento);

        //completo el campo del email y submito
        //driver.findElement(By.name("email_create")).sendKeys(EMAIL_ADDRESS);
        //emailNewAccountField.sendKeys(email);  //reemplazo gracias a FindBy
        //reemplazo para eliminar el driver y queda como accion de pagina
        authenticationPage.fillEmailAddress(email);

        //driver.findElement(By.id("SubmitCreate")).click();
        //createAccountBtn.click();  //reemplazo gracias a FindBy
        //reemplazo para eliminar el driver y queda como accion de pagina
        //authenticationPage.clickOnCreateAccountBtn();
        //este inclusive se deberia refactorizar como una accion que devuelva una nueva pagina que se abre al hacer click en ese boton
        //creo nueva clase de pagina "CreateAccountPage" para ser devuelta al hacer click y redirigir a la proxima pagina
        CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccountBtn();
        //de ahora en mas estoy en esa pagina, en la de create account

        //para evitar errores
        Thread.sleep(4000);

        //en el String populatedEmail guardaré el valor del elemento email con getAttribute
        //valido lo que ingresé en el input contra lo que tengo en el campo de email

        //WebElement emailElement = driver.findElement(By.id("email"));
        //String populatedEmail = emailUserField.getAttribute("value");
        //reemplazo gracias a FindBy
        //ubicado dentro de los metodos de pagina
        String populatedEmail=createAccountPage.getEmailValue();

        System.out.println("-----> populatedEmail: "+populatedEmail);
        Assert.assertEquals(populatedEmail, email, "Los emails deberian de coincidir!!!");

        //t odo el llenado de campos esta como un metodo de pagina de create account page
        createAccountPage.fillRegistrationFields();


        //clicking on register button
        //registerButton.click();
        //pasado a metodo en create account page
        //createAccountPage.clickOnRegisterAcountBtn();
        //este inclusive se deberia refactorizar como una accion que devuelva una nueva pagina que se abre al hacer click en ese boton
        //creo nueva clase de pagina "MyAccountPage" para ser devuelta al hacer click y redirigir a la proxima pagina
        MyAccountPage myAccountPage = createAccountPage.clickOnRegisterAccountBtn();
        //de ahora en mas estoy en esa pagina, en la de my account

        //recordar que esto no es un metodo de test
        //por ende antes de finalizar ejecucion de este helper voy a retornar donde quede parado
        return myAccountPage;
        //modifico la firma de la funcion para que no sea void pues retorna un elemento al final

    }

}
