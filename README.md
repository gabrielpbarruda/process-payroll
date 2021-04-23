# Getting Started

### Payroll Management

The system enables the user to register new enterprises and new employees and run payrolls in
conformation with pre-defined requirements

### Reference Documentation

To run the application do:
1. Download this repo
2. Inside the folder run mvn clean package
3. Run ```docker compose up``` (for recent versions of docker), or ```docker-compose up``` inside the terminal
4. Execute queries using a app like [Insominia](https://insomnia.rest/download) or [Postman](https://www.postman.com/downloads/)

### Guides

There are two APIs: enterprises and employees
When the application is running, you can access the data from

```localhost:8081/api/enterprises``` or ```localhost:8081/api/employees```

Running those end points, the system returns a list of enterprises/employees pre-defined inside the database.

* To register an enterprise

1. Call ```localhost:8081/api/enterprises/register```
2. Switch the mapping to **Post**
3. Create an object whith at least:
``` json
{
    "name": "Joao das Neves",
    "fantasyName": "Watch Patrol",
    "email": "watch@email.com",
    "cnpj": "32.100.000/0001-71"
}
```

**OBS**: The fields **email** and **cnpj** must be unique. In case of repetition, the system throws an error message

* To register an employee

1. Call ```localhost:8081/api/employees/register```
2. Switch the mapping to **Post**
3. Create an object whith at least:
``` json
{
    "name": "Romario",
    "cpf": "213.564.879-03",
    "birthday": "1966-01-29",
    "email": "romario@email.com",
}
```

**OBS**: The fields **email** and **cpf** must be unique. In case of repetition, the system throws an error message
