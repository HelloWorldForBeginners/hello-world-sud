package com.knightsofsomethingnotable;
/**
 * Created by matt on 1/31/16.
 */
public class GameObject {

        private String name;
        private String description;

        public GameObject(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public void setName(String name) { this.name = name; }

        public String getName() {
            return this.name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
}
