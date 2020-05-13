# Analysis and promotion modeling of Russian universities in the QS World Universities Rankings
This repository is devoted to my bachelor's thesis about the promotion of Russian universities in QS Rankings.
The practical part consists of two separate parts.
The first part is the statistical analysis of QS to define crucial metrics and variables of this rankings for further promotion.
The second part is the dynamical modeling web-application of ten-top Russian universities in QS.
Below it is said im more detail about each application

## Statistical Analysis
Statistical analysis of QS rankings was done in Python using the library "matblotlib" to save data in a graphical way.
To conduct statistical analysis, it was applied concepts of statistics.
Below there are some charts are represented as a result of this script (all images are represented in the folder named 'images/second chapter')
## Statistical analysis (some photos)
Regression analysis example

![Alt text](./regression_analysis_example.png)
Correlation analysis example

![Alt text](./correlation_analysis_example.png)
Overall correlation example

![Alt text](./general_correlation_example.png)

## Dynamical modeling promotion of Russian universities web-application
This part of the thesis is devoted to developing a web-application allowing to promote a university in QS World University Ranking by Overall Score as well as by each separate criterion.
Angular is used to develop a client-side app.
Spring is used to develop a server.
PostgreSQL is chosen to keep and operate with data.

## Database structure

Here is represented the database structure diagram which is used in this app

![Alt text](./database_structure.PNG)

# There are some screenshots of the web-app below.

## Dynamical modeling (client-side view)

<b>Home page screenshot (first part)</b>

![Alt text](./1_web.PNG)

<b>Home page screenshot (second part)</b>

![Alt text](./2_web.PNG)

<b>General information (university criteria) about a university example</b>

![Alt text](./3_web.PNG)

<b>General information (university classification) about a university example</b>

![Alt text](./4_web.PNG)

<b>Dynamic modeling promotion example (by Academic Reputation) - first part<b>

![Alt text](./5_web.PNG)

<b>Dynamic modeling promotion example (by Academic Reputation) - second part<b>

![Alt text](./6_web.PNG)

<b>It's possible to export all promotion dynamic models by criterion into an HTML file (example below)<b>

![Alt text](./7_web.PNG)

<b>It's also possible as well to export all promotion dynamic models by criterion into an Excel file (example below)<b>

![Alt text](./8_web.PNG)

##Example of how the app works

<b>Below you can look at the example of this working web-application!<b>

![Alt text](./application_example.gif)

## Client launch
First, just configure and launch Angular CLI Server
Second, you can run this client side part py the command 'ng serve'

## Server launch
1) In order to launch a server, it must be configured a Tomcat server
2) At the first launch, in order to initialize a database, in VM-options it must be written the following: '-Dinitial_start=true'.
This command drops the scema if already exists, initialize the tables and populate them with needed initial values.
3) After the database was initialized, and if the server is run again, the next VM-option must be set in Tomcat configuration: '-Dinitial_start=false'

## Further help
If you have any question regarding this project, do not hesitate to contact me via e-mail 'antonskripin@gmail.com'
