package com.management.lead.leadMangement.dtotesting;

import com.management.lead.leadmangement.dto.MessageResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageResponseTest {

    @Test
    void testGetterAndSetterMethods() {
        String message = "Test Message";
        MessageResponse messageResponse = new MessageResponse(message);

        // Test getter
        assertEquals(message, messageResponse.getMessage());

        // Test setter
        String newMessage = "New Message";
        messageResponse.setMessage(newMessage);
        assertEquals(newMessage, messageResponse.getMessage());
    }
}
