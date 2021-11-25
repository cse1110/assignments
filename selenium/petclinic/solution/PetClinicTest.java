package delft;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PetClinicTest {
    private FindOwnersPage page = new FindOwnersPage(driver);

    protected static WebDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX, true);

    @AfterAll
    static void close() {
        driver.close();
    }

    @Test
    void findOwnersBasedOnTheirLastNames() {
        AddOwnerInfo owner1 = new AddOwnerInfo("John", "Doe", "some address", "some city", "11111");
        AddOwnerInfo owner2 = new AddOwnerInfo("Jane", "Doe", "some address", "some city", "11111");
        addOwners(owner1, owner2);

        page.visit();

        ListOfOwnersPage listPage = page.findOwners("Doe");
        List<OwnerInfo> all = listPage.all();

        assertThat(all).hasSize(2).
                containsExactlyInAnyOrder(owner1.toOwnerInfo(), owner2.toOwnerInfo());
    }

    private void addOwners(AddOwnerInfo... owners) {
        AddOwnerPage addOwnerPage = new AddOwnerPage(driver);

        for (AddOwnerInfo owner : owners) {
            addOwnerPage.visit();
            addOwnerPage.add(owner);
        }
    }

    @Test
    void firstSeleniumTest() {
        // select which driver to use
        WebDriver browser = new HtmlUnitDriver(BrowserVersion.FIREFOX, true);

        // visit a page
        browser.get("http://localhost:8080");

        // find an HTML element in the page
        WebElement welcomeHeader = browser.findElement(By.tagName("h2"));

        // assert it contains what we want
        assertThat(welcomeHeader.getText()).isEqualTo("Welcome");

        // close the browser and the selenium session
        browser.close();
    }


    abstract class PetClinicPageObject {

        protected final WebDriver driver;

        public PetClinicPageObject(WebDriver driver) {
            this.driver = driver;
        }

        public void visit() {
            throw new RuntimeException("This page does not have a visit link");
        }

        protected void visit(String url) {
            driver.get("http://localhost:8080" + url);
            isReady();
        }

        public abstract void isReady();
    }

    class AddOwnerInfo {

        private String firstName;
        private String lastName;
        private String address;
        private String city;
        private String telephone;

        public AddOwnerInfo(String firstName, String lastName, String address, String city, String telephone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.city = city;
            this.telephone = telephone;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public String getTelephone() {
            return telephone;
        }

        public OwnerInfo toOwnerInfo() {
            return new OwnerInfo(firstName + " " + lastName, address, city, telephone, "");
        }
    }

    class OwnerInfo {
        private String name;
        private String address;
        private String city;
        private String telephone;
        private String pets;

        public OwnerInfo(String name, String address, String city, String telephone, String pets) {
            this.name = name;
            this.address = address;
            this.city = city;
            this.telephone = telephone;
            this.pets = pets;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getPets() {
            return pets;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            OwnerInfo ownerInfo = (OwnerInfo) o;
            return Objects.equals(name, ownerInfo.name) &&
                   Objects.equals(address, ownerInfo.address) &&
                   Objects.equals(city, ownerInfo.city) &&
                   Objects.equals(telephone, ownerInfo.telephone) &&
                   Objects.equals(pets, ownerInfo.pets);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, address, city, telephone, pets);
        }

        @Override
        public String toString() {
            return "OwnerInfo{" +
                   "name='" + name + '\'' +
                   ", address='" + address + '\'' +
                   ", city='" + city + '\'' +
                   ", telephone='" + telephone + '\'' +
                   ", pets='" + pets + '\'' +
                   '}';
        }
    }

    class FindOwnersPage extends PetClinicPageObject {

        public FindOwnersPage(WebDriver driver) {
            super(driver);
        }

        public ListOfOwnersPage findOwners(String ownerLastName) {

            driver.findElement(By.id("lastName")).sendKeys(ownerLastName);
            WebElement findOwnerButton = driver.findElement(By.id("search-owner-form")).findElement(By.tagName("button"));
            findOwnerButton.click();

            ListOfOwnersPage listOfOwnersPage = new ListOfOwnersPage(driver);
            listOfOwnersPage.isReady();
            return listOfOwnersPage;
        }

        public AddOwnerPage addOwner() {
            Optional<WebElement> link = driver.findElements(By.tagName("a"))
                    .stream().filter(el -> el.getText().equals("Add Owner")).findFirst();
            link.get().click();

            AddOwnerPage addOwnerPage = new AddOwnerPage(driver);
            addOwnerPage.isReady();
            return addOwnerPage;
        }

        public void visit() {
            visit("/owners/find");
        }

        @Override
        public void isReady() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-owner-form")));
        }
    }

    class AddOwnerPage extends PetClinicPageObject {
        public AddOwnerPage(WebDriver driver) {
            super(driver);
        }

        @Override
        public void visit() {
            visit("/owners/new");
        }

        @Override
        public void isReady() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-owner-form")));
        }

        public OwnerInformationPage add(AddOwnerInfo ownerToBeAdded) {
            driver.findElement(By.id("firstName")).sendKeys(ownerToBeAdded.getFirstName());
            driver.findElement(By.id("lastName")).sendKeys(ownerToBeAdded.getLastName());
            driver.findElement(By.id("address")).sendKeys(ownerToBeAdded.getAddress());
            driver.findElement(By.id("city")).sendKeys(ownerToBeAdded.getCity());
            driver.findElement(By.id("telephone")).sendKeys(ownerToBeAdded.getTelephone());

            driver.findElement(By.id("add-owner-form"))
                    .findElement(By.tagName("button"))
                    .click();


            OwnerInformationPage ownerInformationPage = new OwnerInformationPage(driver);
            ownerInformationPage.isReady();
            return ownerInformationPage;
        }
    }

    class OwnerInformationPage extends PetClinicPageObject {
        public OwnerInformationPage(WebDriver driver) {
            super(driver);
        }

        @Override
        public void isReady() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.textToBe(By.tagName("h2"), "Owner Information"));
        }

        // fully represent the page here...

    }

    class ListOfOwnersPage extends PetClinicPageObject {
        public ListOfOwnersPage(WebDriver driver) {
            super(driver);
        }

        @Override
        public void isReady() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("owners")));
        }

        public List<OwnerInfo> all() {
            List<OwnerInfo> owners = new ArrayList<>();

            WebElement table = driver.findElement(By.id("owners"));
            List<WebElement> rows = table.findElement(By.tagName("tbodsy")).findElements(By.tagName("tr"));

            for (WebElement row : rows) {
                List<WebElement> columns = row.findElements(By.tagName("td"));

                String name = columns.get(0).getText().trim();
                String address = columns.get(1).getText().trim();
                String city = columns.get(2).getText().trim();
                String telephone = columns.get(3).getText().trim();
                String pets = columns.get(4).getText().trim();

                OwnerInfo ownerInfo = new OwnerInfo(name, address, city, telephone, pets);
                owners.add(ownerInfo);
            }

            return owners;
        }
    }


}