Assignment 2: AI-Powered Email Template Generator

A Spring Boot microservice that generates short, customer-friendly email templates using Google's Gemini API. It accepts a few structured input fields and generates an email tailored to the specified purpose, recipient, and communication tone.

The service uses recipient_name to personalize the email and generates an appropriate subject and body based on the supplied information.

Processing Pipeline

          Client / Postman
                 |
                 v
         POST /api/email/generate
                 |
                 v
           EmailController
                 |
                 v
         Request Validation
                 |
                 v
            EmailService
                 |
                 v
          Prompt Construction
                 |
                 v
           Gemini REST API
                 |
                 v
         AI-Generated JSON
                 |
                 v
          Response Parsing
                 |
                 v
       Subject + Email Body
                 |
                 v
       Response Time Calculation
                 |
                 v
          JSON API Response

Input Parameters

Parameter

Description

purpose

Describes the objective of the email.

recipient_name

Specifies the recipient's name for personalization.

tone

Defines the desired communication style, such as Professional, Friendly, or Formal.

Key Implementation Details

REST API: Exposes an HTTP POST endpoint for email generation.

AI Integration: Uses Gemini to generate context-aware email content.

Prompt Engineering: Dynamically constructs prompts using the supplied purpose, recipient name, and tone.

Structured JSON Output: Generates an email subject and body in a predictable JSON format.

Response Time Logging: Measures the time taken by the Gemini API call and includes the duration in the API response.

Modular Architecture: Maintains separation between request handling, business logic, and AI integration.

Configuration Management: Stores API configuration and credentials outside the application source code using YAML configuration and environment variables.

Postman Demonstration

The API was tested using Postman to verify request handling and the generated email response.

Demonstration: 
![alt text](../1.png)
