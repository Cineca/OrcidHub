README for ORCID HUB

Web Application that allows the integration of multiple applications with ORCID.

Live application on https://orcidhub.cineca.it

REST API:

**getTicket
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




Project Based on JHipster
Full documentation and information is available on website at http://jhipster.github.io/