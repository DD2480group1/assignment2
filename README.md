## DD2480 - Continuous Integration (CI)

# To Compile and Test, please follow the following steps - 


# The Compilation for this project has been implemented and unit-tested in the following way - 
1. Use the CompileProject() and check which system is being used. If it is windows, we need to use a different code
we virtually execute commands to the terminal. We want the results, so we save it from the buffer system and return the results to be able to use the data for the Notification. 

The Compilation has been unit-tested in the following way -
    - Test if the cloning of the repository works
    - Tets to check if compile class returns an errorCode 0. If it i 0, then we know it is correct

# Test Execution has been implemented and unit-tested in the following way -
1. As  soon as we had a different test, we test it, by returning certain variables and error code

The Test Execution has been unit-tested in the following way - 
    - Test if the RepoURL is retrieved correctly from test file
    - Test if the email is retrieved correctly from test file
    - Test if the commit-ID is retrieved correctly from the test file
    - Test if the commit-message is retrieved correctly from the test file

# Notification has been implemented and unit-tested in the following way - 
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

# To run the code and tests, please follow the following steps - 
Firstly, follow these steps to get ngrok to work (i.e the server) https://dashboard.ngrok.com/get-started/setup. Change from port 80 to 8081 when looking at step 3 (on the link).
Secondly, Go to our github repo --> go to setting --> Webhooks --> Add webhook --> add the ngrok url to "Payload URL"


# Grading 
We are aiming for P+, by implementing the 
1. P7 - CI feature, that is the CI server keeps the history of past builds
2. P9 - Property (SE), that is most of our commits are linked to an issue describing the commit

# Contributions -
Lucas Eren :-
1. Compilation of the cloned repo
2. Parsing json and reading data from request body

Viktor Luthman :-
1. 

Isabel Redtzer :-
1. 

Gustav Rubbestad :-
1. Netcat testing
2. Webhook testing
3. CloneRepo

Mayuri Salunke :-
1. 