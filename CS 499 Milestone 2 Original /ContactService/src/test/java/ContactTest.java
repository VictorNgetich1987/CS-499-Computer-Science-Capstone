import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void testValidContactCreation() {
        Contact contact = new Contact("001", "Victor", "Ngetich", "1234567890", "123 Main St");
        assertEquals("Victor", contact.getFirstName());
        assertEquals("Ngetich", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
        assertEquals("001", contact.getContactId());
    }

    @Test
    public void testInvalidPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("001", "Victor", "Ngetich", "123", "123 Main St");
        });
    }

    @Test
    public void testNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("001", null, "Ngetich", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testLongLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("001", "Victor", "NgetichLastNameTooLong", "1234567890", "123 Main St");
        });
    }
}
