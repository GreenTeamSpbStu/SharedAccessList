package server.entity;

import java.util.Date;

/**
 *
 * @author magni
 */
public class Message {
    protected int messageId;
    protected String messageAuthor;
    protected String messageText;
    protected Date messageCreationDate;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

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
