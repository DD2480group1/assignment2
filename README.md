# DD2480 - Continuous Integration (CI)

## Description of project
This project implements a small Continuous Integration (CI) server.
The core functionality of the CI server is to fetch, build and test any code that is pushed to the repository.
The CI server also implements a notification system that sends an email to the committer with the results of the build and tests. 

## To Compile and Test, please follow the following steps - 
1. Install ngrok using this https://ngrok.com/download
2. Open a new terminal window and run ngork with the following command - ./ngrok http 8081
3. Take the Forwarding Link found in Forwarding in the ngrok window and add a new webhook in the repo under 
    Settings -> Webhooks -> Add Webhook -> Payload URL
4. Set content type to application/json and save webhook
5. Clone down the repo and start main in the Skeleton code
6. Checkout the branch of assessment, where you can push and receive an email with information about the build and the tests


## Implementation and unit-testing
### Compilation
In order to compile the project it first has to be fetched from Github, and then compiled.
We use the JGit library to accomplish this with the built-in function cloneRepository(), which can take arguments like a URL to the branch of the repo and clone it to a specified path.

When the branch is cloned it is time to build it, this is done by utilizing the Runtime library in Java which can inject terminal commands during runtime. 
We are then able to run ***mvn compile*** on the project and catch the response from the terminal.

The compilation unit-test looks at the exit code after running ***mvn compile***, if it is 0 the compilation was successful, if it is non-zero something went wrong.

### Testing
If the build is successful it is time to run all the tests on the branch.
Ths is done in a similar fashion to the compilation, where we can inject terminal commands using Runtime. 
This time we call ***mvn test*** instead. This results in running all tests on the branch and catching the terminal response with the purpose of forwarding it to the committer.

The testing unit-test works the same way as the compilation unit-test.
Injecting terminal commands with Runtime and then looks at the exit code after running ***mvn test*** to see if all tests passed.

### Notification
The Pre-requisites for the implementation of Notification are the following -

- Java Runtime Environment

In order to the implement the notification feature, the following steps were followed -
1. The dependency 'javax.mail.jar 1.6.2' is added to the POM file
2. A package 'com.sendemail' is created with a class 'SendEmail'
3. The following packages and classes are imported for the code to work
    - import java.util.Properties
    - import javax.mail.Message
    - import javax.mail.MessagingException
    - import javax.mail.PasswordAuthentication
    - import javax.mail.Session
    - import javax.mail.Transport
    - import javax.mail.internet.InternetAddress
    - import javax.mail.internet.MimeMessage
3. We used a JAVA code to send emails using Gmail SMTP server

The Notification has been unit-tested in the following way -
- Test that the email is sent successfully by checking that the sent boolean is set to be true
- Test that emails can't be sent to invalid email_ids

## Contributions
Everyone has contributed to the project and there has been a lot of pair programming. 

Lucas Eren :-
1. Compilation of the cloned repo
2. Parsing json and reading data from request body

Viktor Luthman :-
1. Compilation of the cloned repo
2. Parsing json and reading data from request body

Isabel Redtzer :-
1. Testing of the classes
2. Email Notification
3. Refactoring and Bug Fixing
4. SEMAT document

Gustav Rubbestad :-
1. Netcat and Webhook testing
2. CloneRepo
3. README

Mayuri Salunke :-
1. README
2. Email Notification