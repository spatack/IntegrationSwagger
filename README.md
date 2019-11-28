# IntegrationSwagger
## What it it ?
This Project includes Eureka Discovery Server and  Gateway Server
and shows examples :
1.Eureka Discovery Server sends message when Eureka Client Regstered
2.Gateway Server receivs message from Eureka Discovery Server and fetch swagger document.

## Problems
1.I found that Eureka Client will send registry message to Eureka Discovery Server at least twice
which cause Gateway Server will call fetchSwaggerDocument twice.
Maybe I should learn more about Eureka Client Registry.
