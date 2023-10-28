# API Documentation - CRM(Lead management system)

This documentation provides information about the OpenAPI definition for the API.

**Base URL**: [Generated server URL](http://localhost:8080)

**Security**: Bearer Authentication

## Update Lead Contact Information

### Description

This endpoint allows you to update lead contact information.

- **URL**: `/leads/update/{contactInfoId}`
- **Method**: PUT
- **Tags**: lead-controller
- **Operation ID**: updateLeadContactInfo

### Parameters

- `contactInfoId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Request Body

- Content Type: `application/json`
  - Schema:
    ```json
    {
      {
  "companyName": "string",
  "jobPosition": "string",
  "address": "string",
  "city": "string",
  "state": "string",
  "postalCode": 0,
  "region": "string"
}
    }
    ```
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "companyName": "string",
  "jobPosition": "string",
  "address": "string",
  "city": "string",
  "state": "string",
  "postalCode": 0,
  "region": "string",
  "leadCaptureData": {
    "id": 0,
    "name": "string",
    "email": "string",
    "phone": "string",
    "createdDate": "2023-10-28T04:56:36.288Z"
  }
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `LeadContactDTO`
  - Define the schema details for `LeadContactDTO` here.

- `LeadContactInfo`
  - Define the schema details for `LeadContactInfo` here.

---

# Update Lead Capture

This endpoint allows you to update lead capture information.

- **URL**: `/leads/capture/update/{leadCaptureId}`
- **Method**: PUT
- **Tags**: lead-controller
- **Operation ID**: updateLeadCapture

### Parameters

- `leadCaptureId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Request Body

- Content Type: `application/json`
  - Schema:
    ```json
    {
  "name": "string",
  "email": "string",
  "phone": "string"
}
    ```
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "name": "string",
  "email": "string",
  "phone": "string",
  "createdDate": "2023-10-28T05:02:49.998Z"
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `LeadCaptureDTO`
  - Define the schema details for `LeadCaptureDTO` here.

- `LeadCapture`
  - Define the schema details for `LeadCapture` here.




# Previous Lead Stage Transition

This endpoint allows you to transition a lead to the previous stage in the pipeline.

- **URL**: `/api/lead/transition/prev/{leadId}`
- **Method**: PUT
- **Tags**: pipeline-controller
- **Operation ID**: prevLeadStageTransition

### Parameters

- `leadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "LOW",
  "stage": "NEW",
  "source": "string",
  "tag": "string",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "expectedClosingDate": "2023-10-28T05:07:27.487Z",
  "leadContact": {
    "id": 0,
    "companyName": "string",
    "jobPosition": "string",
    "address": "string",
    "city": "string",
    "state": "string",
    "postalCode": 0,
    "region": "string",
    "leadCaptureData": {
      "id": 0,
      "name": "string",
      "email": "string",
      "phone": "string",
      "createdDate": "2023-10-28T05:07:27.487Z"
    }
  }
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `Lead`
  - Define the schema details for the `Lead` object here.



# Next Lead Stage Transition

This endpoint allows you to transition a lead to the next stage in the pipeline.

- **URL**: `/api/lead/transition/next/{leadId}`
- **Method**: PUT
- **Tags**: pipeline-controller
- **Operation ID**: nextLeadStageTransition

### Parameters

- `leadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "LOW",
  "stage": "NEW",
  "source": "string",
  "tag": "string",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "expectedClosingDate": "2023-10-28T05:09:37.998Z",
  "leadContact": {
    "id": 0,
    "companyName": "string",
    "jobPosition": "string",
    "address": "string",
    "city": "string",
    "state": "string",
    "postalCode": 0,
    "region": "string",
    "leadCaptureData": {
      "id": 0,
      "name": "string",
      "email": "string",
      "phone": "string",
      "createdDate": "2023-10-28T05:09:37.998Z"
    }
  }
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `Lead`
  - Define the schema details for the `Lead` object here.




# Lost Lead Stage Transition

This endpoint allows you to transition a lead to the "lost" stage in the pipeline.

- **URL**: `/api/lead/transition/lost/{leadId}`
- **Method**: PUT
- **Tags**: pipeline-controller
- **Operation ID**: lostLeadStageTransition

### Parameters

- `leadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "LOW",
  "stage": "NEW",
  "source": "string",
  "tag": "string",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "expectedClosingDate": "2023-10-28T05:12:37.037Z",
  "leadContact": {
    "id": 0,
    "companyName": "string",
    "jobPosition": "string",
    "address": "string",
    "city": "string",
    "state": "string",
    "postalCode": 0,
    "region": "string",
    "leadCaptureData": {
      "id": 0,
      "name": "string",
      "email": "string",
      "phone": "string",
      "createdDate": "2023-10-28T05:12:37.037Z"
    }
  }
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `Lead`
  - Define the schema details for the `Lead` object here.



# Closed-WON Lead Stage Transition

This endpoint allows you to transition a lead to the "closed-won" stage in the pipeline.

- **URL**: `/api/lead/transition/closedwon/{leadId}`
- **Method**: PUT
- **Tags**: pipeline-controller
- **Operation ID**: closedWONLeadStageTransition

### Parameters

- `leadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "LOW",
  "stage": "NEW",
  "source": "string",
  "tag": "string",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "expectedClosingDate": "2023-10-28T05:14:29.010Z",
  "leadContact": {
    "id": 0,
    "companyName": "string",
    "jobPosition": "string",
    "address": "string",
    "city": "string",
    "state": "string",
    "postalCode": 0,
    "region": "string",
    "leadCaptureData": {
      "id": 0,
      "name": "string",
      "email": "string",
      "phone": "string",
      "createdDate": "2023-10-28T05:14:29.010Z"
    }
  }
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `Lead`
  - Define the schema details for the `Lead` object here.




# Update Activity Status to Complete

This endpoint allows you to update the status of a lead activity to "complete."

- **URL**: `/api/activities/update/status/complete/{capturedLeadId}`
- **Method**: PUT
- **Tags**: lead-activity-controller
- **Operation ID**: updateActivityStatus

### Parameters

- `capturedLeadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "activityType": "EMAIL",
  "createdDate": "2023-10-28T06:03:41.009Z",
  "dueDate": "2023-10-28T06:03:41.009Z",
  "summary": "string",
  "detail": "string",
  "activityStatus": "PENDING",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "lead": {
    "id": 0,
    "name": "string",
    "email": "string",
    "phone": "string",
    "createdDate": "2023-10-28T06:03:41.009Z"
  }
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `LeadActivity`
  - Define the schema details for the `LeadActivity` object here.



# Update Activity Status to Cancel

This endpoint allows you to update the status of a lead activity to "cancel."

- **URL**: `/api/activities/update/status/cancel/{capturedLeadId}`
- **Method**: PUT
- **Tags**: lead-activity-controller
- **Operation ID**: updateActivityStatusCancle

### Parameters

- `capturedLeadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "activityType": "EMAIL",
  "createdDate": "2023-10-28T06:05:21.189Z",
  "dueDate": "2023-10-28T06:05:21.189Z",
  "summary": "string",
  "detail": "string",
  "activityStatus": "PENDING",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "lead": {
    "id": 0,
    "name": "string",
    "email": "string",
    "phone": "string",
    "createdDate": "2023-10-28T06:05:21.189Z"
  }
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `LeadActivity`
  - Define the schema details for the `LeadActivity` object here.


# Update Lead

This endpoint allows you to update lead information.

- **URL**: `/leads/lead/update/{leadId}`
- **Method**: POST
- **Tags**: lead-controller
- **Operation ID**: updateLead

### Parameters

- `leadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int32
  - **Required**: Yes

### Request Body

- Content Type: `application/json`
  - Schema:
    ```json
    {
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "string",
  "stage": "string",
  "source": "string",
  "tag": "string",
  "expectedClosingDate": "2023-10-28T06:08:11.231Z",
  "assignedUser": 0
}
    ```
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "LOW",
  "stage": "NEW",
  "source": "string",
  "tag": "string",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "expectedClosingDate": "2023-10-28T06:08:11.270Z",
  "leadContact": {
    "id": 0,
    "companyName": "string",
    "jobPosition": "string",
    "address": "string",
    "city": "string",
    "state": "string",
    "postalCode": 0,
    "region": "string",
    "leadCaptureData": {
      "id": 0,
      "name": "string",
      "email": "string",
      "phone": "string",
      "createdDate": "2023-10-28T06:08:11.270Z"
    }
  }
}

      ```
  - Description: The request was successful.

## Components

### Schemas

- `LeadDTO`
  - Define the schema details for `LeadDTO` here.

- `Lead`
  - Define the schema details for `Lead` here.


# Create Lead

This endpoint allows you to create a new lead.

- **URL**: `/leads/create/{leadId}`
- **Method**: POST
- **Tags**: lead-controller
- **Operation ID**: createLead

### Parameters

- `leadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int32
  - **Required**: Yes

### Request Body

- Content Type: `application/json`
  - Schema:
    ```json
    {
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "string",
  "stage": "string",
  "source": "string",
  "tag": "string",
  "expectedClosingDate": "2023-10-28T06:10:49.768Z",
  "assignedUser": 0
}
    ```
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "LOW",
  "stage": "NEW",
  "source": "string",
  "tag": "string",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "expectedClosingDate": "2023-10-28T06:10:49.771Z",
  "leadContact": {
    "id": 0,
    "companyName": "string",
    "jobPosition": "string",
    "address": "string",
    "city": "string",
    "state": "string",
    "postalCode": 0,
    "region": "string",
    "leadCaptureData": {
      "id": 0,
      "name": "string",
      "email": "string",
      "phone": "string",
      "createdDate": "2023-10-28T06:10:49.771Z"
    }
  }
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `LeadDTO`
  - Define the schema details for `LeadDTO` here.

- `Lead`
  - Define the schema details for `Lead` here.



# Capture Lead

This endpoint allows you to capture and create a new lead.

- **URL**: `/leads/create/capture`
- **Method**: POST
- **Tags**: lead-controller
- **Operation ID**: captureLead

### Request Body

- Content Type: `application/json`
  - Schema:
    ```json
    {
  "name": "string",
  "email": "string",
  "phone": "string"
}
    ```
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
     {
  "id": 0,
  "name": "string",
  "email": "string",
  "phone": "string",
  "createdDate": "2023-10-28T06:12:57.341Z"
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `LeadCaptureDTO`
  - Define the schema details for `LeadCaptureDTO` here.

- `LeadCapture`
  - Define the schema details for `LeadCapture` here.



# Create Lead Contact Info

This endpoint allows you to create lead contact information.

- **URL**: `/leads/contactInfo/{capturedId}`
- **Method**: POST
- **Tags**: lead-controller
- **Operation ID**: createLeadContact

### Parameters

- `capturedId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int32
  - **Required**: Yes

### Request Body

- Content Type: `application/json`
  - Schema:
    ```json
   {
  "companyName": "string",
  "jobPosition": "string",
  "address": "string",
  "city": "string",
  "state": "string",
  "postalCode": 0,
  "region": "string"
}
    ```
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "companyName": "string",
  "jobPosition": "string",
  "address": "string",
  "city": "string",
  "state": "string",
  "postalCode": 0,
  "region": "string",
  "leadCaptureData": {
    "id": 0,
    "name": "string",
    "email": "string",
    "phone": "string",
    "createdDate": "2023-10-28T06:15:48.311Z"
  }
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `LeadContactDTO`
  - Define the schema details for `LeadContactDTO` here.

- `LeadContactInfo`
  - Define the schema details for `LeadContactInfo` here.



# Get Authentication Token

This endpoint allows you to obtain an authentication token.

- **URL**: `/api/auth/token`
- **Method**: POST
- **Tags**: auth-controller
- **Operation ID**: token

### Request Body

- Content Type: `application/json`
  - Schema:
    ```json
    {
  "username": "string",
  "password": "string"
}
    ```
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "token": "token string",
  "username": "string",
}
      ```
  - Description: The request was successful.

## Components

### Schemas

- `LoginRequest`
  - Define the schema details for `LoginRequest` here.

- `JwtResponse`
  - Define the schema details for `JwtResponse` here.



# User Registration

This endpoint allows users to register for a new account.

- **URL**: `/api/auth/signup`
- **Method**: POST
- **Tags**: auth-controller
- **Operation ID**: registerUser

### Request Body

- Content Type: `application/json`
  - Schema:
    ```json
    {
  "username": "string",
  "password": "string",
  "email": "string",
  "companyname": "string",
  "phone": "string",
  "role": "string",
  "teamname": "string"
}
    ```
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "string"
      }
      ```
  - Description: The request was successful.

