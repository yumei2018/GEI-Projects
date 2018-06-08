
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;

public class EmailTest {

  @Test
  public void t1() throws Exception {
    HtmlEmail email = new HtmlEmail();
    email.setHostName("geiconsultants-com.mail.protection.outlook.com");
    email.addTo("aleshchuk@geiconsultants.com");
    email.setSubject("test");
    email.setDebug(true);
//    email.setSSLOnConnect(true);
    email.setSendPartial(true);
    email.setFrom("geideveloper@geiconsultants.com");
    email.setHtmlMsg("<h1>Hello, world!</h1>");
    email.send();
  }
}
