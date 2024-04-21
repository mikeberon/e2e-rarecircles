Cypress.on('uncaught:exception', (err, runnable) => {
    // returning false here prevents Cypress from failing the test
    return false;
});

Cypress.Commands.add('loadRegistrationFixtures', () => {
    cy.fixture('registration.json').as('registration');
});


// This command is for auto-generating an email that has a unique "+test[XXXX]" suffix -> 'typeUniqueEmail'
// For easier implementation, I used random numbers 0000 - 9999 and append it to the default email address to be used
Cypress.Commands.add("fillOutRegistrationPage", () => {
    cy.visit('signup'); // Sample registration page
    cy.get('@registration').then((registration) => {
        const randomnum = () => {
            const num = Cypress._.random(0, 9999);
            return num.toString().padStart(4, '0');
        };

        const uniqueEmail = `mikeandrew.beron+test${randomnum()}@gmail.com`;
        cy.xpath(registration.USER.EMAIL_TXTBOX).type(uniqueEmail);
        cy.xpath(registration.USER.TERMS_CHKBOX).check();
        cy.xpath(registration.USER.CONTINUE_BTN).click();
    });
});

Cypress.Commands.add('categoryLocator', (category) => {
    const locator = `//li[@role='option' and contains(text(), '${category}')]`;
    return cy.xpath(locator); 
});

Cypress.Commands.add("fillOutProfilePage", (profileCategory) => {
    cy.get('@registration').then((registration) => {
        cy.xpath(registration.PROFILE.NAME_TXTBOX).type(uniqueEmail);
        cy.xpath(registration.PROFILE.URL_TXTBOX).type(registration.PROFILE.TEST_URL);
        cy.xpath(registration.PROFILE.CATEGORY_DRPDOWN).click();
        cy.categoryLocator(profileCategory).click(); // Now clicking on the category after locating it
        cy.xpath(registration.PROFILE.CREATE_BTN).click();
    });
});

Cypress.Commands.add('programLocator', (program) => {
    const locator = `//p[contains(text(), '${program}')]`;
    return cy.xpath(locator); 
});

Cypress.Commands.add("chooseProgram", (profileProgram) => {
    cy.get('@registration').then((registration) => {
        cy.programLocator(profileProgram).click();
        cy.xpath(registration.PROGRAMS.CONTINUE_BTN).click();
    });
});

Cypress.Commands.add('strategyLocator', (strategy) => {
    const locator = `//p[contains(text(), '${strategy}')]`;
    return cy.xpath(locator); 
});

Cypress.Commands.add("chooseStrategy", (loyaltyStrategy) => {
    cy.get('@registration').then((registration) => {
        cy.strategyLocator(loyaltyStrategy).click();
        cy.xpath(registration.LOYALTY_STRATEGY.NEXT_BTN).click();
    });
});
