package org.training.core.event;

import de.hybris.platform.servicelayer.event.events.AbstractEvent;

public class CustomerEmailEvent extends AbstractEvent {
    private String email;
    private String message;

    public CustomerEmailEvent(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}