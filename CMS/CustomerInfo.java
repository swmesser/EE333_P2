package CMS;

/* 
 * File: CustomerInfo
 * Copy: Copyright (c) 2023 Samuel W. Messer
 * BlazerID: swmesser
 * Vers: 1.0.0 Sep 19, 2023 SWM - Original Coding
 * Desc: Driver for testing concepts
 */

public class CustomerInfo {
    // Declaration and Initialization as safe guard
    private CMS.CustomerInfo.CustomerInfoStatus Status;
    private String ID;
    private String Firstname;
    private String Lastname;
    private String Address;
    private String Email;
    
    
    //Like a structure from C
    public enum CustomerInfoStatus{
        Active,
        Inactive,
        Unknown,
        //  Dirty - would represent an object that was changed...
    }

    //Hand made get function
    public CMS.CustomerInfo.CustomerInfoStatus getStatus(){
        return(this.Status);
    }
    
    public boolean setStatus( CMS.CustomerInfo.CustomerInfoStatus PotentialStatus ){
        boolean results = false;
        //write logic so that only SOME of the statuses are updateable externally
        if ( PotentialStatus == CMS.CustomerInfo.CustomerInfoStatus.Unknown){
            results = false;
        } else {
            this.Status = PotentialStatus;
            
            results = true;
        }
        
        return( results );
    }

    /**
     * @return the ID
     */
    public String getID() {
        return (this.ID);
    }

    /**
     * @return the Firstname
     */
    public String getFirstname() {
        return (this.Firstname);
    }

    /**
     * @param Firstname the Firstname to set
     */
    public boolean setFirstname(String Firstname) {
        this.Firstname = Firstname;
        return( true );
    }

    /**
     * @return the Lastname
     */
    public String getLastname() {
        return (this.Lastname);
    }

    /**
     * @param Lastname the Lastname to set
     */
    public boolean setLastname(String Lastname) {
        this.Lastname = Lastname;
        return( true );
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return (this.Address);
    }

    /**
     * @param Address the Address to set
     */
    public boolean setAddress(String Address) {
        this.Address = Address;
        return( true );
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return (this.Email);
    }

    /**
     * @param Email the Email to set
     */
    public boolean setEmail(String Email) {
        this.Email = Email;
        return( true );
    }
    
    private CustomerInfo(){
        this.Status = CMS.CustomerInfo.CustomerInfoStatus.Unknown;
        this.ID = ID;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Address = Address;
        this.Email = Email;
    }
    
    public CustomerInfo( String ID, String Email ) throws Exception {
        this.Status = CMS.CustomerInfo.CustomerInfoStatus.Unknown;
        this.ID = "";
        this.Email = "";
        this.Firstname = "";
        this.Lastname = "";
        this.Address = "";
                
        if (( ID == null ) || ( ID.length() == 0 )) {
            System.out.println("Warning: Please enter a valid ID!");
            throw new Exception("Warning: Invalid ID passed!");
            //throw new IllegalArgumentException("Warning: Invalid ID passed!");
        } else if (( Email == null ) || ( Email.length() == 0 ) || ( validateEmail(Email) == false)) {
            System.out.println("Warning: Please enter a valid Email!");
            this.Status = CMS.CustomerInfo.CustomerInfoStatus.Active; // Unknown
            this.ID = ID;
        } else { 
            // set the defaults
            this.Status = CMS.CustomerInfo.CustomerInfoStatus.Active;
            this.ID = ID;
            this.Email = Email;
            
        }
    }
    
