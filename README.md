
<h1 align="center">
 DESAFIO DIO MODELAGEM DE BANCO PARA DIO
 <h3 align ="center" >Projeto de modelagem de backend de simulação de aplicação bancária para DIO ☕</h3> 
</h1>

### 📕 SOBRE 
<h4>Desafio da DIO de modelagem  de backend de uma aplicação simulação de banco</h4>



### 🧜‍♀️ **DIAGRAMA(Mermaid)**: 

```mermaid
classDiagram
   class Client {
      -  Long id
      -  String name
      -  String cpf
      -  String address
      -  Set<Account> accounts
    }

    class Account{
    -  Long id;
    - agencyNumber
	- accountNumber
	- balance
    + SalaryAccounts
    + SavingsAccount
    + CheckingAccount
    }
    

    Client --> "1" Account : hasMany
    Account --> "1" SalaryAccounts : hasOne
    Account --> "1" SavingsAccount : hasOne
    Account --> "1" CheckingAccount : hasOne
```

    

    ### 🔨 FERRAMENTAS UTILIZADAS

- [**JAVA**](https://docs.oracle.com/en/java/)
- [**SPRING BOOT**](https://docs.spring.io/spring-boot/index.html)
- [**GIT**](https://git-scm.com/doc)
- [**GITHUB**](https://docs.github.com/pt)
- [**H2**](https://www.h2database.com/html/main.html)
- [**POSTGRESQL**](https://www.h2database.com/html/main.html)
- [**SWAGGER**](https://swagger.io/docs/)
- [**POSTMAN**](https://learning.postman.com/docs/introduction/overview/)

### 👁‍🗨 OS MICROSERVIÇOS ESTARÃO DISPONÍVEIS NAS SEGUINTES URLS

- **hr-worker**: http://localhost:8080/client
- **hr-payroll**: http://localhost:8080/account

#### 🔗 LINKS PARA ACESSO DO SWAGGER
-[SWAGGER FUNCIONÁRIOS](http://localhost:8080/swagger-ui/index.html)

-[SWAGGER PAGAMENTOS](http://localhost:8080/swagger-ui/index.html)

#### 📃 IMAGENS ILUSTRATIVAS SWAGGER
<p>
 <img src ="c:\Users\User\Desktop\DIO\dio_bank\src\main\java\assets\postman1.PNG">
 </p><br>
 <p>
 <img src ="c:\Users\User\Desktop\DIO\dio_bank\src\main\java\assets\swagger.PNG">
 </p><br>