## Components

### Schemas

- `SignUpRequest`
  - Define the schema details for `SignUpRequest` here.



## Create Activity

This endpoint allows you to create a new lead activity.

- **URL**: `/api/activities/create/{capturedLeadId}`
- **Method**: POST
- **Tags**: lead-activity-controller
- **Operation ID**: createActivity

### Parameters

- `capturedLeadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Request Body

- Content Type: `application/json`
  - Schema:
    ```json
    {
  "activityType": "EMAIL",
  "activityStatus": "PENDING",
  "dueDate": "2023-10-28T06:22:49.318Z",
  "summary": "string",
  "detail": "string",
  "assignedUser": 0
}
    ```
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "activityType": "EMAIL",
  "createdDate": "2023-10-28T06:22:49.319Z",
  "dueDate": "2023-10-28T06:22:49.319Z",
  "summary": "string",
  "detail": "string",
  "activityStatus": "PENDING",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "lead": {
    "id": 0,
    "name": "string",
    "email": "string",
    "phone": "string",
    "createdDate": "2023-10-28T06:22:49.319Z"
  }
}
      ```
  - Description: The request was successful.

---

## Update Lead (Patch)

This endpoint allows you to update lead information using the PATCH method.

- **URL**: `/leads/lead/patch/{leadId}`
- **Method**: PATCH
- **Tags**: lead-controller
- **Operation ID**: updateLead_1

