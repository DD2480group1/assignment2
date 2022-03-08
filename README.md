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
7. For any assistance, or if you are unable to add a webhook, please contact the following - 
    Email - isabel.redtzer@gmail.com
    Contact Number - +46 0708680588

## Implementation and unit-testing
### Compilation

### Testing

### Notification

## The Compilation for this project has been implemented and unit-tested in the following way - 
1. Use the CompileProject() and check which system is being used. If it is windows, a different code will be used
2. We virtually execute commands to the terminal. 
3. Since, we want the results, we save it from the buffer system and return the results which are then used for the Email Notification. 

The Compilation has been unit-tested in the  following way -
    - Test if the cloning of the repository works
    - Tets to check if compile class returns an errorCode 0. If it i 0, then we know it is correct

## Test Execution has been implemented and unit-tested in the following way -
1. When we have a different test, we test it by returning certain variables and an error code. 

The Test Execution has been unit-tested in the following way - 
    - Test if the RepoURL is retrieved correctly from test file
    - Test if the email is retrieved correctly from test file
    - Test if the commit-ID is retrieved correctly from the test file
    - Test if the commit-message is retrieved correctly from the test file

## Notification has been implemented and unit-tested in the following way - 
1. The Dependencies were added to the POM file
2. We imported the following packages and classes for the code to work
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


## Contributions -
Everyone has contributed to the project and there has been a lot of pair programming. 

Lucas Eren :-
1. Compilation of the cloned repo
2. Parsing json and reading data from request body

Viktor Luthman :-
1. Compilation of the cloned repo
2. Parsing json and reading data from request body
3. Working on the history database

Isabel Redtzer :-
1. Testing of the classes
2. Email Notification
3. Refactoring and Bug Fixing
4. SEMAT document

Gustav Rubbestad :-
1. Netcat testing
2. Webhook testing
3. CloneRepo
4. Working on the history database

Mayuri Salunke :-
1. README
2. Email Notification