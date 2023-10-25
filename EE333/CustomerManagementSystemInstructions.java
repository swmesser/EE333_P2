/**
 * Name: P2_Instructions
 * Description:
 * Links: 
 * Date Assigned: 09-19-2023
 * Date Due: 09-27-2023
 */

/**
 * P2: Customer Management System (CMS)
 * You have been approached by a small sales focused company wishing to organize
 * their customer information.  Their current system still involves pen and 
 * paper and an incredible assortment of business cards. They are historically 
 * slow to adopt new technology, so you may view this application as a simple 
 * attempt to gain their confidence in your skills. 
 * 
 * Since you cannot assume that they will be a strong contributor in 
 * establishing the requirements, you must take initiative and ask questions 
 * regarding their expectations of the application. As such, since you have a 
 * basic understanding of what they need, and a basic appreciation for what 
 * these types of problems involved, you can start with the following 
 * requirements: 
 * 
 * The system shall be ran from the command line.
 * 
 * The system shall have an interactive and a parametric interface.  More 
 *  details will be provided for these interfaces.
 * 
 * The system shall store the following information about a customer in a 
 *  persistent repository:
 *      ID - unique, no duplicates allowed
 *      Firstname
 *      Lastname
 *      Address
 *      Email - must be validated
 * 
 * The system shall allow for a new customer to be added with only an ID and 
 *  email address provided.
 * 
 * The system shall allow for a new customer to be added with all information 
 *  provided at once.
 * 
 * The system shall allow for customers to be queried by the following fields:
 *      ID - exact match
 *      Firstname - partial match
 *      Lastname - partial match
 *      Email - exact match
 * 
 * The system shall display all matching customer information on the command 
 *  line as well as producing a results output file.  More details will be 
 *  provided for the file format.
 * 
 * The system shall allow for the following fields to be updated after a 
 *  customer is added:
 *      Firstname
 *      Lastname
 *      Address
 *      Email
 * 
 * The system shall allow for the removal of a customer by deleting their 
 *  information from the repository.
 * 
 * The system shall allow for the archival of a customer (by setting an 
 *  enumerator).
 * 
 * The system shall maintain a transaction log describing any changes or queries
 *  to the repository. 
 * 
 * The system shall maintain a settings file that stores the repository and 
 *  format information.
 * 
 * Additional requirements:
 * The developer shall be required to use Java for this application, must 
 *  provide .java and .class files for all classes, must support the agreed upon
 *  formats, and will be responsible for querying the client for additional 
 *  information.
 * 
 * The developer must utilize the function signatures provided in a nontrivial 
 *  manner.
 * 
 * The system shall utilize the CMS package space.  Additional user defined 
 *  packages are permitted but not encouraged for this project.
 * 
 * The system shall utilize the following classes:
 *  CustomerManager
 *  CustomerInfo
 * 
 * The CustomerInfo class shall support:
 *  encapsulation of private fields (getField, setField)
 *  integrity of ID (no setID)
 *  public CustomerInfo(String ID, String Email)
 *  public CustomerInfo(String ID, String Firstname, String Lastname, 
 *      String Address, String Email)
 *  toString()
 *  toCustom() (field: value)
 *  toCSV()
 *  toXML()
 *  toJSON()
 */

/**
 * Command line argument support:
 *  /customer /add <ID> <Email>
 *  /customer /add <ID> <Firstname> <LastName> <Address> <Email>
 *  /customer /search ID=<ID>
 *  /customer /search field=<field> value=<value>
 *  /customer /update ID=<ID> field=<field> value=<value>
 *  /customer /delete ID=<ID>
 *  /customer /archive ID=<ID>
 *  /export /format <custom | csv | xml | json>
 *  /setting /repository <filename>
 *  /setting /format <custom | csv | xml | json>
*/