### Parameters

- `leadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Request Body

- Content Type: `application/json`
  - Schema:
    ```json
    {
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "string",
  "stage": "string",
  "source": "string",
  "tag": "string",
  "expectedClosingDate": "2023-10-28T06:23:49.364Z",
  "assignedUser": 0
}
    ```
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "LOW",
  "stage": "NEW",
  "source": "string",
  "tag": "string",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "expectedClosingDate": "2023-10-28T06:23:49.365Z",
  "leadContact": {
    "id": 0,
    "companyName": "string",
    "jobPosition": "string",
    "address": "string",
    "city": "string",
    "state": "string",
    "postalCode": 0,
    "region": "string",
    "leadCaptureData": {
      "id": 0,
      "name": "string",
      "email": "string",
      "phone": "string",
      "createdDate": "2023-10-28T06:23:49.365Z"
    }
  }
}
      ```
  - Description: The request was successful.



## Get Lead Detail

This endpoint allows you to retrieve detailed information about a lead.

- **URL**: `/leads/lead/{leadId}`
- **Method**: GET
- **Tags**: lead-controller
- **Operation ID**: leadDetail

### Parameters

- `leadId` (Path, Integer)
  - **Type**: Integer
  - **Format**: int64
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
  "id": 0,
  "quote": "string",
  "budget": 1,
  "probability": 0,
  "priority": "LOW",
  "stage": "NEW",
  "source": "string",
  "tag": "string",
  "assignedUser": {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  },
  "expectedClosingDate": "2023-10-28T06:26:37.560Z",
  "leadContact": {
    "id": 0,
    "companyName": "string",
    "jobPosition": "string",
    "address": "string",
    "city": "string",
    "state": "string",
    "postalCode": 0,
    "region": "string",
    "leadCaptureData": {
      "id": 0,
      "name": "string",
      "email": "string",
      "phone": "string",
      "createdDate": "2023-10-28T06:26:37.560Z"
    }
  }
}
      ```
  - Description: The request was successful.

---

## Get All Users

This endpoint allows you to retrieve a list of all users.

- **URL**: `/leads/allusers`
- **Method**: GET
- **Tags**: lead-controller
- **Operation ID**: getUsers

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "array",
        [
  {
    "id": 0,
    "name": "string",
    "password": "string",
    "email": "string",
    "companyname": "string",
    "phone": "string",
    "teamname": "string"
  }
]
      }
      ```
  - Description: The request was successful.

