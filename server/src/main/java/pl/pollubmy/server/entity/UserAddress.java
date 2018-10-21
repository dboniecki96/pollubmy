package pl.pollubmy.server.entity;

import pl.pollubmy.server.enumType.CountryType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class UserAddress {

    //Fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userAddressId;

    @NotNull
    @NotEmpty
    private CountryType Country;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String street;

    @NotNull
    @NotEmpty
    private String houseNumber;

    private String apartmentNumber;

    @NotNull
    @NotEmpty
    private String postalCode;

    @OneToOne
    @JoinColumn(name = "userIdFk")
    private User userIdFK;


    // Getters and setters

    public long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public CountryType getCountry() {
        return Country;
    }

    public void setCountry(CountryType country) {
        Country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public User getUserIdFK() {
        return userIdFK;
    }

    public void setUserIdFK(User userIdFK) {
        this.userIdFK = userIdFK;
    }
}
