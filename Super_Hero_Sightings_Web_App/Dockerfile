# Use OpenJDK 11 image as base image
FROM openjdk:11

#Add the jar file to the container
ADD ./target/Super_Hero_Sightings_Web_App-1.0-SNAPSHOT.jar Super_Hero_Sightings_Web_App-1.0-SNAPSHOT.jar

#Expose the proper port
EXPOSE 8080

#Create a new app directory inside container NOT host machine.
#RUN mkdir /app

#Copy the app files from host machine to image filesystem. {host path} {target path}
#COPY . /app

# Set the directory for executing future commands
#WORKDIR /app

# Set the entry point for our app
ENTRYPOINT ["java", "-jar", "Super_Hero_Sightings_Web_App-1.0-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-jar", "./target/Super_Hero_Sightings_Web_App-1.0-SNAPSHOT.jar"]

# Run the main file
#CMD java App