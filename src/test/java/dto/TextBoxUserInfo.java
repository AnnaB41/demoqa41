package dto;

import pages.TextBoxPage;

public class TextBoxUserInfo {

    String name;
    String email;
    String currentAddress;
    String permanentAddress;

    public TextBoxUserInfo() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public TextBoxUserInfo withName(String name) {
        this.name = name;
        return this;
    }

    public TextBoxUserInfo withEmail(String email) {
        this.email = email;
        return this;
    }

    public TextBoxUserInfo withCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
        return this;
    }

    public TextBoxUserInfo withPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
        return this;
    }
}
