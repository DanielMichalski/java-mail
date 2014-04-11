import service.MailService;

public class MailTest {
    public static void main(String[] args) {
        String from = "sender";
        String to = "receipment";
        String subcject = "subject";
        String text = "message";

        MailService mailService = new MailService(from, to);

        boolean czyWysłany = mailService.sendEMail(subcject, text);

        System.out.println("Czy wysłano e-mail? " + czyWysłany);

    }
}