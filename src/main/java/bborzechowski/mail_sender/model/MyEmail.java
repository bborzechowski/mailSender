package bborzechowski.mail_sender.model;

import lombok.Data;

@Data
public class MyEmail {

    private String address;
    private String subject;
    private String body;
}
