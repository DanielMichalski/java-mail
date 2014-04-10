public class MailTest {
    public static void main(String[] args) {
        MailService mailService = new MailService();

        String from = "sender";
        String to = "receipment";
        String subcject = "subject";
        String text = "message";

        boolean czyWysłany = mailService.sendEMail(from, to, subcject, text);

        System.out.println("Czy wysłano e-mail? " + czyWysłany);

    }
}