# e2e-rarecircles
e2e scripts for assessment in [portal.rarecircles](https://portal.rarecircles.com/)



# Task #1: we want you to automate tests for the signup flow of our
application—https://portal.rarecircles.com/signup
- Done - Implement a solution using one of the following frameworks: selenium, cypress, or
playwright (note: we currently use cypress for our own automation tests) 
- Done - Ensure emails that you use are suffixed with +test[1..N] — eg. -> See commands.js/cy.fillOutRegistrationPage where logic for this is found
pietro+test@rarecircles.com (note: we purge testing accounts on a recurring basis)
- Done - Provide commentary on how your implementation can be improved.
- Done - Provide the generated report. -> This can be easily viewed in the cypress dashboard page

# Task #2: once signed up, we want you to automate tests for our
onboarding flow (until the paywall)
- Done - Implement a solution using one of the following frameworks: selenium, cypress, or
playwright (note: we currently use cypress for our own automation tests)
- Done - Ensure emails that you use are suffixed with +test[1..N] — eg. -> See commands.js/cy.fillOutRegistrationPage where logic for this is found
pietro+test@rarecircles.com (note: we purge testing accounts on a recurring basis)
- Done - Provide commentary on how your implementation can be improved.
- Done - Provide the generated report. - This can be easily viewed in the cypress dashboard page
 

# Setting up repository (VIA NPM)
1. Created repository (https://github.com/mikeberon/e2e-rarecircles.git)
2. git clone https://github.com/mikeberon/e2e-rarecircles.git
3. Open in IDE (VsCode)
4. Run "npm init" in terminal
5. Run "npm install cypress --save-dev" in terminal

# Specs: 
- I have created a registration spec for registration purposes only
- This file contains a somehow e2e flow of registration 

# registration.cy.js - cypress/e2e/registration.cy.js
# Blocker: verification/login code since switching tabs is a limitation of cypress. Also, I am not comfortable logging my personal email with cypress
# The flow is disrupted due to cypress' limitation with switching tabs. Refer to: https://docs.cypress.io/guides/references/trade-offs

# Fixtures: 

# I have 1 fixture for proper organization of elements and locators
# registration.json - cypress/fixtures/registration.json
- This contains elements and locators that are being used in the whole registration flow/process

# Support:
1. commads.js
- reusable functions that will be used across the project/framework
2. e2e.js
- imports that will be used accross the project/framework

# Cypress.config.js
- This contains the default settings for cypress to run and utilize in execution of scripts

# Windows
# Sample way of running scripts via command shell in windows (remove ""): 
- "npx cypress open" (running local)
- "npx cypress run --record --key 0843fe95-9aff-4ab4-8938-74a5aad86a23" (for running in cypress dahsboard)

# Linux (remove "")
- "cypress open"
- "cypress run --record --key 0843fe95-9aff-4ab4-8938-74a5aad86a23"

# Mac (remove "")
- "cypress open"
- "cypress run --record --key 0843fe95-9aff-4ab4-8938-74a5aad86a23"

# Running scripts via VsCode NPM Scripts:
- Open NPM Scripts and click play button beside scripts what you wish to run