    public CustomerInfo( String ID, String Firstname, String Lastname, String Address, String Email ) throws Exception {
        this.Status = CMS.CustomerInfo.CustomerInfoStatus.Unknown;
        this.ID = "";
        this.Firstname = "";
        this.Lastname = "";
        this.Address = "";
        this.Email = "";
        
        if (( ID == null ) || ( ID.length() == 0 )) {
            System.out.println("Warning: Please enter a valid ID!");
            throw new Exception("Warning: Invalid ID passed!");
            //throw new IllegalArgumentException("Warning: Invalid ID passed!");
        } else {
            //Assignment of the critical "Key"
            this.ID = ID;
            this.Status = CMS.CustomerInfo.CustomerInfoStatus.Active;
            //series of if-else for none critical fields
            if (( Email == null ) || ( Email.length() == 0 ) || ( validateEmail(Email) == false)) {
                System.out.println("Warning: Please enter a valid email!");
                this.Email = ""; //for an invalid email
            } else {
                this.Email = Email; // for a valid email
            }
            
            if (( Firstname == null )){
                System.out.println("Warning: Please enter a valid firstname!");
                this.Firstname = ""; // for an invalid firstname
            } else {
                this.Firstname = Firstname; // valid firstname
            }
            
            if (( Lastname == null )){
                System.out.println("Warning: Please enter a valid lastname!");
                this.Lastname = "";
            } else {
                this.Lastname = Lastname;
            }
            
            if (( Address == null )){
                System.out.println("Warning: Please enter a valid address!");
                this.Address = "";
            } else {
                this.Address = Address;
            }
            
        }
    }
    
    private boolean validateFields(){
        // validates ALL fields...
        // does not need an argument passed because it is an instance method
        // knows about itself
        // ex: this.Firstname
        boolean results = true;
        
        
        
        return( results );
    }
    
    private boolean validateEmail( String Email) {
        boolean results = true;
        // use regular expressions... pattern matching
        
        if (( Email == null ) || ( Email.length() == 0)){
            results = false;
        } else if ( Email.length() < 5 ){
            results = false;
        } else if (( Email.contains(".") == false ) || ( Email.contains("@") == false )){
            results = false;
        } 
        
        return( results );
    }
    
    public String toCustom(){
        String output = "";
        System.out.println("");
        output += "ID: " + this.ID + "\n";
        output += "Status: " + this.Status + "\n";
        output += "Firstname: " + this.Firstname + "\n";
        output += "Lastname: " + this.Lastname + "\n";
        output += "Address: " + this.Address + "\n";
        output += "Email: " + this.Email + "\n";

        return ( output );    
    }
    
    public String toCSV(){
        String output = "";
        System.out.println("");
        output += this.ID + ",";
        output += this.Status + ",";
        output += this.Firstname + ",";
        output += this.Lastname + ",";
        output += this.Address + ",";
        output += this.Email + '\n';     
        
        return( output );
    }
    
    public String toXML(){
        String output = "";
        System.out.println("");
        output += "<CustomerInfo>" + '\n'; 
        output += "    <ID>" + this.ID + "</ID>" + '\n';
        output += "    <Status>" + this.Status + "</Status>" + '\n';
        output += "    <FirstName>" + this.Firstname + "</FirstName>" + '\n';
        output += "    <LastName>" + this.Lastname + "</LastName>" + '\n';
        output += "    <Address>" + this.Address + "</Address>" + '\n';
        output += "    <Email>" + this.Email + "</Email>" + '\n';
        output += "</CustomerInfo>" + '\n'; 
        return( output );
    }
    
    public String toJSON(){
        String output = "";
        System.out.println("");
        output += "{" + "\n"; 
        output += " \"ID\" : \"" + this.ID + "\"," + "\n" ;
        output += " \"Status\" : \"" + this.Status + "\"," + "\n";
        output += " \"Firstname\": \"" + this.Firstname + "\"," + "\n" ;
        output += " \"Lastname\": \"" + this.Lastname + "\"," + "\n";
        output += " \"Address\": \"" + this.Address + "\"," + "\n";
        output += " \"Email\": \"" + this.Email + '\"' + "\n";
        output += "}";
        return( output );
    }
    
