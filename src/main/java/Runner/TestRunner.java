import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Configuration.*;

public class Test {

@BeforeClass
public void setConfiguration() {
    browser = "chrome";
    browserSize
    open("https://rozetka.com.ua/");
}
}
