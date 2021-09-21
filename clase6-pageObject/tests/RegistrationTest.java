package clase6.tests;

import clase6.pages.MyAccountPage;
import clase6.pages.YourPersonalInfoPage;
import clase6.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {
//extiendo con BaseTest porque allí esta la declaracion del driver

    @Test
    public void registegrationTest() throws InterruptedException {

        String EMAIL_ADDRESS = "seleniumintermedio" + Math.random() + "test@gmail.com";
        //mail necesario para las pruebas que sera pasado por parametro al metodo completeRegistration

        //llamo al metodo que contiene la automatizacion para generar la registracion
        //completeRegistration();
        //ya no llamo mas al metodo, debo crear un objeto e instanciar el metodo
        //y le paso el driver
        EcommerceHelper helper=new EcommerceHelper(driver);
        //helper.completeRegistration(EMAIL_ADDRESS);
        //lo pongo dentro de un objeto de tipo MyAccountPage porque refactorizamos
        //el metodo completeRegistration para que al final de la ejecucion retorne un objeto pagina, en este caso de tipo MyAccountPage
        MyAccountPage myAccountPage = helper.completeRegistration(EMAIL_ADDRESS);

        //localizo el elemento donde voy a validar que la cuenta se haya creado y le hago click
        //WebElement myPersonalInformationBtn=driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?controller=identity']"));
        //ya localizado con FindBy en clase de pagina correspondiente
        //myPersonalInformationBtn.click();
        //refactorizo para convertirlo en accion de pagina
        //myAccountPage.clickOnMyPersonalInformationBtn();
        //igualmente tengo que seguir refactorizando porque el click me lleva a ooootra paginola
        YourPersonalInfoPage yourPersonalInfoPage = myAccountPage.clickOnMyPersonalInformationBtn();

        //valido que estoy en la pagina correcta, busco por h1 porque se que hay un solo elemento h1
        //WebElement myPersonalInformationElement=driver.findElement(By.tagName("h1"));
        //ya no se utiliza, esta con FindBy en la pagina correspondiente
        //Assert.assertEquals(myPersonalInformationElement.getText(),"YOUR PERSONAL INFORMATION","El sitio no es el correcto");
        //refactorizo para dejarlo como accion de pagina
        Assert.assertEquals(yourPersonalInfoPage.getMyPersonalInformationElementText(),Constants.YOUR_PERSONAL_INFO_PAGE_MYPERSONALINFOELEMENT,"El sitio no es el correcto");

        //valido otros elementos de la pagina
        //WebElement firstNameElement=driver.findElement(By.id("firstname"));
        //refactorizo con FindBy en pagina correspondiente
        //String actualFirstName=firstNameElement.getAttribute("value");  //getAttribute para que me traiga el valor de algun atributo del elemento, en este caso "value"
        //refactorizo para que me quede como accion de pagina
        String actualFirstName=yourPersonalInfoPage.getFirstNameElementValue();

        //WebElement lastNameElement=driver.findElement(By.id("lastname"));
        //refactorizo con FindBy en pagina correspondiente
        //String actualLastName=lastNameElement.getAttribute("value");  //getAttribute para que me traiga el valor de algun atributo del elemento, en este caso "value"
        //refactorizo para que me quede como accion de pagina
        String actualLastName=yourPersonalInfoPage.getLastNameElementValue();

        Assert.assertEquals(actualFirstName, Constants.FAKER_FIRST_NAME,"Nombre no correcto");
        Assert.assertEquals(actualLastName, Constants.FAKER_LAST_NAME,"Apellido no correcto");

        //driver.close();
        //ya está en @afterMethod

    }

}