    public static CMS.CustomerInfo fromCustom( String input ) throws Exception {
        CMS.CustomerInfo Contents = null; 
        String[] Lines;
        String Line;
        String[] Chunks;
        String Chunk;
        String ID = "";
        String Firstname = "";
        String Lastname = "";
        String Address = "";
        String Email = "";
        String Status = "";

        //Create a nested loop that will loop through lines and count the customers 
        // think about the lines it takes to hold customer info
        
        //Validation of the input
        if ( input == null ){
            throw new Exception("Error: Null input!");
        } else if ( input.length() == 0 ){
            throw new Exception("Error: Zero length string!");
        } else {
            Lines = input.split("\\n");
            for ( int index = 0; index < Lines.length; index++){
                Line = Lines[ index ];
                Chunks = Line.split(": ");
                if ( Chunks.length == 2 ){
                    if ( Chunks[ 1 ].length() == 0 ){
                        System.out.println("Error: Zero length value was provided");
                    } else if ( Chunks[ 0 ].equalsIgnoreCase("ID") == true){
                        ID = Chunks[ 1 ];
                    } else if ( Chunks[ 0 ].equalsIgnoreCase("Firstname") == true){
                        Firstname = Chunks[ 1 ];
                    } else if ( Chunks[ 0 ].equalsIgnoreCase("Lastname") == true){
                        Lastname = Chunks[ 1 ];
                    } else if ( Chunks[ 0 ].equalsIgnoreCase("Address") == true){
                        Address = Chunks[ 1 ];
                    } else if ( Chunks[ 0 ].equalsIgnoreCase("Email") == true){
                        Email = Chunks[ 1 ];
                    } else if ( Chunks[ 0 ].equalsIgnoreCase("Status") == true){
                        Status = Chunks[ 1 ];
                    } else {
                        System.out.println("Error: Unhandled field provided!");
                    }
                } 
            }
            
            // Need to create validation for if ID exists as an object then check if 
            // Values captured are > 0
            // Nest this into the validation for a pre-existing ID 
            if (( ID.length() != 0 ) && ( Firstname.length() > 0 ) &&
                    ( Lastname.length() > 0 ) && ( Address.length() > 0 ) && ( Email.length() > 0 )){
                Contents = new CMS.CustomerInfo(ID, Firstname, Lastname, Address, Email);
                if ( Status.equalsIgnoreCase("Active") == true ){
                    Contents.setStatus(CustomerInfoStatus.Active);
                } else if ( Status.equalsIgnoreCase("Inactive") == true ){
                    Contents.setStatus(CustomerInfoStatus.Inactive);
                } else {
                    Contents.setStatus(CustomerInfoStatus.Unknown);
                }
            } else if (( ID.length() > 0 ) && ( Email.length() > 0 )){
                Contents = new CMS.CustomerInfo(ID, Email);
                if ( Status.equalsIgnoreCase("Active") == true ){
                    Contents.setStatus(CustomerInfoStatus.Active);
                } else if ( Status.equalsIgnoreCase("Inactive") == true ){
                    Contents.setStatus(CustomerInfoStatus.Inactive);
                } else {
                    Contents.setStatus(CustomerInfoStatus.Unknown);
                }
            } else {
                System.out.println("Error: Failed to parse input!");
            }
        }
                
        return( Contents );
    }
    
