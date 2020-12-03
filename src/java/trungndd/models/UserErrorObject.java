/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.models;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class UserErrorObject implements Serializable {

    private String idError, firstNameError, lastNameError, imgError, phoneError, 
            usernameError, emailError, passwordError, confirmError;

    public UserErrorObject() {
    }

    public boolean checkId(String id) {
        if (!id.trim().matches("U[0-9]{1,3}")) {
            this.idError = "Invalid id (Uxxx)";
            return false;
        }

        return true;
    }

    public boolean checkFirstName(String name) {
        if (!name.trim().matches("[A-Za-z0-9 ]{2,30}")) {
            this.firstNameError = "Name must be at least 3 character and maximum 30, "
                    + "and contain no special character.";
            return false;
        }

        return true;
    }

    public boolean checkLastName(String name) {
        if (!name.trim().matches("[A-Za-z0-9 ]{2,30}")) {
            this.lastNameError = "Name must be at least 3 character and maximum 30, "
                    + "and contain no special character.";
            return false;
        }

        return true;
    }

    public boolean checkImg(String img) {
        if (img.trim().length() == 0) {
            this.imgError = "Invalid Image path";
            return false;
        }

        return true;
    }

    public boolean checkPhone(String phone) {
        if (!phone.trim().matches("[0-9]{10,12}")) {
            this.phoneError = "Invalid Phone number";
            return false;
        }

        return true;
    }

    public boolean checkUsername(String username) {
        if (!username.matches("[A-Za-z0-9]{3,30}")) {
            this.usernameError = "Invalid Username";
            return false;
        }

        return true;
    }

    public boolean checkEmail(String email) {
        if (!email.matches("[0-9A-Za-z.]{3,20}@[a-zA-Z]{2,10}(.[a-zA-Z]{2,10}){1,2}")) {
            this.emailError = "Invalid Email";
            return false;
        }

        return true;
    }

    public boolean checkPassword(String password) {
        if (password.length() < 8 || password.length() > 13) {
            this.passwordError = "Password lenght must be between 8 and 13 characters";
            return false;
        }

        return true;
    }

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getImgError() {
        return imgError;
    }

    public void setImgError(String imgError) {
        this.imgError = imgError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getFirstNameError() {
        return firstNameError;
    }

    public void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public boolean checkConfirm(String confirm, String password){
        if (!confirm.matches(password)) {
            this.confirmError = "Confirm must match password";
            return false;
        }
        
        return true;
    }
}
