package org.but.feec.airport.api;

import java.util.Arrays;

public class PersonEditView {
    private String first_name;
    private String last_name;
    private Long passport_number;
    private String country_of_residence;
    private String comments;

    public String getFirst_name() {
            return first_name;
        }
        public void setFirst_name(String first_name) {this.first_name=first_name;}
    public String getLast_name() {
            return last_name;
        }
        public void setLast_name(String last_name) {this.last_name=last_name;}
    public long getPassport_number() {return passport_number;}
    public void setPassport_number(long passport_number) {this.passport_number=passport_number;}
    public String getCountry_of_residence() {return country_of_residence;}
    public void setCountry_of_residence(String country_of_residence) {this.country_of_residence=country_of_residence;}
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {this.comments=comments;}
    @Override
    public String toString() {
        return "PersonCreateView{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", country_of_residence='" + country_of_residence + '\'' +
                ", comments='" + comments + '\''+
                '}';
    }
}
