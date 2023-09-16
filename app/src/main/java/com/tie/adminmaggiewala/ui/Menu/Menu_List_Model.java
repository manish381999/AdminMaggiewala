package com.tie.adminmaggiewala.ui.Menu;

import java.util.List;

public class Menu_List_Model {

    private String status;
    private String message;

    List<LightDetails> data;

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

    public List<LightDetails> getData() {
        return data;
    }

    public void setData(List<LightDetails> data) {
        this.data = data;
    }

    public class  LightDetails{

        public String getItem_price() {
            return item_price;
        }

        public String getItem_img() {
            return item_img;
        }
        private String id;
        private String item_name;
        private String item_price;
        private String item_img;
        private String short_description;
        private String ingredient;


        // Getter Methods

        public String getId() {
            return id;
        }

        public String getItem_name() {
            return item_name;
        }

        public String getShort_description() {
            return short_description;
        }

        public String getIngredient() {
            return ingredient;
        }

        // Setter Methods

        public void setId(String id) {
            this.id = id;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public void setItem_price(String item_price) {
            this.item_price = item_price;
        }

        public void setItem_img(String item_img) {
            this.item_img = item_img;
        }

        public void setShort_description(String short_description) {
            this.short_description = short_description;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }
    }



}
