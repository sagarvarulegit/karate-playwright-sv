package com.sagar.playwright.demo.testdata;

public class RegistrationData {
    private String name;
    private String email_prefix;
    private String email_domain;
    private String password;
    private Dob dob;
    private Address address;

    public RegistrationData() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail_prefix() { return email_prefix; }
    public void setEmail_prefix(String email_prefix) { this.email_prefix = email_prefix; }

    public String getEmail_domain() { return email_domain; }
    public void setEmail_domain(String email_domain) { this.email_domain = email_domain; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Dob getDob() { return dob; }
    public void setDob(Dob dob) { this.dob = dob; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public static class Dob {
        private String day;
        private String month;
        private String year;
        public Dob() {}
        public String getDay() { return day; }
        public void setDay(String day) { this.day = day; }
        public String getMonth() { return month; }
        public void setMonth(String month) { this.month = month; }
        public String getYear() { return year; }
        public void setYear(String year) { this.year = year; }
    }

    public static class Address {
        private String first_name;
        private String last_name;
        private String street;
        private String country;
        private String state;
        private String city;
        private String zip;
        private String phone;
        public Address() {}
        public String getFirst_name() { return first_name; }
        public void setFirst_name(String first_name) { this.first_name = first_name; }
        public String getLast_name() { return last_name; }
        public void setLast_name(String last_name) { this.last_name = last_name; }
        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getZip() { return zip; }
        public void setZip(String zip) { this.zip = zip; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
    }
}