    public static CMS.CustomerInfo fromXML( String input ) throws Exception {
        CMS.CustomerInfo Contents = null;
        String ID = "";
        String Firstname = "";
        String Lastname = "";
        String Address = "";
        String Email = "";
        String Status = "";
        
            //Parsing the input using regex
            java.util.regex.Pattern regex = java.util.regex.Pattern.compile("<CustomerInfo>(.*)</CustomerInfo>");
            // Checking the input for a match of the pattern
            java.util.regex.Matcher matcher = regex.matcher( input );

            for ( int index = 0; index < matcher.groupCount(); index++ ){

                if ( matcher.find() == true ){
                    //Pattern match for each of the fields in the Object
                    //Pattern for ID
                    regex = java.util.regex.Pattern.compile("<ID>(.*)</ID>");
                    matcher = regex.matcher( input );
                    if ( matcher.find() == true ){
                        ID = matcher.group(1);
                    }

                    regex = java.util.regex.Pattern.compile("<Status>(.*)</Status>");
                    matcher = regex.matcher( input );
                    if ( matcher.find() == true ){
                        Status = matcher.group(1);
                    }


                    //Pattern match for firstname
                    regex = java.util.regex.Pattern.compile("<Firstname>(.*)</Firstname>");
                    matcher = regex.matcher(input);
                    if ( matcher.find() == true ){
                        Firstname = matcher.group(1);
                    }

                    //Pattern match for lastname
                    regex = java.util.regex.Pattern.compile("<Lastname>(.*)</Lastname>");
                    matcher = regex.matcher(input);
                    if ( matcher.find() == true ){
                        Lastname = matcher.group(1);
                    }

                    //Pattern match for address
                    regex = java.util.regex.Pattern.compile("<Address>(.*)</Address>");
                    matcher = regex.matcher(input);
                    if ( matcher.find() == true ){
                        Address = matcher.group(1);
                    }

                    //Pattern match for email
                    regex = java.util.regex.Pattern.compile("<Email>(.*)</Email>");
                    matcher = regex.matcher(input);
                    if ( matcher.find() == true ){
                        Email = matcher.group(1);
                    }
                }

                // Need to create validation for if ID exists as an object then check if 
                // Values captured are > 0
                // Nest this into the validation for a pre-existing ID 
                if (( ID.length() > 0 ) && ( Firstname.length() > 0 ) &&
                        ( Lastname.length() > 0 ) && ( Address.length() > 0 ) && ( Email.length() > 0 )){
                    Contents = new CMS.CustomerInfo(ID, Firstname, Lastname, Address, Email);
                    if ( Status.equalsIgnoreCase("Active") == true ){
                        Contents.setStatus(CustomerInfoStatus.Active);
                    } else if ( Status.equalsIgnoreCase("Inactive") == true ){
                        Contents.setStatus(CustomerInfoStatus.Inactive);
                    } else {
                        Contents.setStatus(CustomerInfoStatus.Unknown);
                    }
                } else if (( ID.length() > 0 ) && ( Email.length() > 0 )){
                    Contents = new CMS.CustomerInfo(ID, Email);
                    if ( Status.equalsIgnoreCase("Active") == true ){
                        Contents.setStatus(CustomerInfoStatus.Active);
                    } else if ( Status.equalsIgnoreCase("Inactive") == true ){
                        Contents.setStatus(CustomerInfoStatus.Inactive);
                    } else {
                        Contents.setStatus(CustomerInfoStatus.Unknown);
                    }
            } else {
                System.out.println("Error: Failed to parse input!");
            }
            
        }
        
        return( Contents );
    }
    
    public static CMS.CustomerInfo fromJSON( String input ) throws Exception {
        CMS.CustomerInfo Contents = null;
        String[] Chunks;
        String ID = "";
        String Firstname = "";
        String Lastname = "";
        String Address = "";
        String Email = "";
        
        
        if ( input == null ){
            throw new Exception("Error: Null input!");
        } else if ( input.length() == 0 ){
            throw new Exception("Error: Zero length input provided!");
        } else {
//             //regex for json
//             java.util.regex.Pattern regex = java.util.regex.Pattern.compile("");
//             //matcher
//             java.util.regex.Matcher matcher = regex.matcher(input);
               
             
        }
        return( Contents );
    }
    
    public static CMS.CustomerInfo fromCSV( String input ) throws Exception {
        CMS.CustomerInfo Contents = null;
        String[] Chunks;
        String ID = "";
        String Firstname = "";
        String Lastname = "";
        String Address = "";
        String Email = "";
        String Status = "";
        
        if ( input == null ){
            throw new Exception("Error: Null input!");
        } else if ( input.length() == 0 ){
            throw new Exception("Error: Zero length string provided!");
        } else { 
            Chunks = input.split(",");
            if ( Chunks.length == 6 ){
                //Initialize the fields to each value
                ID = Chunks[ 0 ];
                Status = Chunks[ 1 ];
                Firstname = Chunks[ 2 ];
                Lastname = Chunks[ 3 ];
                Address = Chunks[ 4 ];
                Email = Chunks[ 5 ];
                
                if ( ID.length() == 0 ){
                    throw new Exception("Error: Zero length ID provided!");
                } else if ( Email.length() == 0 ){
                    throw new Exception("Error: Zero length Email provided!");
                } else {
                    //need to reevaluate if this is correct validation
                    Contents = new CMS.CustomerInfo(ID, Firstname, Lastname, Address, Email);
                    if ( Status.equalsIgnoreCase("Active") == true ){
                        Contents.setStatus(CustomerInfoStatus.Active);
                    } else if ( Status.equalsIgnoreCase("Inactive") == true ){
                        Contents.setStatus(CustomerInfoStatus.Inactive);
                    } else {
                        Contents.setStatus(CustomerInfoStatus.Unknown);
                    }
                }
            }
        }
        
        return( Contents );
    }
      
}