---

## Get All Lead Contacts

This endpoint allows you to retrieve a list of all lead contacts.

- **URL**: `/leads/allcontacts`
- **Method**: GET
- **Tags**: lead-controller
- **Operation ID**: getAllLeadContacts

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "array",
        "items": {
          [
  {
    "id": 0,
    "companyName": "string",
    "jobPosition": "string",
    "address": "string",
    "city": "string",
    "state": "string",
    "postalCode": 0,
    "region": "string",
    "leadCaptureData": {
      "id": 0,
      "name": "string",
      "email": "string",
      "phone": "string",
      "createdDate": "2023-10-28T06:28:21.299Z"
    }
  }
]
      }
      ```
  - Description: The request was successful.



## Search Leads by Source

This endpoint allows you to search for leads by their source.

- **URL**: `/api/lead/search/source`
- **Method**: GET
- **Tags**: pipeline-controller
- **Operation ID**: searchLeadsBySource

### Query Parameters

- `source` (Query, String)
  - **Type**: String
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "array",
        "items": [
  {
    "id": 0,
    "quote": "string",
    "budget": 1,
    "probability": 0,
    "priority": "LOW",
    "stage": "NEW",
    "source": "string",
    "tag": "string",
    "assignedUser": {
      "id": 0,
      "name": "string",
      "password": "string",
      "email": "string",
      "companyname": "string",
      "phone": "string",
      "teamname": "string"
    },
    "expectedClosingDate": "2023-10-28T06:30:21.129Z",
    "leadContact": {
      "id": 0,
      "companyName": "string",
      "jobPosition": "string",
      "address": "string",
      "city": "string",
      "state": "string",
      "postalCode": 0,
      "region": "string",
      "leadCaptureData": {
        "id": 0,
        "name": "string",
        "email": "string",
        "phone": "string",
        "createdDate": "2023-10-28T06:30:21.130Z"
      }
    }
  }
]
      }
      ```
  - Description: The request was successful.



