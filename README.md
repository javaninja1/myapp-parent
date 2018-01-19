Sample spring-mvc/hibernate application to demo following:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Project Structure:
~~~~~~~~~~~~~~~~~
myapp-parent
  —pom.xml (parent pom: version info for libraries, dependencies, module list)
  —myapp-server (common ORM objects, service, DAO classes, context configs)
  —myapp-weblib (web libraries common to all wars: taglibs, interceptors, filters, resolvers etc. )
  —myapp-war (main war application)
  —myapp-reports-parent (parent pom: for reports microservice; child of myapp-parent)
   — — myapp-reports-server (service, DAO specific to reports micro service)
   — — myapp-reports-war (JSP, controllers to reports micro service)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Feature Catalog:
~~~~~~~~~~~~~~~
Maven
 -parent/child poms
 -adding jars to wars
 -parent and child poms
 -managing dependencies, deployment profiles etc.

MVC
 -Spring mvc controller; Request Mapping annotations
 -Spring form tags and data binding, post and save data
 -Custom method annotation and resolver
 -Interceptor
 -Custom tag using java class
 -Custom tag using tag file
 -Application context listener
 -Lookup bean values in JSP/Controllers/Service layers

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
  -custom query handler using @Repository 

JUnit testing
  -test classes/methods 
  -injecting test-spring-context

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
TODO:
~~~~
-display tablular data using data-tables
-single sign on filter
-gson/message convertors
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

