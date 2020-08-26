package src;

public class Contact implements Comparable<Contact>{
private String firstName;
private String lastName;
private String phone;

    public Contact(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int compareTo(Contact obj) {
        /*
         * Sorting by last name. compareTo should return < 0 if this(keyword)
         * is supposed to be less than obj, > 0 if this is supposed to be
         * greater than object obj and 0 if they are supposed to be equal.
         */
        int last = this.getLastName().compareTo(obj.getLastName());
        //Sorting by first name if last name is same d
    return last == 0 ? this.getFirstName().compareTo(obj.getFirstName()) : last;

    }
}
