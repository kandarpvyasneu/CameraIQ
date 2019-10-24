# CameraIQ

I have used below technologies to develop the application REST API

* Spring Boot REST API with Test Driven Development
* MySQL
* Maven
* Hibernate

Factory design is more suited to this requirement.



To run this application, please change MySQL database credential, Username and Password in application.properties

* To create a single organization please use below JSON in postman

http://localhost:8080/createOrganization

`
{
	"name" : "CameraIQ",
	"address" : "1 Arther Dr, Apt 00, Boston, MA 02215",
	"phone" : "(123) 456 789"
}
`

* To create a single user please use below JSON in postman

`
{
	"address" : "110 North Dr, Apt 00, Boston, MA 02215",
	"email" : "abc.pqr@gmail.com",
	"firstname" : "abc",
	"lastname" : "pqr",
	"phone" : "(098) 765 5432"
}

`

* To add a user to an organization, please use below JSON in postman

`
http://localhost:8080/updateUser/1

where 1 is the userId of an User.

`

{
	"address" : "110 North Dr, Apt 00, Boston, MA 02215",
	"email" : "abc.pqr@gmail.com",
	"firstname" : "abc",
	"lastname" : "pqr",
	"phone" : "(098) 765 5432",
	
	"organization" :
	{
			"id" : 1
	}
}

`

* To delete a user from an organization, please use below JSON in postman

http://localhost:8080/deleteUser/2

where 2 is the userId of an User.

* To read all users who belongs to specific organization, please use below JSON in postman

http://localhost:8080/getAllUser/1

where 1 is the organizationID

* To read a organization to which a user belongs, please use below JSON in postman

http://localhost:8080/getAllOrganization/1

where 1 is the userID
