Assignment 1: AI-Powered Text Classification

A Spring Boot application that classifies user-provided text into one of three primary categories: Complaint, Query, or Feedback. The application integrates Google's Gemini 3.8 Flash as its language model, with the AI integration abstracted behind the service layer to keep the model interaction independent of the controller and API-facing logic.

Processing Pipeline

        Client / Postman
               |
               v
       Text Input (JSON)
               |
               v
       ClassificationController
               |
               v
       ClassificationService
               |
               v
       Prompt Construction
               |
               v
       Gemini 3.8 Flash API
               |
               v
       AI-Generated JSON
               |
               v
       Response Parsing
               |
               v
       Category + Confidence
               |
               v
        JSON API Response

Key Implementation Details

REST API: Accepts user-provided text through an HTTP POST endpoint.

AI Integration: Uses Gemini to understand and classify the submitted text.

Structured Output: Requests a JSON response containing the predicted category and confidence score.

Modular Architecture: Separates API handling, AI communication, and response processing into distinct layers.

Model Abstraction: Keeps the underlying AI interaction encapsulated within the service layer.

Testing & Observations

During testing, Gemini exhibited significant response latency and frequently returned HTTP 500 errors. These issues affected the consistency of live demonstrations.

Consequently, Postman demonstration screenshots are provided as evidence of the implementation and API interaction.

Postman Demonstrations:
💠**Demonstration 1: **
![alt text](2.png)

💠**Demonstration 2: **
![alt text](3.png)

