Sample spring-mvc/hibernate application to demo following:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Completed:
~~~~~~~~~
-parent and child poms

MVC
-Spring mvc controller
-spring form tags and data binding, post and save data
-request handlers
-custom method annotation and resolver
-interceptor
-custom tag using java class
-custom tag using tag file
-application context listener
-lookup bean values in JSP/Controllers/Service layers

Config files: server-context xml, persistence.xml, ehcache.xml, bean definitions xml
  -hsql datasource
  -entity manager factory that uses hsql datasource
  -hibernate as JPA vendor
  -transaction manager, transaction template, transaction advice 
  -lookup beans
  -create schema and test data

Service and DAO Layer
-JPA ORM
-JPA named query
-JPA named query from xml
-JPA named native query from xml
-BaseJpaDAO - common methods using generic-types
-sql-mapping with lombok
-custom query handler

JUnit testing
  -test classes/methods 
  -injecting test-spring-context

Maven
 -parent/child poms
 -adding jars to wars  

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
TODO:
~~~~
-display tablular data using data-tables
-single sign on filter
-gson/message convertors
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

