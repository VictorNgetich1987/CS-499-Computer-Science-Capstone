import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
public class ContactServiceTest {

    @Test
    public void testAddContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("001", "Victor", "Ngetich", "1234567890", "123 Main St");
        service.addContact(contact);
        assertEquals("Victor", service.getContact("001").getFirstName());
    }

    @Test
    public void testDeleteContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("002", "John", "Smith", "0987654321", "456 Elm St");
        service.addContact(contact);
        service.deleteContact("002");
        assertThrows(IllegalArgumentException.class, () -> service.getContact("002"));
    }

    @Test
    public void testUpdatePhone() {
        ContactService service = new ContactService();
        Contact contact = new Contact("003", "Ana", "Lee", "1112223333", "789 Oak St");
        service.addContact(contact);
        service.updatePhone("003", "9998887777");
        assertEquals("9998887777", service.getContact("003").getPhone());
    }

    @Test
    public void testDuplicateIdThrowsError() {
        ContactService service = new ContactService();
        Contact contact1 = new Contact("004", "Mark", "Brown", "1231231234", "12 Street");
        Contact contact2 = new Contact("004", "Sara", "Green", "4564564567", "34 Avenue");
        service.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2));
    }
}
