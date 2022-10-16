import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void shouldFillOutForm() {
        String date = generateDate(3);
        open("http://0.0.0.0:7777/");
        ElementsCollection items = $$(".tab-item");
        $("[data-test-id=city] input").setValue("Иваново");
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Иванов  Иван");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").shouldHave(matchText("Успешно!"), Duration.ofSeconds(15));
    }
}
