package bborzechowski.mail_sender.service;

import bborzechowski.mail_sender.model.MyEmail;

public interface EmailSender {

    MyEmail sendEmail(MyEmail myEmail);
}
