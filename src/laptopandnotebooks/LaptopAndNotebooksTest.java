package laptopandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopAndNotebooksTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    /*
    1. Test name verifyProductsPriceDisplayHighToLowSuccessfully()
     * 1.1 Mouse hover on Laptops & Notebooks Tab.and click
     * 1.2 Click on “Show All Laptops & Notebooks”
     * 1.3 Select Sort By "Price (High > Low)"
     * 1.4 Verify the Product price will arrange in High to Low order.
     */
    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException {

        mouseHoverToElementAndClick(By.xpath("//span[normalize-space()='Currency']"));//Mouse hover on Currency Dropdown and click
        mouseHoverToElementAndClick(By.xpath("//button[normalize-space()='£Pound Sterling']"));//Mouse hover on £Pound Sterling and click
        mouseHoverToElementAndClick(By.xpath("//a[normalize-space()='Laptops & Notebooks']")); //Mouse hover on Laptops & Notebooks Tab.and click
        clickOnElement(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']"));  //Click on “Show All Laptops & Notebooks”
        List<WebElement> beforeFilterProductPrice = getMultipleElements(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//div[1]//div[2]//div[1]//p//span[@class='price-tax']"));

        List<Double> beforeFilterProductPriceList = new ArrayList<>();//Create arraylist
        //Store elements text to array list
        for (WebElement p : beforeFilterProductPrice) {
            String beforeFilterPrice = p.getText().replaceAll("[E,x,T,a,x,£,:,$]", "").replace(",", "");
            Double priceValueBeforeFilter = Double.parseDouble(beforeFilterPrice);
            beforeFilterProductPriceList.add(priceValueBeforeFilter);
        }

        Collections.sort(beforeFilterProductPriceList);//Sort arraylist to ascending oreder
        Collections.reverse(beforeFilterProductPriceList); //Reverse the list
        //Select Sort By position "Price high to low"
        selectByValueFromDropDown(By.id("input-sort"), "https://tutorialsninja.com/demo/index.php?route=product/category&path=18&sort=p.price&order=DESC");
        Thread.sleep(2000);
        //Store elements after filtering
        List<WebElement> afterFilterProductPrice = getMultipleElements(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//div[1]//div[2]//div[1]//p//span[@class='price-tax']"));
        //Create anothor list to store text of elements after clicking on filter Price high to low
        List<Double> afterFilterProductPriceList = new ArrayList<>();
        for (WebElement s : afterFilterProductPrice) {
            String afterFilterPrice = s.getText().replaceAll("[E,x,T,a,x,£,:,$]", "").replace(",", "");
            Double afterFilterPriceValue = Double.parseDouble(afterFilterPrice);
            afterFilterProductPriceList.add(afterFilterPriceValue);
        }
        //Verify the Product will arrange in Descending order.
        Assert.assertEquals("Products are not sorted in descending order", afterFilterProductPriceList, beforeFilterProductPriceList);
    }
/*
2. Test name verifyThatUserPlaceOrderSuccessfully()
 * 2.1 Mouse hover on Laptops & Notebooks Tab and click
 * 2.2 Click on “Show All Laptops & Notebooks”
 * 2.3 Select Sort By "Price (High > Low)"
 * 2.4 Select Product “MacBook”
 * 2.5 Verify the text “MacBook”
 * 2.6 Click on ‘Add To Cart’ button
 * 2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
 * 2.8 Click on link “shopping cart” display into success message
 * 2.9 Verify the text "Shopping Cart"
 * 2.10 Verify the Product name "MacBook"
 * 2.11 Change Quantity "2"
 * 2.12 Click on “Update” Tab
 * 2.13 Verify the message “Success: You have modified your shopping cart!”
 * 2.14 Verify the Total £737.45
 * 2.15 Click on “Checkout” button
 * 2.16 Verify the text “Checkout”
 * 2.17 Verify the Text “New Customer”
 * 2.18 Click on “Guest Checkout” radio button
 * 2.19 Click on “Continue” tab
 * 2.20 Fill the mandatory fields
 * 2.21 Click on “Continue” Button
 * 2.22 Add Comments About your order into text area
 * 2.23 Check the Terms & Conditions check box
 * 2.24 Click on “Continue” button
 * 2.25 Verify the message “Warning: Payment method required!”
 */

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        mouseHoverToElementAndClick(By.xpath("//span[normalize-space()='Currency']"));//Mouse hover on Currency Dropdown and click
        mouseHoverToElementAndClick(By.xpath("//button[normalize-space()='£Pound Sterling']"));   //Mouse hover on £Pound Sterling and click
        mouseHoverToElementAndClick(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));   //Mouse hover on Laptops & Notebooks Tab.and click
        clickOnElement(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']")); //Click on “Show All Laptops & Notebooks”
        //Select Sort By "Price (High > Low)"
        selectByValueFromDropDown(By.id("input-sort"), "https://tutorialsninja.com/demo/index.php?route=product/category&path=18&sort=p.price&order=DESC");
        clickOnElement(By.linkText("Sony VAIO")); //Select Product “Sony VAIO”
        Assert.assertEquals("Sony VAIO", getTextFromElement(By.xpath("//h1[contains(text(),'Sony VAIO')]"))); //Verify the text “Sony VAIO”
        clickOnElement(By.id("button-cart"));//Click on ‘Add To Cart’ button
        //Verify the message “Success: You have added Sony VAIO to your shopping cart!”
        Assert.assertEquals("Success: You have added Sony VAIO to your shopping cart!", getTextFromElement(By.xpath("//div[contains(text(),'Success')]")).substring(0, 56));
        //Click on link “shopping cart” display into success message
        clickOnElement(By.linkText("shopping cart"));
        //Verify the text "Shopping Cart"
        Assert.assertEquals("Shopping Cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).substring(0, 13));
        //Verify the Product name "Sony VAIO"
        Assert.assertEquals("Sony VAIO", getTextFromElement(By.linkText("Sony VAIO")));
        driver.findElement(By.xpath("//input[@class='form-control']")).clear();  //2.11 Change Quantity "2"
        sendTextToElement(By.xpath("//input[@class='form-control']"), "2");
        clickOnElement(By.xpath("//button[@type='submit']"));// 2.12 Click on “Update” Tab
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        Assert.assertEquals("Success: You have modified your shopping cart!", getTextFromElement(By.xpath("//div[contains(text(),'Success')]")).substring(0, 46));
        Assert.assertEquals("£1,472.45", getTextFromElement(By.xpath("//tbody//tr//td[6]"))); // 2.14 Verify the Total £737.45
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));//2.15Click on “Checkout” button
        //Verify the text “Checkout”
        Assert.assertEquals("Checkout", getTextFromElement(By.xpath("//h1[normalize-space()='Checkout']")));
        //Verify the Text “New Customer”

        verifyExpectedAndActual(By.xpath("//h2[contains(text(),'New Customer')]"),"New Customer");
        clickOnElement(By.xpath("//input[@value='guest']")); //Click on “Guest Checkout” radio button
        clickOnElement(By.id("button-account")); //Click on “Continue” tab
        //Fill the mandatory fields
        sendTextToElement(By.id("input-payment-firstname"), "Kia");
        sendTextToElement(By.id("input-payment-lastname"), "Shah");
        sendTextToElement(By.id("input-payment-email"), "prime123@gmail.com");
        sendTextToElement(By.id("input-payment-telephone"), "01236796229");
        sendTextToElement(By.id("input-payment-address-1"), "malabar tower");
        sendTextToElement(By.id("input-payment-city"), "Brighton");
        sendTextToElement(By.id("input-payment-postcode"), "B929BQ");
        selectByValueFromDropDown(By.id("input-payment-zone"), "3524");//selectByValueFromDropDown(By.id("input-payment-country"), "222");
        clickOnElement(By.id("button-guest"));//Click on “Continue” Button
        //Add Comments About your order into text area
        sendTextToElement(By.xpath("//textarea[@name='comment']"), "I want a macbook");
        clickOnElement(By.id("button-shipping-method"));//Click on “Continue” button
        Thread.sleep(1000);
        clickOnElement(By.xpath("//input[@name='agree']"));
        clickOnElement(By.id("button-payment-method"));
        clickOnElement(By.id("button-confirm"));        //Verify the message “Warning: Payment method required!”
    }

    @After
    public void tearDown () {
        //  closeBrowser();
    }
}
