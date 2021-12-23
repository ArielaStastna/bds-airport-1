package org.but.feec.airport.api;
import java.util.Arrays;
public class PersonCreateView {


    private String first_name;
    private String last_name;
    private String passport_number;




    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name){this.first_name=first_name;}
    public String getLast_name() {
        return last_name;}
    public void setLast_name(String last_name) {this.last_name=last_name;}
    public String getPassport_number() {return passport_number;}
    public void setPassport_number(String passport_number) {this.passport_number = passport_number;}




    @Override
    public String toString() {
        return "PersonCreateView{" +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", passport_number='" + passport_number + '\'' +
        '}';
    }
}
