package CMS;

import java.util.ArrayList;

/* 
 * File: CustomerInfoManager
 * Copy: Copyright (c) 2023 Samuel W. Messer
 * BlazerID: swmesser
 * Vers: 1.0.0 Sep 19, 2023 SWM - Original Coding
 * Desc: Driver for testing concepts
 */

public class CustomerInfoManager {

    private static SettingInfo CurrentSettingInfo = new CMS.SettingInfo(); //shared hidden object for settings storage
    private static OutputInfo CurrentOutputInfo = new CMS.OutputInfo();

            
    public static void main(String[] args) {
        // Variable Declaration and Initialization Bank
        int index = 0;
        
        
        // Print the count of arguments in CMD
        System.out.println("Count: " + args.length );
                
        //Loop to print all arguments in CMD
        for ( index = 0; index < args.length; index++ ){
            System.out.println("args[ " + index + " ]: " + args[ index ]);
        }
        
        //Getting setting info to run through program
        boolean settingsExist = false;
        boolean exportExist = false;
        exportExist = getOutputInfo();
        settingsExist = getSettingInfo();
        //Testing if the settings do exist
        if ( settingsExist == true){
            // Meaning we have the settings and can run the functions
            if ( args.length == 0 ){
                // Implicit call for help
                CMS.CustomerInfoManager.help();
            } else if (( args.length == 1 ) && ( args[ 0 ].compareToIgnoreCase("/help") == 0 )){
                // Explicit call for help 
                CMS.CustomerInfoManager.help();
            } else if (( args[ 0 ].compareToIgnoreCase("/customer") == 0) &&
                    ( args[ 1 ].compareToIgnoreCase("/add") == 0 )){
                // Declarations and initializations
                String ID = "";
                String Firstname = "";
                String Lastname = "";
                String Address = "";
                String Email = "";
                CMS.CustomerInfo PotentialCustomer;
                java.util.ArrayList<CMS.CustomerInfo> Customers;

                if ( args.length == 4 ){
                    ID = args[ 2 ];
                    Email = args[ 3 ];

                    if (( ID.length() > 0 ) && ( Email.length() > 0 )){
                        //Create new customer object to send to AddCustomer
                        try{ 
                            PotentialCustomer = new CMS.CustomerInfo(ID, Email);
                            //Import Repository file into Customers arraylist using CurrentSettingInfo
                            Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            //Send Potential and pre-existing into AddCustomer to try and add into arraylist
                            Customers = AddCustomer(Customers, PotentialCustomer);
                            //Exporting the entire arraylist with new customer added
                            Export(Customers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());

                        } catch ( Exception ex ){
                            System.out.println("Warning: Failed to Create Customer!");
                            System.out.println( ex.toString() );
                        }
                    } else {
                        System.out.println("Error: Invalid arguments passed!");
                    }
                    
                } else if ( args.length == 7){
                    ID = args[ 2 ];
                    Firstname = args[ 3 ];
                    Lastname = args[ 4 ];
                    Address = args[ 5 ];
                    Email = args[ 6 ]; 
                    
                    if (( ID.length() > 0 ) && ( Firstname.length() > 0 ) && ( Lastname.length() > 0 ) 
                            && ( Address.length() > 0 ) && ( Email.length() > 0 )){
                        //Create new customer object to send to AddCustomer
                        try{ 
                            PotentialCustomer = new CMS.CustomerInfo(ID, Firstname, Lastname, Address, Email);
                            //Import Repository file into Customers arraylist using CurrentSettingInfo
                            Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            //Send Potential and pre-existing into AddCustomer to try and add into arraylist
                            Customers = AddCustomer(Customers, PotentialCustomer);
                            //Exporting the entire arraylist with new customer added
                            Export(Customers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());

                        } catch ( Exception ex ){
                            System.out.println("Warning: Failed to Create Customer!");
                            System.out.println( ex.toString() );
                        }
                    } else {
                        System.out.println("Error: Invalid arguments passed!");
                    }
                } else if (  args.length == 2 ){
                    //interactive
                    java.util.Scanner Scanner = new java.util.Scanner( System.in );
                    
                    System.out.println("To add a customer enter at a minimum the ID and Email!");
                    
                    System.out.println("Please enter a valid ID for the customer:");
                    ID = Scanner.nextLine();
                    System.out.println("");
                    System.out.println("Please enter a firstname:");
                    Firstname = Scanner.nextLine();
                    System.out.println("");
                    System.out.println("Please enter a lastname:");
                    Lastname = Scanner.nextLine();
                    System.out.println("");
                    System.out.println("Please enter an address:");
                    Address = Scanner.nextLine();
                    System.out.println("");
                    System.out.println("Please enter an email:");
                    Email = Scanner.nextLine();
                    
                    if (( ID.length() > 0 ) && ( Firstname.length() > 0 ) && ( Lastname.length() > 0 ) && 
                            (Address.length() > 0 ) && (Email.length() > 0 )){
                        try{
                            PotentialCustomer = new CMS.CustomerInfo(ID, Firstname, Lastname, Address, Email);
                            //Import Repository file into Customers arraylist using CurrentSettingInfo
                            Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            //Send Potential and pre-existing into AddCustomer to try and add into arraylist
                            Customers = AddCustomer(Customers, PotentialCustomer);
                            //Exporting the entire arraylist with new customer added
                            Export(Customers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                        } catch( Exception ex ){
                            System.out.println( ex.toString());
                        }
                    } else if (( ID.length() > 0) && ( Email.length() > 0 )){
                       try{
                            PotentialCustomer = new CMS.CustomerInfo(ID, Firstname, Lastname, Address, Email);
                            //Import Repository file into Customers arraylist using CurrentSettingInfo
                            Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            //Send Potential and pre-existing into AddCustomer to try and add into arraylist
                            Customers = AddCustomer(Customers, PotentialCustomer);
                            //Exporting the entire arraylist with new customer added
                            Export(Customers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                        } catch( Exception ex ){
                            System.out.println( ex.toString());
                        } 
                    } else { 
                        System.out.println("Error: Failed to Create Customer!");
                    }
                } else {
                    System.out.println("Error: Invalid argument count!");
                    System.out.println("");
                    CMS.CustomerInfoManager.help();
                }

            } else if (( args[ 0 ].compareToIgnoreCase("/customer") == 0 ) && 
                    ( args[ 1 ].compareToIgnoreCase("/search") == 0 )){ 
                String field = "";
                String value = "";
                String[] Chunks1;
                String[] Chunks2;
                CMS.CustomerInfo Customer;
                // Arraylist to loop through and find object matching the partial search
                java.util.ArrayList<CMS.CustomerInfo> Customers;
                java.util.ArrayList<CMS.CustomerInfo> Results;

                if ( args.length == 3 ){
                    //Search by ID
                    Chunks1 = args[2].split("=");
                    if ( Chunks1[ 0 ].compareToIgnoreCase("ID") == 0){
                        field = Chunks1[ 0 ];
                        value = Chunks1[ 1 ];
                        if (( field.length() > 0 ) && ( value.length() > 0 )){
                            Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            if ( Customers.size() > 0){
                                Results = SearchCustomer(Customers, field, value);
                                System.out.println("");
                                System.out.println("Customers found in search: " + Results.size());
                                DisplayCustomer(Results);
                                Export(Results, CurrentOutputInfo.getFile(), CurrentOutputInfo.getFormat());
                            } else {
                                System.out.println("Error: " + CurrentSettingInfo.getRepository() + " is empty!");
                            }
                        } else {
                            System.out.println("Error: Invalid arguments passed!");
                        }
                            
                    } else {
                        System.out.println("Error: Unhandled field");
                    }

                } else if ( args.length == 4 ){
                    Chunks1 = args[2].split("=");
                    Chunks2 = args[3].split("=");

                    if (( Chunks1[ 0 ].equalsIgnoreCase("field") == true ) && ( Chunks2[ 0 ].equalsIgnoreCase("value"))){
                        field = Chunks1[ 1 ];
                        value = Chunks2[ 1 ];
                        if (( field.length() > 0 ) && ( value.length() > 0 )){
                            //search by field and value from CMD 
                            Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            if ( Customers.size() > 0 ){
                                Results = SearchCustomer(Customers, field, value);
                                System.out.println("");
                                System.out.println("Customers found in search:" + Results.size());
                                DisplayCustomer(Results);
                                Export(Results, CurrentOutputInfo.getFile(), CurrentOutputInfo.getFormat());
                            } else {
                                System.out.println("Error: " + CurrentSettingInfo.getRepository() + " is empty!");
                            }
                        } else {
                            System.out.println("Error: Invalid arguments passed!");
                        }

                    } else {
                        System.out.println("Error: Unhandled argument fields!");
                    }

                } else if ( args.length == 2 ){
                    //Interactive
                    java.util.Scanner Scanner = new java.util.Scanner( System.in );
                    
                    System.out.println("To search for a customer fill out the following parameters!");
                    System.out.println("Please enter a field to search by:");
                    field = Scanner.nextLine();
                    System.out.println("");
                    System.out.println("Please enter a value for this field:");
                    value = Scanner.nextLine();
                    
                    if (( field.length() > 0 ) && ( value.length() > 0)){
                        Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                        if ( Customers.size() > 0 ){
                            Results = SearchCustomer(Customers, field, value);
                            System.out.println("");
                            System.out.println("Customers found in search:" + Results.size());
                            DisplayCustomer(Results);
                            Export(Results, CurrentOutputInfo.getFile(), CurrentOutputInfo.getFormat());
                        } else {
                            System.out.println("Error: " + CurrentSettingInfo.getRepository() + " is empty!");
                        }
                    } else {
                        System.out.println("Error: Invalid arguments passed!");
                    }
                } else {
                    System.out.println("Error: Unhandled argument count!");
                }

            } else if (( args.length == 5 ) && 
                    ( args[ 0 ].compareToIgnoreCase("/customer") == 0 ) &&
                    ( args[ 1 ].compareToIgnoreCase("/update")) == 0 ){
                String ID = "";
                String field = "";
                String value = "";
                // ArrayList to loop through and find the Object with ID matching the inputted ID
                java.util.ArrayList<CMS.CustomerInfo> Customers;
                // Customer Object that contains ID to update
                CMS.CustomerInfo Customer;
                String[] Chunks1;
                String[] Chunks2;
                String[] Chunks3;

                if ( args.length == 5 ){
                    //Chunk for ID
                    Chunks1 = args[ 2 ].split("=");
                    //Chunk for Field
                    Chunks2 = args[ 3 ].split("=");
                    //Chunk for Value
                    Chunks3 = args[ 4 ].split("=");

                    if ( ( Chunks3[ 0 ].equalsIgnoreCase("ID") == true ) && 
                            ( Chunks1[ 0 ].equalsIgnoreCase("field") == true ) &&
                            ( Chunks2[ 0 ].equalsIgnoreCase("value") == true )){
                        ID = Chunks3[ 1 ];
                        field = Chunks1[ 1 ];
                        value = Chunks2[ 1 ];
                        if ( ( ID.length() > 0 ) && (field.length() > 0) && (value.length() > 0 )){
                            Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            if ( Customers.size() > 0 ){
                                //Call update function
                                Customers = UpdateCustomer(Customers, field, value, ID);
                                //DisplayCustomer(Customers); Just a test for showing that the updated customer has been added back to the arraylist
                                Export(Customers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            } else {
                                System.out.println("Error: " + CurrentSettingInfo.getRepository() + " is empty!");
                            }
                        } else {
                            System.out.println("Error: Invalid arguments passed!");
                        }
                    } else {
                        System.out.println("Error: Unhandled arguments!");
                    }

                } else if ( args.length == 2 ){
                    //Interactively
                    java.util.Scanner Scanner = new java.util.Scanner( System.in );
                    
                    System.out.println("To Update a Customer fill out the following parameters!");
                    System.out.println("");
                    System.out.println("Please enter an ID of the customer to update:");
                    ID = Scanner.nextLine();
                    System.out.println("");
                    System.out.println("Please enter the field to update:");
                    field = Scanner.nextLine();
                    System.out.println("");
                    System.out.println("Please enter the value for that field:");
                    value = Scanner.nextLine();
                    
                    if ( ( ID.length() > 0 ) && ( field.length() > 0 ) && ( value.length() > 0 )){
                        Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                        if ( Customers.size() > 0 ){
                            //Call update function
                            Customers = UpdateCustomer(Customers, field, value, ID);
                            //DisplayCustomer(Customers); Just a test for showing that the updated customer has been added back to the arraylist
                            Export(Customers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                        } else {
                            System.out.println("Error: " + CurrentSettingInfo.getRepository() + " is empty!");
                        }
                    }
                } else {
                    System.out.println("Error: Unhandled argument counts!");
                }

            } else if (( args.length == 3 ) && 
                    ( args[ 0 ].compareToIgnoreCase("/customer") == 0 ) &&
                    ( args[ 1 ].compareToIgnoreCase("/delete") == 0 )){
                String ID = "";
                java.util.ArrayList<CMS.CustomerInfo> Customers;
                CMS.CustomerInfo Customer;
                String[] Chunks;

                if ( args.length == 3 ){
                    Chunks = args[ 2 ].split("=");

                    if ( Chunks[ 0 ].equalsIgnoreCase("ID") == true){
                        ID = Chunks[ 1 ];
                        if ( ID.length() > 0 ){
                            Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            if ( Customers.size() > 0){
                                DeleteCustomer(Customers, ID);
                                DisplayCustomer(Customers);
                                Export(Customers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            }
                        } else {
                            System.out.println("Error: Invalid argument passed!");
                        }
                    } else {
                        System.out.println("Error:Invalid argument passed!");
                    }
                } else if ( args.length == 2 ) {
                    //interactive
                    java.util.Scanner Scanner = new java.util.Scanner( System.in );
                    
                    System.out.println("To delete a customer enter the following parameters!");
                    System.out.println("");
                    System.out.println("Please enter an ID:");
                    ID = Scanner.nextLine();
                    
                    if ( ID.length() > 0 ){
                        Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            if ( Customers.size() > 0 ){
                                DeleteCustomer(Customers, ID);
                                DisplayCustomer(Customers);
                                Export(Customers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                            }
                    } else {
                        System.out.println("Error: Invalid arguments passed!");
                    }
                    
                } else {
                    System.out.println("Error: Unhandled argument counts!");
                }

            } else if (( args.length == 3 ) &&
                    ( args[ 0 ].compareToIgnoreCase("/customer") == 0 ) &&
                    ( args[ 1 ].compareToIgnoreCase("/archive") == 0 )){
                String ID = "";
                java.util.ArrayList<CMS.CustomerInfo> Customers;
                java.util.ArrayList<CMS.CustomerInfo> Results;
                CMS.CustomerInfo Customer;
                String[] Chunks;

                if ( args.length == 3 ){
                    Chunks = args[ 2 ].split("=");
                    if ( Chunks[ 0 ].equalsIgnoreCase("ID") == true ){
                        ID = Chunks[ 1 ];
                        Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                        if ( Customers.size() > 0 ){
                            Customers = ArchiveCustomer(Customers, ID);
                            DisplayCustomer(Customers);
                            Export(Customers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                        } else { 
                            System.out.println("Error: " + CurrentSettingInfo.getRepository() + " is empty!");
                        }
                    } else {
                        System.out.println("Error: Unhandled arguments passed!");
                    }

                } else if ( args.length == 2 ){
                    //interactively
                    java.util.Scanner Scanner = new java.util.Scanner( System.in );
                    
                    System.out.println("To archive a customer enter the following parameters!");
                    System.out.println("");
                    System.out.println("Please enter an ID:");
                    ID = Scanner.nextLine();
                    
                    if ( ID.length() > 0 ){
                        Customers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                        if ( Customers.size() > 0 ){
                            Customers = ArchiveCustomer(Customers, ID);
                            DisplayCustomer(Customers);
                            Export(Customers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                        } else { 
                            System.out.println("Error: " + CurrentSettingInfo.getRepository() + " is empty!");
                        }
                    } else {
                        System.out.println("Error: Invalid arguments passed!");
                    }
                    
                } else {
                    System.out.println("Error: Unhandled argument counts!");
                }


            } else if (( args[ 0 ].compareToIgnoreCase("/export") == 0 ) &&
                    ( args[ 1 ].compareToIgnoreCase("/format") == 0 )){
                //Store this in a place close to the settingsinfo file but it will be for the export format
                String Format = "";
                java.util.Scanner Scanner = new java.util.Scanner( System.in );
                
                if ( args.length == 3 ){
                     Format = args[ 2 ];
                      if ( Format.length() > 0 ){
                        if ( Format.equalsIgnoreCase("Custom") == true ){
                            CurrentOutputInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setOutputInfo();
                        } else if ( Format.equalsIgnoreCase("CSV") == true ){
                            CurrentOutputInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setOutputInfo();
                        } else if ( Format.equalsIgnoreCase("XML") == true ){
                            CurrentOutputInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setOutputInfo();
                        } else if ( Format.equalsIgnoreCase("JSON") == true ){
                            CurrentOutputInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setOutputInfo();
                        } else {
                            System.out.println("Error: Invalid Format " + Format + "!");
                            help();
                        }
                    } else { 
                        System.out.println("Error: Zero length string!");
                    }
                } else if ( args.length == 2 ){
                    //interactively
                    
                    System.out.println("To change the format of the export settings enter the following!");
                    System.out.println("");
                    System.out.println("Please enter the format for exporting ( I.e. Custom, CSV, XML):");
                    Format = Scanner.nextLine();
                    
                    if ( Format.length() > 0 ){
                        if ( Format.equalsIgnoreCase("Custom") == true ){
                            CurrentOutputInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setOutputInfo();
                        } else if ( Format.equalsIgnoreCase("CSV") == true ){
                            CurrentOutputInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setOutputInfo();
                        } else if ( Format.equalsIgnoreCase("XML") == true ){
                            CurrentOutputInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setOutputInfo();
                        } else if ( Format.equalsIgnoreCase("JSON") == true ){
                            CurrentOutputInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setOutputInfo();
                        } else {
                            System.out.println("Error: Invalid Format " + Format + "!");
                            help();
                        }
                    } else { 
                        System.out.println("Error: Zero length string!");
                    }
                    
                    
                } else {
                    System.out.println("Error: Invalid argument count!");
                }

            } else if (( args[ 0 ].compareToIgnoreCase("/setting") == 0 ) &&
                    ( args[ 1 ].compareToIgnoreCase("/repository") == 0 )){
                String File = "";
                java.util.ArrayList<CMS.CustomerInfo> OldFileCustomers;
                //Also need to read information from file and change location to new repository!
                
                OldFileCustomers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                if ( args.length == 3 ){
                    File = args[ 2 ];
                    if (( File.contains(".") == true) && ( File.length() > 0 )){
                        CurrentSettingInfo.setRepository(File);
                        setSettingInfo();
                    }
                } else if ( args.length == 2){
                    //interactively
                    java.util.Scanner Scanner = new java.util.Scanner( System.in );
                    
                    System.out.println("To change the repository in the settings enter the following information!");
                    System.out.println("");
                    System.out.println("Please enter a valid filename:");
                    File = Scanner.nextLine();
                    
                    if ( (File.length() > 0 ) && ( File.contains(".") == true )){
                        CurrentSettingInfo.setRepository(File);
                        setSettingInfo();
                    } else {
                        System.out.println("Error: Invalid File name!");
                    }
                } else {
                    System.out.println("Error: Invalid argument count");
                    System.out.println("");
                    help();
                }
                
                if ( OldFileCustomers.size() > 0 ){
                    //File has information that needs to be moved to the new file
                    Export(OldFileCustomers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                }
            } else if (( args[ 0 ].compareToIgnoreCase("/setting") == 0 ) &&
                    ( args[ 1 ].compareToIgnoreCase("/format") == 0 )){
                String Format = "";
                java.util.ArrayList<CMS.CustomerInfo> OldFileCustomers;
                //Also need to read infromation from file and change formatting to match
                OldFileCustomers = Import(CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                if ( args.length == 3 ){
                    Format = args[ 2 ];
                    if ( Format.length() > 0 ){
                        if ( Format.equalsIgnoreCase("Custom") == true ){
                            CurrentSettingInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setSettingInfo();
                        } else if ( Format.equalsIgnoreCase("CSV") == true ){
                            CurrentSettingInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setSettingInfo();
                        } else if ( Format.equalsIgnoreCase("XML") == true ){
                            CurrentSettingInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setSettingInfo();
                        } else if ( Format.equalsIgnoreCase("JSON") == true ){
                            CurrentSettingInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setSettingInfo();
                        } else {
                            System.out.println("Error: Invalid Format " + Format + "!");
                            help();
                        }
                    } else { 
                        System.out.println("Error: Zero length string!");
                    }
                    
                    if ( OldFileCustomers.size() > 0 ){
                        //Exporting the pre-existing customers into the file with a new formatting
                        Export(OldFileCustomers, CurrentSettingInfo.getRepository(), CurrentSettingInfo.getFormat());
                    } // there is no information so nothing needs to be done
                
                } else if ( args.length == 2){
                    //interactive
                    java.util.Scanner Scanner = new java.util.Scanner( System.in );
                    
                    System.out.println("To change the repository format enter the following information!");
                    System.out.println("");
                    System.out.println("Please enter a format for the repository (i.e. Custom, CSV, XML):");
                    Format = Scanner.nextLine();
                    
                    if ( Format.length() > 0 ){
                        if ( Format.equalsIgnoreCase("Custom") == true ){
                            CurrentSettingInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setSettingInfo();
                        } else if ( Format.equalsIgnoreCase("CSV") == true ){
                            CurrentSettingInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setSettingInfo();
                        } else if ( Format.equalsIgnoreCase("XML") == true ){
                            CurrentSettingInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setSettingInfo();
                        } else if ( Format.equalsIgnoreCase("JSON") == true ){
                            CurrentSettingInfo.setFormat(Format);
                            //Setting the format to the one just captured
                            setSettingInfo();
                        } else {
                            System.out.println("Error: Invalid Format " + Format + "!");
                            help();
                        }
                    } else {
                        System.out.println("Error: Zero length string!");
                    }
                } else {
                    System.out.println("Error: Invalid argument count");
                    System.out.println("");
                    help();
                }

            } else {
                // Implicit call for help
                CMS.CustomerInfoManager.help();
            }

        } else {
            //Push user into wizard to set up a setting file
            String Repository = "";
            String Format = "";
            boolean results = false;
            java.util.Scanner Scanner = new java.util.Scanner( System.in );
            System.out.println("Wizard for Settings!");
            System.out.println("");
            
            while ( results == false){
                System.out.println("Please enter a file/repository to store customers in:");
                Repository = Scanner.nextLine();

                System.out.println("Please enter a format to save as ( i.e. Custom, CSV, XML, JSON ):");
                Format= Scanner.nextLine();

                if ( ( Repository.length() > 3 ) && ( Repository.contains(".") == true )){
                    CurrentSettingInfo.setRepository(Repository);
                    if ( Format.equalsIgnoreCase("Custom") == true ){
                        CurrentSettingInfo.setFormat(Format);
                        results = true;
                    } else if ( Format.equalsIgnoreCase("CSV") == true ){
                        CurrentSettingInfo.setFormat(Format);
                        results = true;
                    } else if ( Format.equalsIgnoreCase("XML") == true ){
                        CurrentSettingInfo.setFormat(Format);
                        results = true;
                    } else if ( Format.equalsIgnoreCase("JSON") == true ){
                        CurrentSettingInfo.setFormat(Format);
                        results = true;
                    } else {
                        System.out.println("Error: Invalid format please enter one from the list provided!");
                    }
                } else {
                    System.out.println("Error Invalid file name provided!");
                }
            }
            setSettingInfo();
            System.out.println("Wizard Complete: Settings have been initialized!");
            
        }
        
    }
    
    public static java.util.ArrayList<CMS.CustomerInfo> Import( String Repository, String Format ){
        java.util.ArrayList<CMS.CustomerInfo> Results = new java.util.ArrayList<CMS.CustomerInfo>();
        CMS.CustomerInfo CurrentObject;
        java.io.File ImportFile;
        java.io.FileReader FileReader;
        java.io.BufferedReader BufferedReader;
        String Line = "";
        int count;

        String Contents = "";
        
        
        ImportFile = new java.io.File( Repository );
        //Validating that the file does exist
        if ( ImportFile.exists() == true ){
            try {
                FileReader = new java.io.FileReader( ImportFile );
                BufferedReader = new java.io.BufferedReader( FileReader );
                
                if ( Format.equalsIgnoreCase("Custom") == true ){     
                    //Reading file using nio.file
                    java.util.List<String> ContentsAsList;
                    java.nio.file.Path RepositoryPath = java.nio.file.Path.of(Repository);
                    //int count;
                    int incrementVal = 0;
                    int endIndex = 6;
                    try{
                        ContentsAsList = java.nio.file.Files.readAllLines(RepositoryPath);
                        count = 0;
                        for ( String LineRead : ContentsAsList ){
                            count++;
                            if ( count == 6 ){
                                count = 0;
                                for ( int index = incrementVal; index < (endIndex + incrementVal); index++ ){
                                    Contents += ContentsAsList.get(index) + "\n";
                                }
                                incrementVal += 6;
                                CurrentObject = CMS.CustomerInfo.fromCustom(Contents);
                                Results.add(CurrentObject);
                                Contents = "";
                            }
                            
                        }
                        ContentsAsList.clear();
                        
                    } catch ( Exception ex ){
                        System.out.println( ex.toString());
                    }
                        
                } else if ( Format.equalsIgnoreCase("CSV") == true ){
                    
                    while (( Line = BufferedReader.readLine()) != null ){
                        try {
                            CurrentObject = CMS.CustomerInfo.fromCSV(Line);
                            Results.add(CurrentObject);
                            
                        } catch ( Exception ex ){
                            System.out.println("Exception: Failed to import Customer as " + Format + "!");
                            System.out.println( ex.toString() );
                        }
                    }
                    
                } else if ( Format.equalsIgnoreCase("XML") == true ){
                    
                    java.util.List<String> ContentsAsList;
                    java.nio.file.Path RepositoryPath = java.nio.file.Path.of(Repository);
                    int incrementVal = 0;
                    int endIndex = 8;
                    
                    try{
                        ContentsAsList = java.nio.file.Files.readAllLines(RepositoryPath);
                        count = 0;
                        for ( String LineRead : ContentsAsList ){
                            count++;
                            if ( count == 8 ){
                                count = 0;
                                for ( int index = incrementVal; index < (endIndex + incrementVal); index++ ){
                                    Contents += ContentsAsList.get(index);
                                }
                                incrementVal += 8;
                                CurrentObject = CMS.CustomerInfo.fromXML(Contents);
                                Results.add(CurrentObject);
                                Contents = "";
                            }
                            
                        }
                        ContentsAsList.clear();
                    } catch (Exception ex ){
                        System.out.println( ex.toString());
                    }
                    
                } else if ( Format.equalsIgnoreCase("JSON") == true ){
                    //Read the JSON formatting
                    java.util.List<String> ContentsAsList;
                    java.nio.file.Path RepositoryPath = java.nio.file.Path.of(Repository);
                    int incrementVal = 0;
                    int endIndex = 8;
                    try{
                         ContentsAsList = java.nio.file.Files.readAllLines(RepositoryPath);
                         count = 0;
                         for ( String LineRead : ContentsAsList ){
                             count++;
                             if( count == 8 ){
                                 count = 0;
                                 for ( int index = incrementVal; index < ( endIndex + incrementVal ); index++ ){
                                     Contents += ContentsAsList.get(index);
                                 }
                                 incrementVal += 8;
                                 CurrentObject = CMS.CustomerInfo.fromJSON(Contents);
                                 Results.add(CurrentObject);
                                 Contents = "";
                             }
                         }
                    } catch ( Exception ex) {
                        System.out.println( ex.toString() );
                    }
                    
                    
                } else {
                    System.out.println("Error: Unhandled format for importing");
                }
                

                
                BufferedReader.close();
                FileReader.close();
            } catch ( java.io.IOException IOEx ){
                System.out.println( IOEx.toString() );
            }
        }
        
        return( Results );
    }
    
    public static int Export( java.util.ArrayList<CMS.CustomerInfo> Customers, String Repository, String Format ){
        int count = 0;
        java.io.File ExportFile; //Access to the file
        java.io.FileWriter OutputWriter; // Class to write customers to file
        try {
            ExportFile = new java.io.File( Repository ); // Openning the file to write to from settings file
            OutputWriter = new java.io.FileWriter( ExportFile );
            if ( ExportFile.exists() == true ){
                for ( int index = 0; index < Customers.size(); index++ ){
                    //Evaluation the position of this if statement
                    // Validation for the format to output as from the Settings file
                    if ( Format.equalsIgnoreCase("Custom") == true ){
                        OutputWriter.write( Customers.get( index ).toCustom());
                    } else if ( Format.equalsIgnoreCase("CSV") == true ){
                        OutputWriter.write( Customers.get( index ).toCSV());
                    } else if ( Format.equalsIgnoreCase("XML") == true ){
                        OutputWriter.write( Customers.get( index ).toXML());
                    } else if ( Format.equalsIgnoreCase("JSON") == true ){
                        OutputWriter.write( Customers.get( index ).toJSON());
                    } else {
                        System.out.println("Error: Unhandled formatting for export!");
                    }
                    count++; // increment the count "# of customers exported"
                }
            } else {
                System.out.println("Warning: Repository File '" + ExportFile + "' does not exist!");
            }
            OutputWriter.close();
        } catch ( Exception ex ){
            System.out.println("Error: Exception thrown while attempting to write file: ");
            System.out.println( ex.toString() );
        }
        
        
        return( count );
    }
    
    public static java.util.ArrayList<CMS.CustomerInfo> AddCustomer( java.util.ArrayList<CMS.CustomerInfo> Customers , CMS.CustomerInfo Customer ){
        // Purpose: Take the arraylist imported and check if null
        //          if not null (Array size of zero) check for existing id
        //          if null add and export the customer to add
        
        int customersExported = 0;
        //Array to export for the output -- not the repository
        java.util.ArrayList<CMS.CustomerInfo> OutputAdded = new java.util.ArrayList<CMS.CustomerInfo>();
       
        if ( Customers.size() != 0 ){
            //Check for existing customer in Customers arraylist
            if ( Exists(Customers, Customer.getID()) == false){
                //Adding the new customer into arraylist and export then print the Customer Added to the user
                Customers.add(Customer);
                OutputAdded.add(Customer);
                customersExported = Export(OutputAdded, CurrentOutputInfo.getFile(), CurrentOutputInfo.getFormat());                
                System.out.println("Exported " + customersExported + " successfully!");
                DisplayCustomer(OutputAdded);

            } else {
                System.out.println("Error: ID already exist!");
            }
        } else {
            //We received blank arraylist punch new customer into arraylist then export and print the arraylist
            Customers.add(Customer);
            OutputAdded.add(Customer);
            customersExported = Export(OutputAdded, CurrentOutputInfo.getFile(), CurrentOutputInfo.getFormat());                
            System.out.println("Exported " + customersExported + " successfully!");
            DisplayCustomer(OutputAdded);
        }
        //Returning the arraylist with new customer added
        return( Customers );
    }
    
    public static java.util.ArrayList<CMS.CustomerInfo> DeleteCustomer(java.util.ArrayList<CMS.CustomerInfo> Customers, String ID ){
        //Find the matching customer in the arraylist and do the following
        //Customers.remove(Customer)
        java.util.ArrayList<CMS.CustomerInfo> Removed = new java.util.ArrayList<CMS.CustomerInfo>();
        for ( int index = 0; index < Customers.size(); index++){
            if ( Customers.get(index).getID().equalsIgnoreCase(ID) == true ){
                Customers.remove(Customers.get(index));
            }// else do nothing
        }
        
//        java.util.ArrayList<CMS.CustomerInfo> DeleteCustomer;
//        if ( Customers.size() > 0 ){
//            //Search for the customer in the arraylist
//            DeleteCustomer = SearchCustomer(Customers, "ID", Customer.getID());
//
//            for ( int index = 0; index < DeleteCustomer.size(); index++ ){
//                //Remove the customer found in the search
//                Customers.remove( DeleteCustomer.get(index) );
//                System.out.println("Customers Removed from Database: " + DeleteCustomer.size());
//                DisplayCustomer(DeleteCustomer);
//            }
//        } else {
//            
//        }
        
        return( Customers ); 
    }
    
    public static java.util.ArrayList<CMS.CustomerInfo> ArchiveCustomer( java.util.ArrayList<CMS.CustomerInfo> Customers, String ID ){
        if ( ID == null ){
            
        } else {
            //Loop through all objects in arraylist
            //Compare the ID of the object to the passed ID
            // if match then set Status to inactive
            for ( int index = 0; index < Customers.size(); index++ ){
                if ( Customers.get(index).getID().equalsIgnoreCase(ID) == true){
                    Customers.get(index).setStatus(CustomerInfo.CustomerInfoStatus.Inactive);
                }
            }
            
        }
        
        return( Customers );
    } 
    
    public static java.util.ArrayList<CMS.CustomerInfo> UpdateCustomer( java.util.ArrayList<CMS.CustomerInfo> Customers, 
            String Fieldname, String Value, String ID){
        //Need to find the customer based on the ID value provided
        // Then use the fieldname to determine what field to change and apply the value provided
        //Updated is the arraylist to display with the added changes
        java.util.ArrayList<CMS.CustomerInfo> Updated = new java.util.ArrayList<CMS.CustomerInfo>();
        
        if ( Customers.size() > 0 ){
            for ( int index = 0; index < Customers.size(); index++ ){
                if( Customers.get(index).getID().equalsIgnoreCase(ID) == true ){
                    if ( Fieldname.equalsIgnoreCase("Firstname") == true ){
                        Customers.get(index).setFirstname(Value);
                        Updated.add(Customers.get(index));
                    } else if ( Fieldname.equalsIgnoreCase("Lastname") == true ){
                        Customers.get(index).setLastname(Value);
                        Updated.add(Customers.get(index));
                    } else if ( Fieldname.equalsIgnoreCase("Address") == true ){
                        Customers.get(index).setAddress(Value);
                        Updated.add(Customers.get(index));
                    } else if ( Fieldname.equalsIgnoreCase("Email") == true ){
                        Customers.get(index).setEmail(Value);
                        Updated.add(Customers.get(index));
                    } else {
                        System.out.println("Error: Invalide Field passed!");
                    }
                }
            }
            Export(Updated, CurrentOutputInfo.getFile(), CurrentOutputInfo.getFormat());
            DisplayCustomer(Updated);
        } else{
            System.out.println("Error: Zero length arraylist was provided!");
        }
        return( Customers );
    }
     
    public static java.util.ArrayList<CMS.CustomerInfo> SearchCustomer( java.util.ArrayList<CMS.CustomerInfo> Customers, String Field, String Value ){
        CMS.CustomerInfo CurrentCustomer; 
        // Arraylist to return full of matching objects
        java.util.ArrayList<CMS.CustomerInfo> Results = new java.util.ArrayList<CMS.CustomerInfo>();
        for( int index = 0; index < Customers.size(); index++ ){ 
            //Current object ot observe
            CurrentCustomer = Customers.get(index);

            //if statement to compare correct field from object
            if ( Field.equalsIgnoreCase("ID") == true ){
                //Comparison for value to ID
                if ( CurrentCustomer.getID().equalsIgnoreCase( Value ) == true ){
                    Results.add(CurrentCustomer);
                } 
            } else if ( Field.equalsIgnoreCase("Firstname") == true ){
                if ( CurrentCustomer.getFirstname().contains( Value ) == true ){
                    Results.add(CurrentCustomer);

                } 
            } else if ( Field.equalsIgnoreCase("Lastname") == true ){
                if ( CurrentCustomer.getLastname().contains( Value ) == true ){
                    Results.add(CurrentCustomer);

                } 
            } else if ( Field.equalsIgnoreCase("Address") == true ){
                if ( CurrentCustomer.getAddress().contains( Value ) == true ){
                    Results.add(CurrentCustomer);

                } 
            } else if ( Field.equalsIgnoreCase("Email") == true ){
                if ( CurrentCustomer.getEmail().equalsIgnoreCase( Value ) == true ){
                    Results.add(CurrentCustomer);
                } 
            } else {
                System.out.println("Error: Unhandled field to search by!");
            }          
            
        }      
        
        //Telling user the search was not successful
        if ( Results.size() == 0 ){
            System.out.println("Warning: " + Field + " value of " + Value + " cannot be found!");
        }
        
        return( Results );
    }
    
    public static boolean getSettingInfo(){
        // read the contents of an XML file and adjust the application settings     
        // SettingInfo.xml
        // construct a SettingInfo object (or a ton of individual setting variables)
        java.io.File InputFile = new java.io.File("SettingInfo.xml");
        java.io.FileReader InputFileReader = null;
        java.io.BufferedReader InputBufferedReader = null;
        String Contents = "";
        String Line = "";
        boolean results = false;
        
     
        if ( InputFile.exists() == true ){
            
            try {
                InputFileReader = new java.io.FileReader(InputFile);
                InputBufferedReader = new java.io.BufferedReader(InputFileReader);
                
                //java.nio.file.Files.readAllLines ....
                
                // read all lines from file one at a time
                
                while( ( Line = InputBufferedReader.readLine() ) != null){
                    Contents += Line;
                }

                
                //parse the contents of the file using regular expressions
                //      pattern matching
                //  . - any character
                //  ? - 0 or more times
                //  () indicate a capture
                java.util.regex.Pattern regex = java.util.regex.Pattern.compile("<SettingInfo>(.*)</SettingInfo>");          
                // looks at the contents to see if we find the pattern 
                java.util.regex.Matcher matcher = regex.matcher( Contents );
                //  matcher.find(); checks to see if the pattern was matched
                if ( matcher.find() == true ){
                    results = true;
                    // Can only do these pattern matching if we have the file formatting
                    // Pattern matching for the repository information
                    regex = java.util.regex.Pattern.compile("<Repository>(.*)</Repository>");
                    matcher = regex.matcher( Contents );

                    if ( matcher.find() == true ){
                        results = true;
                        // Setting the repository to the information captured in pattern matching
                        CurrentSettingInfo.setRepository(matcher.group(1));
                         
                    }

                    regex = java.util.regex.Pattern.compile("<Format>(.*)</Format>");
                    matcher = regex.matcher( Contents );

                    if ( matcher.find() == true ){
                        results = true;
                        // Setting the format to the information captured in the pattern matching
                        CurrentSettingInfo.setFormat(matcher.group(1));
                    }
                }
                
                InputBufferedReader.close();
                InputFileReader.close();
            } catch ( Exception ex ){
                System.out.println( ex.toString() );
            }
        } else {
            // Option 1: Tell the user to run the "setup" utility
            //      add the appropriate settings
            //      todo: write a first run wizard
            // Option 2: Use default settings
            
        }
        
        return( results );
    }
    
    public static boolean getOutputInfo(){
        // read the contents of an XML file and adjust the application settings     
        // OutputInfo.xml
        // construct a SettingInfo object (or a ton of individual setting variables)
        java.io.File InputFile = new java.io.File("OutputInfo.xml");
        java.io.FileReader InputFileReader = null;
        java.io.BufferedReader InputBufferedReader = null;
        String Contents = "";
        String Line = "";
        boolean results = false;
        
     
        if ( InputFile.exists() == true ){
            
            try {
                InputFileReader = new java.io.FileReader(InputFile);
                InputBufferedReader = new java.io.BufferedReader(InputFileReader);
                
                //java.nio.file.Files.readAllLines ....
                
                // read all lines from file one at a time
                
                while( ( Line = InputBufferedReader.readLine() ) != null){
                    Contents += Line;
                }

                
                //parse the contents of the file using regular expressions
                //      pattern matching
                //  . - any character
                //  ? - 0 or more times
                //  () indicate a capture
                java.util.regex.Pattern regex = java.util.regex.Pattern.compile("<OutputInfo>(.*)</OutputInfo>");          
                // looks at the contents to see if we find the pattern 
                java.util.regex.Matcher matcher = regex.matcher( Contents );
                //  matcher.find(); checks to see if the pattern was matched
                if ( matcher.find() == true ){
                    results = true;
                    // Can only do these pattern matching if we have the file formatting
                    // Pattern matching for the repository information
                    regex = java.util.regex.Pattern.compile("<File>(.*)</File>");
                    matcher = regex.matcher( Contents );

                    if ( matcher.find() == true ){
                        results = true;
                        // Setting the repository to the information captured in pattern matching
                        CurrentOutputInfo.setFile(matcher.group(1));
                         
                    }

                    regex = java.util.regex.Pattern.compile("<Format>(.*)</Format>");
                    matcher = regex.matcher( Contents );

                    if ( matcher.find() == true ){
                        results = true;
                        // Setting the format to the information captured in the pattern matching
                        CurrentOutputInfo.setFormat(matcher.group(1));
                    }
                }
                
                InputBufferedReader.close();
                InputFileReader.close();
            } catch ( Exception ex ){
                System.out.println( ex.toString() );
            }
        } else {
            CurrentOutputInfo.setFile("Output.txt");
            CurrentOutputInfo.setFormat("Custom");
            
        }
        
        return( results );
    }
    
    public static boolean setOutputInfo(){
        // write the contents of an XML file with the updated application settings
        // OutputInfo.xml
        java.io.File SettingInfoFile = new java.io.File("OutputInfo.xml");
        java.io.FileWriter SettingInfoWriter = null;
        
        try {
            //todo: rename the existing settingg file using a datetime...
            SettingInfoWriter = new java.io.FileWriter(SettingInfoFile);
            
            //Outputting file info
            System.out.println( CurrentOutputInfo.toXML() );
            SettingInfoWriter.write( CurrentOutputInfo.toXML() ); 
            
            SettingInfoWriter.close();
        } catch ( Exception ex ){
            System.out.println( ex.toString() );
        }
        boolean Results = true;
        
        return( Results );
    }
            
    public static boolean setSettingInfo(){
        // write the contents of an XML file with the updated application settings
        // SettingInfo.xml
        java.io.File SettingInfoFile = new java.io.File("SettingInfo.xml");
        java.io.FileWriter SettingInfoWriter = null;
        
        try {
            //todo: rename the existing settingg file using a datetime...
            SettingInfoWriter = new java.io.FileWriter(SettingInfoFile);
            
            //Outputting file info
            System.out.println( CurrentSettingInfo.toXML() );
            SettingInfoWriter.write( CurrentSettingInfo.toXML() ); 
            
            SettingInfoWriter.close();
        } catch ( Exception ex ){
            System.out.println( ex.toString() );
        }
        boolean Results = true;
        
        return( Results );
    }

    public static boolean Exists( java.util.ArrayList<CMS.CustomerInfo> Customers, String ID ){
        // Sort through the arraylist and check against the entered ID
        boolean exists = false;
        // Temp Customer Object to use to pull info
        CMS.CustomerInfo CurrentCustomer;
        int index = 0;
        
        do {
            CurrentCustomer = Customers.get(index);
            if ( CurrentCustomer.getID().equalsIgnoreCase( ID ) == true ){
                exists = true;
            }
            index++;
        } while ( index < Customers.size());
        
        return( exists );
    }
    
    //Helper Display
    public static void DisplayCustomer(java.util.ArrayList<CMS.CustomerInfo> Customers){
        
        CMS.CustomerInfo CurrentCustomer;
        //Loop to print through all customers in arraylist
        for ( int index = 0; index < Customers.size(); index++ ){
            CurrentCustomer = Customers.get(index);
            //Display the matches found
            System.out.println("========================");
            if ( CurrentSettingInfo.getFormat().equalsIgnoreCase("Custom") == true ){
                System.out.println(CurrentCustomer.toCustom());
            } else if (CurrentSettingInfo.getFormat().equalsIgnoreCase("XML") == true){
                System.out.println(CurrentCustomer.toXML());
            } else if (CurrentSettingInfo.getFormat().equalsIgnoreCase("CSV") == true){
                System.out.println(CurrentCustomer.toCSV());
            } else if (CurrentSettingInfo.getFormat().equalsIgnoreCase("JSON") == true){
                System.out.println(CurrentCustomer.toJSON());
            } else {
                System.out.println("Error: Invalid format!");
            }
            System.out.println("========================");
        }
        
    }
    
    public static void help(){
        System.out.println("Usage:");
        System.out.println("    All Switches can be ran interactively by leaving the variables off the end (i.e. /customer /add)");
        System.out.println("    /customer /add <ID> <Email>");
        System.out.println("    /customer /add <ID> <Firstname> <Lastname> <Address> <Email>");
        System.out.println("    /customer /search ID=<ID>");
        System.out.println("    /customer /search field=<field> value=<value>");
        System.out.println("    /customer /update ID=<ID> field=<field> value=<value>");
        System.out.println("    /customer /delete ID=<ID>");
        System.out.println("    /customer /archive ID=<ID>");
        System.out.println("    /export /format <Custom | CSV | XML | JSON>");
        System.out.println("    /setting /repository <filename>");
        System.out.println("    /setting /format <Custom | CSV | XML | JSON>");
    }

}

// Helper class 
class SettingInfo {
    
    // add validation for information pushed in
    // public --> private
    
    private String repository;
    private String format;
    
    public SettingInfo(){
        repository = "";
        format = "";
    }
    
    public String toXML(){
        String output = "";
        // add any additional fields that the customer comes up with later...
        output += "<SettingInfo>\n";
        output += "     <Repository>" + repository + "</Repository>\n";
        output += "     <Format>" + format + "</Format>\n";
        output += "</SettingInfo>";
        
        return( output );
    }

    /**
     * @return the repository
     */
    public String getRepository() {
        return (this.repository);
    }

    /**
     * @param repository the repository to set
     */
    public void setRepository(String repository) {
        this.repository = repository;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return (this.format);
    }

    /**
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }
}

//Helper Class for Outputting information
class OutputInfo {
    
    private String file;
    private String format;
    
    public OutputInfo(){
        file = "Output.txt";
        format = "";
    }

    public String toXML(){
        String output = "";
        // add any additional fields that the customer comes up with later...
        output += "<OutputInfo>\n";
        output += "     <File>" + file + "</File>\n";
        output += "     <Format>" + format + "</Format>\n";
        output += "</OutputInfo>";
        
        return( output );
    }
        
    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }
}