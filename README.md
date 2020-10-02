# Analytical tool 

## Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [Author](#author)

### <a name="purpose"></a>Project purpose

Webhosting company provides customer support via email. They record reply waiting time,
type of question, category, and service. To improve customer satisfaction, they need an
analytical tool to evaluate these data. 

<hr>

### Input
The company provides 10 different services, each with 3 variations. Questions are divided into
10 types, each can belong to 20 categories, a category can have 5 subcategories.

First line contains count S (<= 100.000) of all lines.
Every line starts with character <b>C</b> (waiting timeline) or <b>D</b> (query).

##### Waiting timeline:

`C service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date time`

Values in square brackets are optional. <br>
`service_id[.variation_id]` - service 9.1 represent service 9 of variation 1. <br>
`question_type_id[.category_id.[sub-category_id]]` - question type 7.14.4 represent question type 7
category 14 and sub-category 4. <br>
`P/N` - response type ‘P’ (first answer) or ‘N’ (next answer). <br>
`date` - response date format is DD.MM.RRRR, for example, 27.11.2012 (27.november 2012) <br>
`time` - time in minutes represent waiting time.

##### Query line:

`D service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date_from[-date_to]`

Represent query, it prints out the <b>average waiting time</b> of all records matching specific criteria.
`service_id` and `question_type_id` can have special value “\*”, it means query match all
services/question types. In the case of value “\*”, neither service variation nor service
category/subcategory can be specified. 

### Output
Query line of type <b>‘D’</b> print out <b>average waiting time</b> rounded to minutes.
Only matching lines defined <b>before</b> the query line is counted.
It prints out <b>“-”</b> if the output is not defined.

### Example

##### Input:
7 <br>
C 1.1 8.15.1 P 15.10.2012 83 <br>
C 1 10.1 P 01.12.2012 65 <br>
C 1.1 5.5.1 P 01.11.2012 117 <br>
D 1.1 8 P 01.01.2012-01.12.2012 <br>
C 3 10.2 N 02.10.2012 100 <br>
D 1 * P 8.10.2012-20.11.2012<br>
D 3 10 P 01.12.2012 <br>

##### Output:
83 <br>
100 <br>
\-

##### Explanation:
<b>1.query</b> ( D 1.1 8 P 01.01.2012-01.12.2012 ) at line 5: <br>
Valid only for 1.data line, because others have different question type. <br>
1.data line has question type 8.15.1 which matches the query of question type 8. <br>
Result: 83.

<b>2.query</b> ( D 1 * P 8.10.2012-20.11.2012 ) at line 7: <br>
Valid only for 1.data line and 3.data line. <br>
2.data line doesn’t match the date filter. <br>
4.data line has a different response type (<b>D</b>). <br>
5.data line doesn’t match service, response type, and data filter. <br>
Result: (83+117)/2=100.

<b>3.query</b> ( D 3 10 P 01.12.2012 ) at line 8: <br>
Doesn’t match any data line. <br>
Result: “-”. 

<hr>

### <a name="structure"></a>Project structure

- java 11
- maven 4.0.0
- spring-boot 2.3.4.RELEASE
- lombok 1.18.12
- h2 1.4.200
- mockito-core 3.5.13

<hr>

### <a name='author'></a>Author
[Serhii Kinashchuk](https://github.com/serg-ksv)
