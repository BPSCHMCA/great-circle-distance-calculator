# Great circle distance calculator app
It reads the location data from a URL and calculates the distance by using the formula of spherical law of cosines.
Inputs -  
  - URL to a JSON file containing latitude and longitude information along with other metadata (reads from application.properties file)
  - Distance of service area (reads from application.properties file)
  - Path to the file where processed customer data will be written (reads from application.properties file)

Outputs - It filters the customers' data who are outside the range of service area and writes the filtered customer data into a text file at given path.

# Prerequisites
1. Maven is installed on the machine
2. Java 8 is installed on the machine

# Running application
To run the application, follow below steps
1. Go to the project folder
2. Run the command - mvn spring-boot:run

For developers,
1. Import the project as maven project
2. Run the command - mvn install
