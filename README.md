# Nursing Room SG
This is a web application that helps mothers to find nursing rooms in shopping malls around Singapore. 

This is deployed at https://nursing-room-sg.appspot.com/

![nursing-room screenshot](https://user-images.githubusercontent.com/28668724/219826373-42615b12-e98e-4af5-b4bb-add601e384b6.JPG)

# Prerequisites

## MongoDB

### Start mongodb locally
"C:\Users\user\apps\mongodb\bin\mongod.exe" --dbpath="C:\Users\user\data\db"

### Connect to MongoDB Atlas
* Modify `application.properties` with the MongoDB Atlas URL
* Add JVM Args:
  https://stackoverflow.com/questions/65125510/spring-boot-mongodb-connectivity-issue
  `-Djdk.tls.client.protocols=TLSv1.2`

# Running Locally 

## Run Java Server side

- run 
The website can be accessed at http://localhost:8080


# Deploying to GCP (Google Cloud)
project-id: `nursing-room-sg`

```
mvnw -DskipTests package appengine:deploy
```


## First time only
```
gcloud app create --region asia-southeast1
```
