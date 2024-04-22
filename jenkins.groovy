pipeline {
    agent {
        label "${test_machine}"
    }

    options {
        timeout(time: 1, unit: 'HOURS')
    }
    
    environment {
        NODE_WORKSPACE = "/home/jenkins/workspace/e2e-rarecircles"
        LATEST_REPORT_DIR_JENKINS = "${REPORTS_DIR_JENKINS}/${BUILD_NUMBER}"
        NPM_GLOBAL = "/home/.npm-global"
        REPORTS_DIR = "/home/jenkins/workspace/e2e-rarecircles/cypress/reports/mocha/.jsons" // Updated report directory
        CYPRESS_PROJECT_ID = 's8i7qb' // Replace with your actual project ID
        CYPRESS_RUN_ID = '' // Initialize Cypress run ID
        CI_BUILD_ID = new Date().getTime().toString() // Capture current timestamp for Cypress build ID
    }

    stages {
        stage('Install Dependencies') {
            steps {
                sh 'cd ../e2e-rarecircles'
                sh 'pwd'
                sh 'curl -fsSL https://deb.nodesource.com/setup_16.x | sudo -E bash -' // Use Node.jssetup script URL
                sh 'sudo apt-get install -y nodejs' // Install Node.js
                
                // Debugging: Print npm configuration
                sh 'npm config list'

                // Debugging: Print Node.js and npm versions
                sh 'node -v'
                sh 'npm -v'

                // Debugging: Print environment variables
                sh 'env'
                
                // Install Cypress and set npm prefix
                sh 'mkdir -p $NPM_GLOBAL' // Create the directory if it doesn't exist
                sh 'npm config set prefix $NPM_GLOBAL'
                sh 'export PATH=$NPM_GLOBAL/bin:$PATH && npm install cypress --save-dev'
            }
        }

        stage('Run Cypress Tests') {
            agent {
                label "${test_machine}"
            }
            steps {
                sh 'npm install cypress --save-dev'
                sh 'cypress install' // Install Cypress binary
                sh "NO_COLOR=1 npx cypress run --browser chrome --parallel --record --key 0843fe95-9aff-4ab4-8938-74a5aad86a23 --ci-build-id ${CI_BUILD_ID}"

            }
        }

    }

    post {   
        success {
            script {
                sendSuccessEmail()
                // publishHTML()
            }
        }
        
        failure {
            script {
                sendFailureEmail()
                // publishHTML()
            }
        }
    }
}

def sendSuccessEmail() {
    // Construct the Cypress Dashboard URL with the run number
    String dashboardUrl = "https://dashboard.cypress.io/projects/${env.CYPRESS_PROJECT_ID}/runs/${env.CYPRESS_RUN_ID}/test-results?"
    
    // Get current date and time with specified formats
    def currentDate = new Date().format('MM-dd-yyyy')
    def currentTime = new Date().format('HH:mm')

    emailext(
        to: env.success_email_recipients,
        subject: "[PASSED][${environment.toUpperCase()}][E2E-RARECIRCLES][CYPRESS][${currentDate}|${currentTime}]",
        body: "E2E - Rarecircles scripts have passed. Please see full report via Cyrpess Dashboard: <a href='${dashboardUrl}'>View Dashboard</a>.",
        mimeType: 'text/html'
    )
}

def sendFailureEmail() {
    // Construct the Cypress Dashboard URL with the run number
    String dashboardUrl = "https://dashboard.cypress.io/projects/${env.CYPRESS_PROJECT_ID}/runs/${env.CYPRESS_RUN_ID}/test-results?"
    
    // Get current date and time with specified formats
    def currentDate = new Date().format('MM-dd-yyyy')
    def currentTime = new Date().format('HH:mm')

    emailext(
        to: env.failed_email_recipients,
        subject: "[FAILED][${environment.toUpperCase()}][USER-CONFIGURATION][CYPRESS][${currentDate}|${currentTime}]",
        body: "E2E - Rarecircles scripts have failed. Please see full report via Cyrpess Dashboard: <a href='${dashboardUrl}'>View Dashboard</a>.",
        mimeType: 'text/html'
    )
}

// def publishHTML() {
//     // Define the HTML content to be saved
//     def htmlContent = """
//     <pre>${currentBuild.rawBuild.getLog()}</pre>
//     """

//     // Save the HTML content using the HTML Publisher plugin
//     publishHTML(
//         target: [
//             allowMissing: false,
//             alwaysLinkToLastBuild: false,
//             keepAll: false,
//             reportDir: 'cypress-logs',
//             reportFiles: 'cypress-log.html',
//             reportName: 'Cypress Logs'
//         ],
//         report: [
//             content: htmlContent,
//             reportFileName: 'cypress-log.html'
//         ]
//     )
// }