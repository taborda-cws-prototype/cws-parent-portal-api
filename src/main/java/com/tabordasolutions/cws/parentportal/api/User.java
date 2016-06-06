package com.tabordasolutions.cws.parentportal.api;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "users")
@JsonSerialize(using=UserSerializer.class)
@JsonDeserialize(using=UserDeserializer.class)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "users_id_seq")
    @SequenceGenerator(name="users_id_seq",sequenceName="users_id_seq",allocationSize=1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "in_care_of")
    private String inCareOf;
    @Column(name = "address1")
    private String streetAddress1;
    @Column(name = "address2")
    private String streetAddress2;
    @Column(name = "state_abbreviation")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "zip_code")
    private String zip;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "email", unique=true)
    private String email;

    // TODO: map this association
    @Transient
    private List<User> caseworkers;

    // TODO: implement password hashing
    @Transient
    private String password;

    public Long getId() {return id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInCareOf() {
        return inCareOf;
    }

    public void setInCareOf(String inCareOf) {
        this.inCareOf = inCareOf;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<User> getCaseworkers() {
        return caseworkers;
    }

    public void setCaseworkers(List<User> caseworkers) {
        this.caseworkers = caseworkers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", inCareOf=" + inCareOf + ", streetAddress1="
				+ streetAddress1 + ", streetAddress2=" + streetAddress2
				+ ", state=" + state + ", city=" + city + ", zip=" + zip
				+ ", imageUrl=" + imageUrl + ", email=" + email + ", password="
				+ password + "]";
	}
    
    public static class Builder 
    {
    	User user;
        public Builder() {
        	user = new User();
        }
        public Builder id(Long id) {
            user.id = id;
            return this;
        }
        public Builder firstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }
        public Builder lastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }
        public Builder inCareOf(String inCareOf) {
            user.setInCareOf(inCareOf);
            return this;
        }
        public Builder streetAddress1(String streetAddress1) {
            user.setStreetAddress1(streetAddress1);
            return this;
        }
        public Builder streetAddress2(String streetAddress2) {
            user.setStreetAddress2(streetAddress2);
            return this;
        }
        public Builder state(String state) {
            user.setState(state);
            return this;
        }
        public Builder city(String city) {
            user.setCity(city);
            return this;
        }
        public Builder zip(String zip) {
            user.setZip(zip);
            return this;
        }
        public Builder imageUrl(String imageUrl) {
            user.setImageUrl(imageUrl);
            return this;
        }
        public Builder email(String email) {
            user.setEmail(email);
            return this;
        }
        public Builder password(String password) {
            user.setPassword(password);
            return this;
        }

        public User build() {
            validateUserObject(user);
            return user;
        }
        private void validateUserObject(User user) {
            //TODO : validate user
        }
    }
}
