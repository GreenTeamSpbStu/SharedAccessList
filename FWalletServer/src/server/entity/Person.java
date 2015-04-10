package server.entity;

import java.util.Date;

/**
 *
 * @author magni
 */
public class Person {
    protected String personName;
    protected String personLogin;
    protected String personPassword;
    protected Date personRegistrationDate;
    protected Date personLastTimeOnline;
    protected String personEmail;

    public Person(String personName, String personLogin, String personPassword, Date personRegistrationDate, Date personLastTimeOnline, String personEmail) {
        this.personName = personName;
        this.personLogin = personLogin;
        this.personPassword = personPassword;
        this.personRegistrationDate = personRegistrationDate;
        this.personLastTimeOnline = personLastTimeOnline;
        this.personEmail = personEmail;
    }

    public Person() {
        this.personName = "";
        this.personLogin = "";
        this.personPassword = "";
        this.personRegistrationDate = new Date();
        this.personLastTimeOnline = new Date();
        this.personEmail = "";
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonLogin() {
        return personLogin;
    }

    public void setPersonLogin(String personLogin) {
        this.personLogin = personLogin;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    public Date getPersonRegistrationDate() {
        return personRegistrationDate;
    }

    public void setPersonRegistrationDate(Date personRegistrationDate) {
        this.personRegistrationDate = personRegistrationDate;
    }

    public Date getPersonLastTimeOnline() {
        return personLastTimeOnline;
    }

    public void setPersonLastTimeOnline(Date personLastTimeOnline) {
        this.personLastTimeOnline = personLastTimeOnline;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }
}
