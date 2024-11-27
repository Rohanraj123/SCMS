package mail_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.manage.scms.constant.Role;
import org.manage.scms.dto.ProductDto;
import org.manage.scms.model.User;
import org.manage.scms.notifications.ProductAddedEvent;
import org.manage.scms.repository.UserRepository;
import org.manage.scms.service.NotificationService;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailSenderTest
{
    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JavaMailSender javaMailSender;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> mailCaptor;

    @Test
    public void TestHandleProductAddedEvent() throws Exception
    {
        Set<Role> roles = Collections.singleton(Role.USER);
        User user1 = new User(1L, "Rohan Raj", "2398", "rohanbusi123@gmail.com", roles);
        User user2 = new User(2L, "Shivam Kumar", "sdjkfk", "rajrohan88293@gmail.com", roles);

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findByRole(Role.USER)).thenReturn(users);

        String name = "Lenovo";
        String description = "Lenovo ideapad 3";
        Integer quantity = 2;
        BigDecimal price = BigDecimal.valueOf(232213);
        String supplier = "Rohan Raj";

        ProductDto productDto = new ProductDto(name, description, quantity, price, supplier);

        ProductAddedEvent productAddedEvent = new ProductAddedEvent(this, "Lenovo Laptop", productDto);

        notificationService.handleProductAddedEvent(productAddedEvent);

        verify(javaMailSender, times(2)).send(mailCaptor.capture());
        List<SimpleMailMessage> sentMessages = mailCaptor.getAllValues();

        assertEquals(2, sentMessages.size());
        assertEquals("rohanbusi123@gmail.com", Objects.requireNonNull(sentMessages.get(0).getTo())[0]);
        assertEquals("rajrohan88293@gmail.com", Objects.requireNonNull(sentMessages.get(1).getTo())[0]);
        assertEquals("New Product Added: Lenovo Laptop", sentMessages.get(0).getSubject());
    }

    @Test
    public void testSendEmail()
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText("This is a test email");
        mailMessage.setFrom("rajrohan88293@gmail.com");
        mailMessage.setTo("rohanbusi123@gmail.com");
        mailMessage.setSubject("Test Email");

        try {
            javaMailSender.send(mailMessage);
            System.out.println("Email sent successfully");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Email sending failed: " + e.getMessage());
        }
    }
}
