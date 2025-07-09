package email;

import config.PropertyUtil;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;
import util.DecodeUtil;
import util.FileAttachmentUtil;
import util.LoggingUtil;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class EmailSender {

    private static final String SMTP_HOST = "smtp.standardbank.co.za";
    private static final String SMTP_PORT = "587";
    private static final String USERNAME = "Philimon.Motsinoni@standardbank.co.za";
    private static final String PASSWORD = DecodeUtil.decode64("QE1wMDYwNjI1NTk1NQ==");
    private static final String FROM_EMAIL = "Philimon.Motsinoni@standardbank.co.za";
    private static final String TO_EMAIL = "savelo.khosa@standardbank.co.za";
    private static final String DIR_PATH = "src/main/java/logs";
    private static final int FILES_TO_ATTACH = PropertyUtil.getConfig().numberOfFilesToAttachOnEmail();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");

    public static void main(String[] args) throws Exception {
        sendEmailWithAttachments();
    }

    public static void sendEmailWithAttachments() {
        List<File> latestFiles = FileAttachmentUtil.getLatestFiles(DIR_PATH, FILES_TO_ATTACH);

        try {
            String emailBody = String.format("Hi Phill,\n\nFind the attached test results ran on %s. Below are the attached files.",
                    LocalDate.now());

            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO_EMAIL));
            message.setSubject("Test Results - " + LocalDate.now());

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(emailBody);

            // attachments
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);

            for (File file : latestFiles) {
                // Check if file exists and is readable
                if (file != null && file.canRead()) {
                    try {
                        MimeBodyPart attachmentPart = new MimeBodyPart();
                        DataSource source = new FileDataSource(file);
                        attachmentPart.setDataHandler(new DataHandler(source));

                        multipart.addBodyPart(attachmentPart);

                    } catch (Exception ex) {
                        LoggingUtil.log("EmailSender", "sendEmailWithAttachments", ex.toString());
                    }
                }

                message.setContent(multipart);
                try{
                    Transport.send(message);
                }catch (Exception ex){
                    LoggingUtil.log("EmailSender", "sendEmailWithAttachments", ex.toString());
                }
            }

        } catch (Exception ex) {
            LoggingUtil.log("EmailSender", "sendEmailWithAttachments", ex.toString());
        }
    }

}
