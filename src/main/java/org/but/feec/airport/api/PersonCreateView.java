package org.but.feec.airport.api;
import java.util.Arrays;
public class PersonCreateView {

        private String username;
        private String first_name;
        private String last_name;
        private String check_results;
        char [] password;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
        public String getFirst_name() {
            return first_name;
        }
         public void setFirst_name(String first_name){this.first_name=first_name;}
        public String getLast_name() {
            return last_name;}
        public void setLast_name(String last_name) {this.last_name=last_name;}
        public String getCheck_results() {return check_results;}
        public void setCheck_results(String check_results) {this.check_results=check_results;}
    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

        @Override
        public String toString() {
            return "PersonCreateView{" +
                    "username='" + username + '\'' +
                    ", first_name='" + first_name + '\'' +
                    ", last_name='" + last_name + '\'' +
                    ", check_results='" + check_results + '\'' +
                    ", password=" + Arrays.toString(password) +
                    '}';
        }
    }
