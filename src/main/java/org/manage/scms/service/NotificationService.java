package org.manage.scms.service;

import org.manage.scms.constant.Role;
import org.manage.scms.model.User;
import org.manage.scms.notifications.ProductAddedEvent;
import org.manage.scms.repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    public NotificationService(UserRepository userRepository, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    @EventListener
    public void handleProductAddedEvent(ProductAddedEvent event) throws Exception {
        List<User> users = userRepository.findByRole(Role.USER);

        String message = "New product added: " + event.getProductName()
                 + "\nDetails: " + event.getProductDto();

        for (User user : users) {
            sendNotification(message, user.getEmail(), event);
        }
    }

    private void sendNotification(String message, String toEmail, ProductAddedEvent event) throws Exception {

        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom("rajrohan88293@gmail.com");
            mail.setTo(toEmail);
            mail.setSubject("New Product Added: " + event.getProductName());
            mail.setText(message);

            javaMailSender.send(mail);
        } catch (Exception e) {
            throw new Exception("Failed to send Email to " + toEmail + ": " + e.getMessage());
        }
    }
}
