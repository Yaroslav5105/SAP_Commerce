package org.training.core.event;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import javax.annotation.Resource;
import java.util.List;

public class CustomerEmailEventListener extends AbstractEventListener<CustomerEmailEvent> {

    private static final String SENDER_EMAIL = "noreply@example.com";
    private static final String REPLY_TO_ADDRESS = "support@example.com";
    private static final String SUBJECT = "Notification";
    private static final String DISPLAY_NAME = "Customer Support";


    @Resource
    private BusinessProcessService businessProcessService;
    @Resource
    private ModelService modelService;
    @Resource
    private EmailService emailService;

    @Override
    protected void onEvent(CustomerEmailEvent event) {
        ServicesUtil.validateParameterNotNullStandardMessage("email", event.getEmail());
        ServicesUtil.validateParameterNotNullStandardMessage("message", event.getMessage());

        EmailAddressModel sender = modelService.create(EmailAddressModel.class);
        sender.setEmailAddress(SENDER_EMAIL);
        sender.setDisplayName(DISPLAY_NAME);

        EmailAddressModel recipient = modelService.create(EmailAddressModel.class);
        recipient.setEmailAddress(event.getEmail());
        recipient.setDisplayName(event.getEmail());

        EmailMessageModel emailMessage = modelService.create(EmailMessageModel.class);
        emailMessage.setFromAddress(sender);
        emailMessage.setToAddresses(List.of(recipient));
        emailMessage.setReplyToAddress(REPLY_TO_ADDRESS);
        emailMessage.setSubject(SUBJECT);
        emailMessage.setBody(event.getMessage());

        modelService.save(emailMessage);
        emailService.send(emailMessage);
    }
}