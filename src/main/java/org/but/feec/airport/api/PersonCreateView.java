package org.but.feec.airport.api;
import java.util.Arrays;
public class PersonCreateView {

        private String first_name;
        private String last_name;
        private String valid;

        public String getFirst_name() {
            return first_name;
        }
         public void setFirst_name(String first_name){this.first_name=first_name;}
        public String getLast_name() {
            return last_name;}
        public void setLast_name(String last_name) {this.last_name=last_name;}
        public String getValid() {return valid;}
        public void setValid(String valid) {this.valid=valid;}

        @Override
        public String toString() {
            return "PersonCreateView{" +
                    "first_name='" + first_name + '\'' +
                    ", last_name='" + last_name + '\'' +
                    ", valid='" + valid+ '\'' +
                    '}';
        }
    }
