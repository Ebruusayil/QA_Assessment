# Selenium Automation Case – Implementation Notes


This project contains an **end-to-end UI automation test** for the Magento demo website using **Selenium + Java + JUnit + Page Object Model (POM)**.

The test scenario covers the full **Guest Checkout flow** from product selection to order confirmation.

## 🔧 Setup & Installation

1️⃣ Prerequisites

Make sure the following tools are installed on your machine:

Java JDK 16 (or higher)

Maven

Google Chrome Browser

Git (optional – for cloning the repo)

IDE (IntelliJ IDEA recommended)

Check installations:

java -version
mvn -version

2️⃣ Clone the Project
git clone <repository-url>
cd <project-folder>

If you received the project as a ZIP file, extract it and open the folder in your IDE.

3️⃣ Install Dependencies

This project uses Maven for dependency management.

Run:

mvn clean install

This command will:

Download Selenium & JUnit dependencies

Compile the project

Prepare test execution

4️⃣ Project Configuration

Key configurations:

Java Version: 16

Build Tool: Maven

Automation Framework: Selenium + JUnit 5

Driver Management: WebDriverManager

ChromeDriver is automatically managed — no manual setup required.

...

## ▶️ How to Run the Test

### ✅ Option A — Run from Terminal (Recommended)

**1. Open terminal in the project root**

Make sure you are in the same folder where pom.xml exists.

Example:

cd C:\Users\...\QA_assesment 2) Run all tests
mvn test

This will execute all JUnit tests under:

src/test/java

### ✅ Option B — Run from IntelliJ IDEA

**1. Open the project**

File → Open → select the project folder

IntelliJ should detect Maven automatically

**2. Make sure Maven dependencies are downloaded**

Open Maven tool window (right side)

Click Reload All Maven Projects (🔄)

**3. Run the test from the test class**

Go to:

src/test/java/tests/GuestCheckoutTest.java

Then:

Click the green ▶️ button next to the class OR the test method

Select Run 'GuestCheckoutTest'
...

## 💡 Additional Notes

## 📌 Test Scenario

1. Open the home page.
2. Navigate to the **Gear > Bags** category.
3. Select a **random product** from the list.
4. Add the selected product to the cart.
5. Open the cart and **update the quantity to 2**.
6. Verify that the total price is updated correctly.
7. Proceed to checkout.
8. Complete the checkout **as a guest** by filling in required form fields using dummy data:
   - Email
   - First Name
   - Last Name
   - Company (Optional)
   - Street Address
   - Country
   - State/Province
   - City
   - Zip Code
   - Phone Number
9. Choose a shipping method and continue.
10. Submit the order.
11. Verify that the order is successfully placed and a confirmation message is shown.

---

## 🧱 Tech Stack

- **Java 16**
- **Selenium 4**
- **JUnit 5**
- **WebDriverManager**
- **Maven**
- **Page Object Model (POM)**

---

## ⏳ Wait Strategy

- Explicit Wait is used via custom `Waits` utility.
- Implicit wait is disabled (`Duration.ZERO`).
- Ensures stable element synchronization.

---

## 🛡️ Cloudflare Handling

A custom wait method detects Cloudflare challenge pages and pauses execution until verification is completed manually.

---

## 💰 Price Validation

Subtotal calculation is validated using a custom `Money` utility class that parses UI price text into `BigDecimal` for accurate comparison.

---

## 📝 Notes

- Random product selection is used to test different products.
- Test data is managed via `TestData` utility.
- Logging is handled with a simple `Log` helper.

**Author:** Ebru Sayil
**Automation Type:** UI / E2E
**Design Pattern:** Page Object Model
