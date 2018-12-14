import org.junit.jupiter.api.Test;
import tourSaleManagementSystemUtil.FormatConverter;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class FormatConverterTest {

    @Test
    void testGetLocalDateFormatMethod() {
        // input pattern
        assertEquals((LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))), FormatConverter.getLocalDateFormat("dd-MM-yyyy"));
        assertEquals((LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))), FormatConverter.getLocalDateFormat("dd/MM/yyyy"));
    }

    @Test
    void testGenerateReservationCodeMethod() {
        assertEquals("JPN-4D3N-000001-000001",FormatConverter.generateReservationCode("JPN-4D3N-000001"));
        assertEquals("GER-6D8N-000001-000001", FormatConverter.generateReservationCode("GER-6D8N-000001"));
    }

    @Test
    void testGenerateCustomerIDMethod() {
        assertEquals("CS00000021",FormatConverter.generateCustomerID());
    }

}