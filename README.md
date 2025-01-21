# online-vehicle-system
This project is a comprehensive vehicle rental system that allows users to book vehicles, manage bookings, and handle user registrations. Below you will find detailed documentation on how to set up, use, and contribute to this project.

## Table of Contents

1. Introduction
2. Prerequisites
3. Set up the database
4. Database Schema
5. Usage
6. Contributing
7. Copyright

## Introduction
The Online Vehicle Rental System is designed to facilitate the rental of vehicles. It includes functionalities for user registration, vehicle management, booking management, and more.

## Prerequisites

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [MySQL](https://www.mysql.com/products/community/)
- [Maven](https://maven.apache.org)

### Installation

1. Clone the repository:

```shell
git clone https://github.com/yourusername/online-vehicle-system.git
```

2. Navigate to the project directory:

```shell
cd online-vehicle-system
```

## Set up the database:

- Create a MySQL database named `vehicle_system`
- Update the database connection details in *DatabaseConnection.java.*
- Run the SQL scripts provided in the sql directory to set up the tables.

3. Build the project using Maven:

```shell
mvn clean install
```
4. Run the application:

```shell
java -jar target/online-vehicle-system-1.0-SNAPSHOT.jar
```
## Database Schema

The database schema includes the following tables:

- users
- vehicle
- payment
- booking

Refer to the `SQL` scripts in the sql directory for detailed table definitions.
## Usage

1. Sign Up and Login:

- Users can sign up and log in to the system.
- Admins can register drivers and manage users.

2. Vehicle Management:

- Admins can register and update vehicles.
- Users can view available vehicles.

3. Booking Management:

- Users can book vehicles, view booking history, and return vehicles.
- Admins can view all bookings and manage bookings.

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository.
2. Create a new branch (git checkout -b feature-branch).
3. Commit your changes (git commit -m 'Add some feature').
4. Push to the branch (git push origin feature-branch).
5. Create a new Pull Request.

## Copyright
Released under the MIT License. See the [LICENSE](https://github.com/ayaankashif/online-vehicle-system/blob/main/LICENSE) file.
