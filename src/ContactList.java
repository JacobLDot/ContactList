import com.sun.source.util.SourcePositions;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactList {
    ArrayList<Person> contacts;

    public ContactList(ArrayList<Person> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Person> getContacts() {
        return contacts;
    }

    public void addContacts(Person contact) {
        contacts.add(contact);
    }

    public void printContacts() {
        for(int i = 0; i < contacts.size(); i++) {
            System.out.println(contacts.get(i));
        }
    }

    public void sort(int sortBy) {
        for(int i = 0; i < contacts.size()-1; i++) {
            for(int j = 0; j < contacts.size()-1; j++) {
                if (sortHelper(contacts.get(j), sortBy).compareTo(sortHelper(contacts.get(j+1), sortBy)) > 0) {
                    Person temporary = contacts.get(j);
                    contacts.set(j, contacts.get(j+1));
                    contacts.set(j+1, temporary);
                }
            }
        }
    }

    private String sortHelper(Person person, int sortBy) {
        switch(sortBy) {
            case 0:
                return person.getFirstName();
            case 1:
                return person.getLastName();
            case 2:
                return person.getPhoneNumber();
            default:
                return "Invalid";
        }
    }

    public void listStudents() {
        for(Person contact : contacts) {
            if (contact instanceof Student) {
                System.out.println(contact);
            }
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("\nMenu:");
        System.out.println("1. Add Contact");
        System.out.println("2. List Contacts by First Name");
        System.out.println("3. List Contacts by Last Name");
        System.out.println("4. List Contacts by Phone Number");
        System.out.println("5. List Students");
        System.out.println("6. Search by First Name");
        System.out.println("7. Search by Last Name");
        System.out.println("8. Search by Phone Number");
        System.out.println("9. Exit");

        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("first name: ");
                String firstName = scanner.nextLine();
                System.out.println("last name: ");
                String lastName = scanner.nextLine();
                System.out.println("phone number :");
                String phoneNumber = scanner.nextLine();

                System.out.println("is student? y or n");
                String isStudent = scanner.nextLine();

                if (isStudent.equals("y")) {
                    System.out.println("grade: ");
                    int grade = scanner.nextInt();
                    scanner.nextLine();
                    addContacts(new Student(firstName, lastName, phoneNumber, grade));
                }
                else {
                    addContacts(new Person(firstName, lastName, phoneNumber));
                }
            case 2:
                sort(0);
                printContacts();
                break;
            case 3:
                sort(1);
                printContacts();
                break;
            case 4:
                sort(2);
                printContacts();
                break;
            case 5:
                listStudents();
                break;
            case 6:
                System.out.println("first name: ");
                String searchFN = scanner.nextLine();
                searchContacts(searchFN, 0);
                break;
            case 7:
                System.out.println("last name: ");
                String searchLN = scanner.nextLine();
                searchContacts(searchLN, 0);
                break;
            case 8:
                System.out.println("phone number: ");
                String searchPN = scanner.nextLine();
                searchContacts(searchPN, 0);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid");

        }
    }

    private void searchContacts(String name, int searchType) {
        boolean found = false;
        for (Person contact : contacts) {
            switch (searchType) {
                case 0:
                    if (contact.getFirstName().equalsIgnoreCase(name)) {
                        System.out.println(contact);
                        found = true;
                    }
                    break;
                case 1:
                    if (contact.getLastName().equalsIgnoreCase(name)) {
                        System.out.println(contact);
                        found = true;
                    }
                    break;
                case 2:
                    if (contact.getPhoneNumber().equalsIgnoreCase(name)) {
                        System.out.println(contact);
                        found = true;
                    }
                    break;
            }
        }
        if (!found) {
            System.out.println(name + " isnt here");
        }
    }
}
