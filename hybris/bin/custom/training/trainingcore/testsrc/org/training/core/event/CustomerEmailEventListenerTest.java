package org.training.core.event;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

@IntegrationTest
public class CustomerEmailEventListenerTest extends ServicelayerTransactionalTest {

    private static final String TEST_EMAIL = "testcustomer@example.com";
    private static final String TEST_MESSAGE = "This is a test notification.";
    private static final String SENDER_EMAIL = "noreply@example.com";
    private static final String REPLY_TO_ADDRESS = "support@example.com";
    private static final String SUBJECT = "Notification";

    private static final String EMAIL_MESSAGE_QUERY = "SELECT {pk} FROM {EmailMessage} WHERE {subject} = ?subject";
    @Resource
    private EventService eventService;
    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Test
    public void testCustomerEmailEventProcessing() {
        CustomerEmailEvent event = new CustomerEmailEvent(TEST_EMAIL, TEST_MESSAGE);
        eventService.publishEvent(event);

        waitForEventProcessing();

        List<EmailMessageModel> emails = getAllEmails();
        assertEquals(1, emails.size());

        EmailMessageModel emailMessage = emails.get(0);

        assertEquals(TEST_EMAIL, emailMessage.getToAddresses().get(0).getEmailAddress());
        assertEquals(SUBJECT, emailMessage.getSubject());
        assertEquals(TEST_MESSAGE, emailMessage.getBody());
        assertEquals(SENDER_EMAIL, emailMessage.getFromAddress().getEmailAddress());
        assertEquals(REPLY_TO_ADDRESS, emailMessage.getReplyToAddress());
    }

    private List<EmailMessageModel> getAllEmails() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(EMAIL_MESSAGE_QUERY);
        query.addQueryParameter("subject", SUBJECT);
        final SearchResult<EmailMessageModel> searchResult = flexibleSearchService.search(query);
        return searchResult.getResult();
    }

    private void waitForEventProcessing() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