## Search Leads by Company Name

This endpoint allows you to search for leads by their company name.

- **URL**: `/api/lead/search/company`
- **Method**: GET
- **Tags**: pipeline-controller
- **Operation ID**: searchLeadsByCompanyName

### Query Parameters

- `companyname` (Query, String)
  - **Type**: String
  - **Required**: Yes

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "array",
        "items": [
  {
    "id": 0,
    "quote": "string",
    "budget": 1,
    "probability": 0,
    "priority": "LOW",
    "stage": "NEW",
    "source": "string",
    "tag": "string",
    "assignedUser": {
      "id": 0,
      "name": "string",
      "password": "string",
      "email": "string",
      "companyname": "string",
      "phone": "string",
      "teamname": "string"
    },
    "expectedClosingDate": "2023-10-28T06:32:09.835Z",
    "leadContact": {
      "id": 0,
      "companyName": "string",
      "jobPosition": "string",
      "address": "string",
      "city": "string",
      "state": "string",
      "postalCode": 0,
      "region": "string",
      "leadCaptureData": {
        "id": 0,
        "name": "string",
        "email": "string",
        "phone": "string",
        "createdDate": "2023-10-28T06:32:09.835Z"
      }
    }
  }
]
      }
      ```
  - Description: The request was successful.



## Get All Leads

This endpoint allows you to retrieve a list of all leads.

- **URL**: `/api/lead/all`
- **Method**: GET
- **Tags**: pipeline-controller
- **Operation ID**: getAllLeads

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "array",
        "items": [
  {
    "id": 0,
    "quote": "string",
    "budget": 1,
    "probability": 0,
    "priority": "LOW",
    "stage": "NEW",
    "source": "string",
    "tag": "string",
    "assignedUser": {
      "id": 0,
      "name": "string",
      "password": "string",
      "email": "string",
      "companyname": "string",
      "phone": "string",
      "teamname": "string"
    },
    "expectedClosingDate": "2023-10-28T06:35:01.075Z",
    "leadContact": {
      "id": 0,
      "companyName": "string",
      "jobPosition": "string",
      "address": "string",
      "city": "string",
      "state": "string",
      "postalCode": 0,
      "region": "string",
      "leadCaptureData": {
        "id": 0,
        "name": "string",
        "email": "string",
        "phone": "string",
        "createdDate": "2023-10-28T06:35:01.075Z"
      }
    }
  }
]
      }
      ```
  - Description: The request was successful.



