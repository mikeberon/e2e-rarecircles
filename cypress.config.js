const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    baseUrl: "https://portal.rarecircles.com/",
    projectId: "s8i7qb",
    CYPRESS_RECORD_KEY: "0843fe95-9aff-4ab4-8938-74a5aad86a23",
    viewportWidth: 1920,
    viewportHeight: 1080,
    // Default timeout 10 seconds
    defaultCommandTimeout: 10000,
    parallel: true,
    record: false,
    watchForFileChanges: false,
    headless: true,
    chromeWebSecurity: false,
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
});
