package service;

import dao.ContactFormRepository;
import dao.QuestionAnswerRepository;
import model.ContactForm;
import org.springframework.stereotype.Service;

@Service
public class ContactFormService {
    private final ContactFormRepository repository;

    public ContactFormService(ContactFormRepository repository) {
        this.repository = repository;
    }

    public void save(ContactForm contactForm) {
        ContactForm msg = new ContactForm();
        msg.setName(contactForm.getName());
        msg.setEmail(contactForm.getEmail());
        msg.setMessage(contactForm.getMessage());
        repository.save(msg);
    }
}
