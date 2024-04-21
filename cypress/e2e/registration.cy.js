// Example using generateUniqueEmail
describe('Registration until payment', () => {
    beforeEach(() => {
        // Load registration fixtures
        cy.loadRegistrationFixtures();  
    });

    it('should generate and use a unique email for filling out registration form', () => {
        cy.fillOutRegistrationPage();
        //Choices for category are (case-sensitive): Running, Cycling, Outdoors, Yoga & Wellness, Golf, Ski & Snowboard, Sporting Goods, Specialty Retail, Other
        cy.fillOutProfilePage('Running');
        //Choices for programs are (case-sensitive): Loyalty programs, Community programs
        cy.chooseProgram('Loyalty programs');
        //Choice under Loyalty programs are (case-sensitive): Paid, Points
        cy.chooseStrategy('Paid')
    });





});