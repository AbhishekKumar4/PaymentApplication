#Payment Tracker
Write a program that keeps a record of payments. Each payment includes a currency and an amount. The program should output a list of all the currency and amounts to the console once per minute. The input can be typed into the command line, and optionally also be loaded from a file when starting up.

Sample input:
USD 1000   
HKD 100
USD -100
RMB 2000
HKD 200

Sample output:

USD 900
RMB 2000
HKD 300


#Detailed requirements
When your Java program is run, a filename can be optionally specified. The format of the file will be one or more lines with Currency Code Amount like in the Sample Input above, where the currency may be any uppercase 3 letter code, such as USD, HKD, RMB, NZD, GBP etc. The user can then enter more lines into the console by typing a currency and amount and pressing enter. Once per minute, the output showing the net amounts of each currency should be displayed. If the net amount is 0, that currency should not be displayed.  When the user types "quit", the program should exit.


#Assumptions
1. Values entered by user at the runtime are not saved in our record file.
2. There is no possibility to load new file while the program is running, if user wants to input a new file the program must be restarted.

#Implementation details 
PaymentRecordRepository : Interface which provides common methods for saving a PaymentRecord or getting a list of all PaymentRecord's.
PaymentRecordRepositoryImpl : Provides the implementation of PaymentRecordRepository which uses ConcurrentHashMap for the storage of PaymentRecords.
PaymentRecordParser : Class responsible for parsing all the records and saving them using PaymentRecordRepository.
ConsoleWriter : Is responsible to show the net amount of each currency once per minute it uses PaymentRecordRepositoryReader which exposes method to return the list of all payment records using payment record repository.
 
 
 
 
 
 
 
 
 