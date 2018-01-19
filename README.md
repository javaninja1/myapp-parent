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

Config files: server-context xml, persistence.xml, ehcache.xml, bean definitions xml
- hsql datasource
- entity manager factory that uses hsql datasource
- hibernate as JPA vendor
- transaction manager, transaction template, transaction advice 
-lookup beans
-create schema and test data

Service and dao classes
-JPA ORM
-JPA named query
-JPA named query from xml
-JPA named native query from xml
-BaseJpaDAO create common methods using generic-types

-JUnit testing and injecting test-spring-context
-sql-mapping with lombok
-custom query handler

-child jar/war  

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
TODO:
~~~~
-display tablular data use data-tables
-single sign on filter
-gson/message convertors
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

