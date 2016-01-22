#README for ORCID HUB

Web Application that allows the integration of multiple applications with ORCID.

Live application on https://orcidhub.cineca.it

##USE CASE

![use case](http://i.imgur.com/SlQhIDu.png)


##REST API:

###getTicket

POST /user/{LOCALID}/ticket


Request Class:
```
GetTicketRequestDTO {
firstname (string, optional),
lastname (string, optional),
mail (string, optional),
local-id (string, optional),
url-callback (string, optional),
app-id (string, optional),
org-id (string, optional)
}
```

Response Class
```
GetTicketResponseDTO {
token (string, optional),
orcid (string, optional),
result-code (string, optional),
orcid-access-token (string, optional)
}
```

###Start Oauth

GET /oauth/{ticket}


###getListApps

GET /user/{LOCALID}/listApps


Response Class:
```
GetListAppsResponseDTO {
firstname (string, optional),
lastname (string, optional),
listApp (List[ApplicationDTO], optional),
result-code (string, optional)
}
ApplicationDTO {
name (string, optional),
custom (boolean, optional),
authorized (boolean, optional)
}

```


Project Based on JHipster
Full documentation and information is available on website at http://jhipster.github.io/