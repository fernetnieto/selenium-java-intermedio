package clase6.tests;

import clase6.pages.AuthenticationPage;
import clase6.pages.MyAccountPage;
import clase6.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

import static clase6.utilities.Constants.AUTH_PAGE_LOGINBTNHEADERTEXT;
import static clase6.utilities.Constants.MY_ACC_PAGE_LOGOUTBTNTEXT;

public class LoginTest extends BaseTest {
//extiendo con BaseTage porque allí esta la declaracion del driver

    @Test
    public void loginTest() throws InterruptedException {
        //dato a tener en cuenta: en este sitio cuando la cuenta se crea ya queda logueado el usuario
        //asique esa casuistica ya esta cubierta en la parte final de la registracion
        //sin embargo no van a ser las mismas validaciones las de la registracion que las de login

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
        //de ahora en mas estoy en esa pagina, en la de my account page

        //tendriamos que identificar el texto "sign out" para saber que estamos logueados
        //WebElement logOutElement=driver.findElement(By.className("logout"));
        //elemento identificado con FindBy

        //Assert.assertEquals(logOutElement.getText(),"Sign out","No figura el texto Sign out");
        //refactorizo para utilizar en vez de un WebElement una accion de pagina
        //paso a constante los textos que voy viendo
        Assert.assertEquals(myAccountPage.getLogoutBtnText(),MY_ACC_PAGE_LOGOUTBTNTEXT,"No figura el texto Sign out");
        //validamos tambien que aparezca el nombre del usuario logueado
        //WebElement nameElement=driver.findElement(By.xpath("(//*[@href='http://automationpractice.com/index.php?controller=my-account']/span)[1]"));
        //elemento identificado con FindBy
        //System.out.println("-->"+nameElement.getText());
        //refactorizo para utilizar en vez de un WebElement una accion de pagina
        System.out.println("-->"+myAccountPage.getAccountName());

        //acomodo los datos que ya tengo para que coincidan con el formato del campo del usuario logueado
        String fullName= Constants.FAKER_FIRST_NAME+" "+Constants.FAKER_LAST_NAME;
        Assert.assertEquals(myAccountPage.getAccountName(),fullName,"El nombre del usuario logueado no es correcto");
        //o tambien con otros assert
        //Assert.assertTrue(nameElement.getText().contains(fakeFirstName),"El nombre del usuario logueado no es correcto");
        //Assert.assertTrue(nameElement.getText().contains(fakeLastName),"El apellido del usuario logueado no es correcto");
        //hay un montón de otras validaciones que se pueden hacer

        //LOG OUT
        //para cerrar sesion en el usuario logueado
        //logOutElement.click();
        //refactorizo como metodo de pagina
        //myAccountPage.clickOnLogOutBtn();
        //este inclusive se deberia refactorizar como una accion que devuelva una nueva pagina que se abre al hacer click en ese boton
        //no creo nueva clase de pagina, ya está creada la pagina de authentication para ser devuelta al hacer click y redirigir a la proxima pagina
        AuthenticationPage authenticationPage = myAccountPage.clickOnLogOutBtn();
        //de ahora en mas estoy en esa pagina, en la de authentication

        //ubico y valido con assert el boton de Sign in
        //WebElement signInElement=driver.findElement(By.id("SubmitLogin"));
        //ya ubicado con findBy en la pagina correspondiente
        //Assert.assertEquals(loginBtnHeader.getText(),"Sign in","No se encontró el boton de Sing In");
        //refactorizo como metodo de pagina
        //reemplazo texto con constante
        Assert.assertEquals(authenticationPage.getLoginBtnHeaderText(),AUTH_PAGE_LOGINBTNHEADERTEXT,"No se encontró el boton de Sing In");

        //completo usuario/email y pass en la parte de "Ya estas registrado?"
        //WebElement emailAddressLoginElement=driver.findElement(By.id("email"));
        //no se localiza mas desde acá, utilizo Findby en la pagina
        //emailAddressLoginElement.sendKeys(EMAIL_ADDRESS);
        //no puedo usar Constants.EMAIL_ADRESS porque me va a generar un email nuevo
        //lo refactorizo como accion de pagina
        authenticationPage.fillEmailAddressLoginElement(EMAIL_ADDRESS);

        //WebElement passwordElement=driver.findElement(By.id("passwd"));
        //no se localiza mas desde aca, utilizo FindBy en la pagina
        //passwordElement.sendKeys(Constants.PASSWORD);
        //lo refactorizo como accion de pagina
        authenticationPage.fillPasswordElement(Constants.PASSWORD);


        //SIGN IN
        //hago click en el boton de sign in que localice antes
        //loginBtnBody.click();  //reemplazo gracias a FindBy
        //refactorizo como metodo de pagina
        //authenticationPage.clickOnLogInBtn();
        //este inclusive se deberia refactorizar como una accion que devuelva una nueva pagina que se abre al hacer click en ese boton
        //no creo nueva clase de pagina, ya está creada la pagina de MyAccountPage para ser devuelta al hacer click y redirigir a la proxima pagina
        myAccountPage = authenticationPage.clickOnLogInBtn();
        //de ahora en mas estoy en esa pagina, en la de myaccount


        //tendriamos que identificar el texto "sign out" para saber que estamos logueados
        //logOutElement=driver.findElement(By.className("logout"));
        //ya ubicado con FindBy en clase de pagina correspondiente
        //Assert.assertEquals(logOutElement.getText(),"Sign out","No figura el texto Sign out");
        //refactorizo para que me quede como accion de pagina
        Assert.assertEquals(myAccountPage.getLogoutBtnText(),"Sign out","No figura el texto Sign out");
        //validamos tambien que aparezca el nombre del usuario logueado
        //nameElement=driver.findElement(By.xpath("(//*[@href='http://automationpractice.com/index.php?controller=my-account']/span)[1]"));
        //ya ubicado con FindBy en clase de pagina correspondiente
        //System.out.println("-->"+nameElement.getText());
        //refactorizo para que me quede como accion de pagina
        System.out.println("-->"+myAccountPage.getAccountName());

        //Assert.assertEquals(nameElement.getText(),fullName,"El nombre del usuario logueado no es correcto");
        //refactorizo para que me quede como accion de pagina
        Assert.assertEquals(myAccountPage.getAccountName(),fullName,"El nombre del usuario logueado no es correcto");
        //o tambien con otros assert
        //Assert.assertTrue(nameElement.getText().contains(fakeFirstName),"El nombre del usuario logueado no es correcto");
        //Assert.assertTrue(nameElement.getText().contains(fakeLastName),"El apellido del usuario logueado no es correcto");

        //hay un montón de otras validaciones que se pueden hacer
        //Assert.assertEquals(logOutElement.getText(), "Sign out", "El link Signout deberia estar presente");
        //refactorizo para que me quede como accion de pagina
        Assert.assertEquals(myAccountPage.getLogoutBtnText(), MY_ACC_PAGE_LOGOUTBTNTEXT, "El link Signout deberia estar presente");

        //driver.close();
        //ya esta incluido en el afterMethod

    }

}
