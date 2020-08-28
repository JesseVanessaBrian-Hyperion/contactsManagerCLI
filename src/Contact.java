package src;

public class Contact implements Comparable<Contact>{
private String firstName;
private String lastName;
private String phone;

    public Contact(){
        this("default", "default", "000000000");
    }

    public Contact(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    // Setters and Getters
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

    // Override compareTo Method from Comparable Interface to allow sorting of contacts by name
    @Override
    public int compareTo(Contact obj) {
        /*
         * Sorting by last name.
         * compareTo should return
         * < 0 if this(keyword) is less than obj,
         * > 0 if this is greater than obj
         * and 0 if they are equal.
         */
        int last = this.getLastName().compareTo(obj.getLastName());
        //Sorting by first name if last name is same
        return last == 0 ? this.getFirstName().compareTo(obj.getFirstName()) : last;

    }
}
