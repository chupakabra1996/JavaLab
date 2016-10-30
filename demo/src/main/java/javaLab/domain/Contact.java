package javaLab.domain;

import javax.persistence.*;


@Entity
@Table(name = "contact")
public class Contact {

    private long id;

    private String name;

    private String phone;

    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return this.name;
    }

    @Column(name = "phone", nullable = false, length = 32)
    public String getPhone() {
        return this.phone;
    }

    @Column(name = "address", nullable = false, length = 120)
    public String getAddress() {
        return this.address;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!name.equals(contact.name)) return false;
        if (!phone.equals(contact.phone)) return false;
        return address.equals(contact.address);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}