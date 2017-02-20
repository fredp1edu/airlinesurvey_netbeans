# airlinesurvey_netbeans

This was an early project for my first JSP web app class — build a questionnaire website that asked users for opinions on various airline carriers, verify input of required fields, send data to mysql database and with another page, generate a list of all the surveys taken. 

After my second JSP class, I went back to this project, added java beans, added more columns, streamlined connections and created an MVC architecture with servlets doing the work and jsp pages doing the display. I allowed the jsps and servlets to share a database connect via passing the object through session data. Would this be safe in a live deployment?

Next to do:

1. Index the airlineName field in the airline_codes table to make searches faster for the autocomplete form field.

2. The first project I jimmy-rigged a standard html select box to act as an autocomplete component of the airline name input. It’s a bit problematic as the alignment with the input box changes with different browsers and there’s currently no keyboard control for the selections. I can adjust those things or just opt for a pre-made jquery autocomplete setup. Also I have the autocomplete using Ajax to connect to a servlet. Wondering if the Ajax is necessary or can just have the servlet do all the work. 

3. Add more particular verification to both back-end and front-end (email names, flight numbers, no digits in person names, etc.). Clean up javascript verification coding. Finish creating jsp error page in case javas.

4. Have beans pre-clean text inputs for intruder coding (forgot to do that). 

5. Study up on EL and tags, apply if and where necessary. Look into applying Spring framework (once I learn it). 

6. Design and develop a manager login and dashboard page so only manager can look at and modify database data. Move survey display page to manager view only.

7. This is a git of the netbeans version of the app, where it was originally made. Have to make a maven version and then deploy to AWS or google cloud.

