package server.entity;

import java.util.Date;

/**
 *
 * @author magni
 */
public class Message {
    protected String messageAuthor;
    protected String messageText;
    protected Date messageCreationDate;

    public String getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(String messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getMessageCreationDate() {
        return messageCreationDate;
    }

    public void setMessageCreationDate(Date messageCreationDate) {
        this.messageCreationDate = messageCreationDate;
    }
    
}