## Get Closed-Won Lead Count

This endpoint allows you to retrieve the count of closed-won leads.

- **URL**: `/api/dash/won-closed`
- **Method**: GET
- **Tags**: dashboard-controller
- **Operation ID**: getClosedWonLeadCount

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "integer",
        "format": "int64"
      }
      ```
  - Description: The request was successful.



## Get Revenue by Stage

This endpoint allows you to retrieve revenue data categorized by lead stage.

- **URL**: `/api/dash/revenue`
- **Method**: GET
- **Tags**: dashboard-controller
- **Operation ID**: getRevenueByStage

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "object",
        "additionalProperties": {
          "type": "number",
          "format": "double"
        }
      }
      ```
  - Description: The request was successful.

---

## Get New Lead Count

This endpoint allows you to retrieve the count of new leads.

- **URL**: `/api/dash/new`
- **Method**: GET
- **Tags**: dashboard-controller
- **Operation ID**: getNewLeadCount

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "integer",
        "format": "int64"
      }
      ```
  - Description: The request was successful.

---

## Get Closed Lost Lead Count

This endpoint allows you to retrieve the count of closed-lost leads.

- **URL**: `/api/dash/lost-closed`
- **Method**: GET
- **Tags**: dashboard-controller
- **Operation ID**: getClosedLostLeadCount

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "integer",
        "format": "int64"
      }
      ```
  - Description: The request was successful.

---

## Get Lead Count in Each Stage

This endpoint allows you to retrieve lead counts categorized by each lead stage.

- **URL**: `/api/dash/lead/stage`
- **Method**: GET
- **Tags**: dashboard-controller
- **Operation ID**: getLeadCountInEachStage

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "object",
        "additionalProperties": {
          "type": "integer",
          "format": "int64"
        }
      }
      ```
  - Description: The request was successful.

---

## Get All Data

This endpoint allows you to retrieve all dashboard data.

- **URL**: `/api/dash/info`
- **Method**: GET
- **Tags**: dashboard-controller
- **Operation ID**: getAllData

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "$ref": "#/components/schemas/DashboardDTO"
      }
      ```
  - Description: The request was successful.

---

## Get Expected Revenue by Stage

This endpoint allows you to retrieve expected revenue data categorized by lead stage.

- **URL**: `/api/dash/expectedrevenue`
- **Method**: GET
- **Tags**: dashboard-controller
- **Operation ID**: getExpectedRevenueByStage

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "object",
        "additionalProperties": {
          "type": "number",
          "format": "double"
        }
      }
      ```
  - Description: The request was successful.



## Get All Activity Logs

This endpoint allows you to retrieve all activity logs associated with a captured lead.

- **URL**: `/api/activities/log/{capturedLeadId}`
- **Method**: GET
- **Tags**: lead-activity-controller
- **Operation ID**: getAllLog

### Parameters

- **capturedLeadId** (Path Parameter)
  - Type: `integer`
  - Format: `int64`
  - Required: true

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "array",
        "items": {
          "$ref": "#/components/schemas/ActivityLog"
        }
      }
      ```
  - Description: The request was successful.

---

## Get All Lead Activities

This endpoint allows you to retrieve all lead activities.

- **URL**: `/api/activities/all`
- **Method**: GET
- **Tags**: lead-activity-controller
- **Operation ID**: getAllActivities

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "array",
        "items": {
          "$ref": "#/components/schemas/LeadActivity"
        }
      }
      ```
  - Description: The request was successful.



## Get All Activities by Captured Lead ID

This endpoint allows you to retrieve all lead activities by the captured lead's ID.

- **URL**: `/api/activities/all/{capturedLeadId}`
- **Method**: GET
- **Tags**: lead-activity-controller
- **Operation ID**: getAllActivitiesbyId

### Parameters

- **capturedLeadId** (Path Parameter)
  - Type: `integer`
  - Format: `int64`
  - Required: true

### Responses

- **200 OK**
  - Content Type: `*/*`
    - Schema:
      ```json
      {
        "type": "array",
        "items": {
          "$ref": "#/components/schemas/LeadActivity"
        }
      }
      ```
  - Description: The request was successful.

---
