# DnaMutant
API to detect a mutant in a dna secuence

This API has two main endpoinds:

#### Dna Detector

**Path:** /mutant/

**Description:** method to analize if a dna chain is from a mutant.

**Method:** Post

**Body:** Secuence NxN of the dna e.g:

```
{
"dna":["ATCgAgCaGgAaCaGgAaC","CaGgAaTTcTtgTTcTtgT","TTcTtgACAtGCACAtGCA","ACAaGCCtaCcACtaCcAC","CttCcATcgTcgTcgTcgT","TagTcgATGgAgATGgAgA","ATGgAgATTgAgATTgAgA","CaGgAaCaGgAaCaGgAaC","TTcTtgTTcTtgTTcTtgT","ACAtGCACAtGCACAtGCA","CtaCcACtaCcACtaCcAC","TtgTcgTtgTcgTtgTcgT","ATGgAgATGgAgATGgAgA","CaGgAaCaGgAaCaGgAaC","TTcTtgTTcTtgTTcTtgT","ACAaGCACAaGCACAaGCA","CttCcACttCcACttCcAC","TagTcgTagTcgTagTcgT","ATGgAgATGgAgATGgAgA"]
}
```
**Reponses:**

* 403 Forbidden : Human dna
* 200 Ok : Mutant dna

### Stadistics

**Path:** /stats/

**Description:** method to see the stadistics of the analized dna secuences.

**Method:** get

**Reponses:**


 ```
 {"count_human_dna": human analized quantity,"count_mutant_dna": mutant analized quantity"ratio": mutants ratio}
```

# How to execute the code

#### Database

For database install you need to install docker in your operative system 
[Here](https://docs.docker.com/get-docker/) is a guide to install docker 
<ol>
    <li> execute the command to download postgres image
  
    $ docker pull postgres

<li> execute the command to run docker. You can change the password parameter but this must be modified in the **aplication.properties*** also 

    $ docker run --name Magneto-postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=***admin*** -p 5432:5432  -d postgres

<li> download the pgadmin image to manage the database (optional)

    $ docker pull dpage/pgadmin4

<li> run pgadmin docker

    $ docker run -p 5050:80 -e 'PGADMIN_DEFAULT_EMAIL=admin@magneto.com'  -e 'PGADMIN_DEFAULT_PASSWORD=admin -d dpage/pgadmin4

<li> with pgadmin in http://localhost:5050/login or the client that you prefer execute the sql statements in /src/main/resources/database in the specified order


</ol>

#### Spring project

Execute the project:

<ol>
    <li> in the root project excute:

    $ mvn clean install

<li> In the target folder execute.

    $ java -jar dna-mutant-0.0.1-SNAPSHOT.jar
</ol>

the aplication must be running on the 8080 port in your localhost
