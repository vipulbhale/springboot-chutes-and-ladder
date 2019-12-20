# Chutes and Ladders(Standalone application using springboot)
This application is a standalone java application(using Springboot) simulating Chutes and Ladders game.


###Assumptions
- This simulation doesn't have any GUI component.
- It assumes only 2-4 players.
- It assumes spinner is going to spin and give the value from 1-6.
- Chutes and Ladder configuration has to be given in the form of a json file referenced via **location.chutesandladder** property inside the application*.properties file in resources directory. A sample and explanation follows as below:
It is essentially an array of multiple configurations. Game picks up the configuration Randomly. If you are not sure. Please keep only one configuration as kept in the project.
Every configuration has id and within it is an array of construct depicting moveType i.e. either a chute or ladder. Below sample shows a chute and a ladder. Chute has startSquare greater than endsquare and Ladder is vice versa. Id for every chute and ladder should be unique. Type specifies whether its a chute or ladder
```json
[
  {
    "configId": 1,
    "configuration": [
      {
        "id": 1,
        "type": "ladder",
        "startSquare": 1,
        "endSquare": 38
      },
      {
        "id": 2,
        "type": "chute",
        "startSquare": 99,
        "endSquare": 10
      }
    ]
  }
]
``` 

-  Number of squares on the board can be changed by a property **square.total** in the application*.properties file. 
### Prerequisites
To build and compile maven, Java 8

### How to setup the project in IDE
``` git
    git clone https://github.com/vipubhale/springboot-chutes-and-ladder.git
```
 Import it as a maven project in Intellij or eclipse
 
### How to run tests
```bash
mvn clean test verify
```
### How to run it locally
- ####on mac or linux 
```bash
git clone https://github.com/vipubhale/springboot-chutes-and-ladder.git
cd springboot-chutes-and-ladder  # parallel to pom.xml
mvn clean install
cd ~/.m2/repository/com/candidate/priceline/springboot-chutes-and-ladder/1.0-SNAPSHOT/
unzip springboot-chutes-and-ladder-1.0-SNAPSHOT-distribution.zip
cd springboot-chutes-and-ladder-1.0-SNAPSHOT/unix/bin
```
./run.sh args #**args are player names(2 to 4) separated by space** for e.g.

```bash
./run.sh JOHN JOE DAVID BOB
```
Run it in lower(dev and qa) environments. Please make sure application.**environmentName**.properties file exists in src/main/resources directory
```bash
git clone https://github.com/vipubhale/springboot-chutes-and-ladder.git
cd springboot-chutes-and-ladder  # parallel to pom.xml
mvn clean install
cd ~/.m2/repository/com/candidate/priceline/springboot-chutes-and-ladder/1.0-SNAPSHOT/
unzip springboot-chutes-and-ladder-1.0-SNAPSHOT-distribution.zip
cd springboot-chutes-and-ladder-1.0-SNAPSHOT/unix
java -Denvironment=<environmentname> -jar ./springboot-chutesladders-0.0.1-SNAPSHOT.jar JOHN JOE PETER DAVID
```

- ####on windows 
```bash
git clone https://github.com/vipubhale/springboot-chutes-and-ladder.git
cd springboot-chutes-and-ladder  # parallel to pom.xml
mvn clean package
cd target/
unzip springboot-chutes-and-ladder-1.0-SNAPSHOT-distribution.zip
cd springboot-chutes-and-ladder-1.0-SNAPSHOT/windows
java -jar springboot-chutesladders-0.0.1-SNAPSHOT.jar 
```
java -jar springboot-chutesladders-0.0.1-SNAPSHOT.jar args #**args are player names(2 to 4) separated by space** for e.g.

```bash
java -jar springboot-chutesladders-0.0.1-SNAPSHOT.jar JOHN JOE PETER DAVID
```

Run it in lower(dev and qa) environments. Please make sure application.**environmentName**.properties file exists in src/main/resources directory
```bash
git clone https://github.com/vipubhale/springboot-chutes-and-ladder.git
cd springboot-chutes-and-ladder  # parallel to pom.xml
mvn clean install
cd ~/.m2/repository/com/candidate/priceline/springboot-chutes-and-ladder/1.0-SNAPSHOT/
unzip springboot-chutes-and-ladder-1.0-SNAPSHOT-distribution.zip
cd springboot-chutes-and-ladder-1.0-SNAPSHOT/windows
java -Denvironment=<environmentname> -jar ./springboot-chutesladders-0.0.1-SNAPSHOT.jar JOHN JOE PETER DAVID
```
### TODO
- Add tests for random pick up of chutes and ladder configuration from multiple configuration.
- Create a windows batch file for this app.
- Add Jenkinsfile to make it easy to deploy on Jenkins.
