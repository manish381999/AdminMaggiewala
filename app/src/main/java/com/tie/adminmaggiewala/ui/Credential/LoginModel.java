package com.tie.adminmaggiewala.ui.Credential;

public class LoginModel {

    private String status;
    private String message;

    private UserModel data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }

    public class UserModel{
        private String id;
        private String email;
        private String password;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }



        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    }
}
