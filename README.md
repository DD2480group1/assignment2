# DD2480 - Continuous Integration (CI)

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

## The Compilation for this project has been implemented and unit-tested in the following way - 
1. Use the CompileProject() and check which system is being used. If it is windows, a different code will be used
2. We virtually execute commands to the terminal. 
3. Since, we want the results, swe save it from the buffer system and return the results which are then used for the Email Notification. 

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


## History of Past Builds
We did not manage to finish this, but we managed to finish parts of it. All the code related to build history lies in the branch "database". If it would be possible to complete the code for this criteria after the deadline we would like to do so.

We managed to store the history of builds in local files. Each repo gets its own file in the ``db`` folder. But we did not have time to show it in a nice way like a web page and give the history nor individual builds unique urls.
It is however possible to get the build history of a project encoded as JSON by making HTTP get requests to the DatabaseServer. 
It is listening on localhost:8082 and started when along with the SkeletonCode server.

Either the entire history of a repo or a specific commit. 
To get the entire history of the repo ``foo`` you would go to http://localhost:8082/``foo``.
To get a specific commit with id ``x`` you would send a GET request to http://localhost:8082/``foo``/``x``.

We are only storing builds of our own repo ("assignment2"), so the only meaningful url to got to is http://localhost:8082/assignment2.

## Grading 
We are aiming for P+, by implementing the 
1. P7 - CI feature, we partly succeeded in making the CI keep the history of past builds, but we did not finish it completely
2. P9 - Property (SE), that is most of our commits are linked to an issue describing the commit
3. P8 - We have been very creative and proud with our work, as none of the members had any prior knowledge and we have worked collectively to achieve this.

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