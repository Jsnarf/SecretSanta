# SecretSanta
Scala/SBT project to randomly assigned people to make gifts between each other under constraints and send them emails

# Scope of the project 

This project named Secret Santa has the aim to provide a random mapping for a bunch of people that want to make gifts between each other and send emails to warn each person
about its mapping, the whole thing under some constraints.  
The program will iterate many times to find one possible combinations, nevertheless if constraints are too heavy, a logging message will say that no configuration could be 
found and no emails are sent.  
The program is basic, everything is made from the configuration file (read at runtime) and lasts for few seconds   
(basically between 10 persons and 4 constraints, it is 2 seconds of runtime and emails are sent asynchronously but within the 15 seconds of the start of the program)  

## A concrete example

It is always better with a concrete example !   
Let's say that Marshall, Lily, Ted, Robin and Barney, form a group of friends and they want for Christmas to make some gifts between each other.  
Nevertheless, they do not want to make gifts to everyone and so they decided to make a secret random assignation (i.e. Each person will receive the name of the person 
to who he/she will buy a gift and will keep it secret until they offer gifts together) but Robin is always unavailable and so making a physical draw is impossible.  
Besides, Marshall and Lily (who form a couple) do not want to make gifts between them because they already do it as a couple.  
Moreover, having a configuration where Ted will give to Barney and Barney will give to Ted is not really nice and it will be better if those "loops" are avoided.  
(And for sure, if Ted should not give to Ted will also be better)

The program "Secret Santa" is made to solve this type of problems, no matter the number of persons and constraints.


# How does it works ? 

It is a simple scala program that run in a few seconds and makes a mapping between people that will give a gift and other that will receive it  
Givers and receivers are considered as the same  
There are some constraints that could be configured by users : who cannot give to who  
There is one constraint : one person who gives to another one, cannot receive from the one he gives  
Then, the program runs and launches one email per person  
Hence, the program will need an email adress with password to send those emails
 

## Configuration files

Before running it, you have to configure the program with email-addresses, password, message and constraints  
Everything will be made in application.conf and it will take at most 5 minutes :) 

### Users and e-mails

Modify the variable `namailList` as follow :   
Delete the secret santa line : `"Secret Santa:secretsantaperlimpinpin@gmail.com"`  
Add your users with their name and email addresses according to this pattern :
    `"UserName:user@email.com"`  
If we take back our example, it will be : 
    
    "barney:barney.stinson@howimetyourmother.com"
     "ted:ted.mosby@howimetyourmother.com"
     "lily:lily.aldrin@howimetyourmother.com"
     "marshall:marshall.eriksen@howimetyourmother.com"
     "robin:robin.scherbatsky@howimetyourmother.com"
     
### Constraints
     
Maybe, for some good reasons, you want that some person(s) won't give a gift to some other(s)       
In our example, it is useless that marshall and lily makes gifts each other, because as a couple, they have already some gifts to do between each other for Christmas  
To write these constraints, you have to modify the variable `forbidden` as follow :   
Delete the line : `"user1:user2"`  
Add your users that do not want to give to other(s) according to this pattern :  
    `"userName1:userName2"`  
Which means that `userName1` won't give to `userName2`  
It is also possible to inserts some users by separating userNames by a `,`  
In our example, let's say that Marshall won't give anything to Lily and Ted, Lily won't give anything to Marshall:  
   
      "marshall:lily,ted"
      "lily:marshall"
      
Be careful and make sure that names are the same (be respectful to lower and upper case also) between constraints and users

      
### Sender's email
      
In order to send mails, an email will be required, hence fill in those two properties (`email` and `password`) with your email address or one especially created for it,
and the right associated password

### Subject and message 

Fill in those two properties: `subject` and `message` with whatever you want  
Subject will be added to email's subject, as subject still starts with : ` Secret Santa : `  
If you place the words `name_receiver` and `name_giver` , they will be replaced by the real names of the receiver and giver  
Be careful ! If you don't set name_receiver in the message, the user won't know to who he should buy a gift  
      
    

## How to launch it ?

1. Make sure you have scala and sbt installed on your PC 
2. Go the root of the project and launch :

       sbt clean
       sbt compile
       sbt test
      
3. Check compilation and test are ok, then run it with :
 
        sbt run
     
4. Checks outputs (logs) on console and verify on your email address that you do not have returned email(s) saying sending to one address was not possible
      

# Some references and documentation 
 
[SBT official website](http://www.scala-sbt.org/0.13/docs/Getting-Started.html)  
[Scala official website](https://www.scala-lang.org/)  
[Mailing library gitHub](https://github.com/softprops/courier)  
[Configuration library gitHub](https://github.com/lightbend/config)  
[Log4J scala gitHub](https://github.com/apache/logging-log4j-scala)  


Feel free to contribute, respecting the [contributing.md]()
