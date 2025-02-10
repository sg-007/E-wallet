# E-wallet
The ePay Wallet Payment Application aims to provide a robust and user-friendly platform for managing digital transactions, including wallets, users, bank accounts, and navigation data. The system seeks to streamline accessing and organising your finances while ensuring data security, scalability, and performance. To provide an overview of the features and functionalities of the E-wallet Payment Application.

->To analyse the technology stack used in developing ePay, including React, Spring Boot, and MySQL, and evaluate their suitability for the project.

->To assess the usability and user experience of the ePay interface for both librarians and patrons.

->To evaluate the reliability, performance, and scalability of ePay’s backend infrastructure, mainly focusing on Spring Boot and MySQL.

->To examine the security measures implemented in ePay to protect sensitive data and prevent unauthorised access.

->To investigate any challenges encountered during the development and implementation of ePay and the strategies employed to overcome them.

->To gather user feedback regarding their ePay experience and identify improvement areas.

->To propose recommendations for future enhancements and developments of ePay based on the findings and analysis conducted in the report

Dataset In the E-Wallet Payment Application context, the term "dataset" may refer to the collection of data used within the system for various purposes, such as storing information about bank accounts, beneficiaries, bill payments, customers, sessions, and transactions. Here's an elaboration on the dataset used in the system: ->Bank Account Data ->Beneficiary Dataset ->Bill Payment Dataset ->Customer Dataset ->Customer Session Dataset ->Transaction Dataset Dataset in the E-Wallet Payment Application plays a crucial role in storing, organising, and managing various data types essential for system functionality, user interactions, and financial transactions. It forms the backbone of the system's information architecture and enables seamless operation and user experience.

------->Model Description<------------ In the context of the E-Wallet Payment Application, the "Model Description" typically refers to the structure and composition of the data models used to represent various entities within the system. Here's an elaboration on the model description:

->Bank Account Model The Bank Account model represents individual bank accounts linked to the e-wallets. It includes attributes such as account number, IFSC code, bank name, and balance. This model enables the system to manage users' financial accounts, facilitating fund transfers and balance inquiries.

->Beneficiary Model The Beneficiary model represents the beneficiaries added by users for fund transfers. It includes attributes such as beneficiary name, mobile number, and relation. This model allows users to securely transfer funds to saved beneficiaries, enhancing convenience and efficiency in transactions.

->Bill Payment Model The Bill Payment model describes the payments made by users for various bills. It includes attributes such as bill type, amount, payment date, and description. This model facilitates the tracking and management of bill payments, providing users with a comprehensive overview of their financial transactions.

->Customer Model The Customer model represents registered users of the E-Wallet Payment Application. It includes attributes such as customer ID, first name, last name, mobile number, password, date of birth, and gender. This model forms the core of user management, authentication, and profile information storage.

->Customer Session Model The Customer Session model stores information about active user sessions within the application. It includes attributes such as session ID, customer ID, unique ID, and timestamp. This model enables session management and helps track user activity and interactions in real time.

->Transaction Model The Transaction model represents financial transactions initiated by users within the application. It includes attributes such as transaction ID, transaction type, transaction date, amount, description, and receiver's wallet ID. This model records the flow of funds between users' accounts, ensuring transparency and accuracy in financial operations. Overall, the model description outlines the structure and attributes of the data models used within the E-Wallet Payment Application, providing a foundation for secure and efficient financial transactions and user management functionalities.
