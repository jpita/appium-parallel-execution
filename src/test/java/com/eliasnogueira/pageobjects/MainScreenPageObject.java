/*
 * Copyright 2018 Elias Nogueira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eliasnogueira.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class MainScreenPageObject {

	@AndroidFindBy(id = "org.traeg.fastip:id/billAmtEditText")
	MobileElement billAmount;
	
	@AndroidFindBy(id = "org.traeg.fastip:id/calcTipButton")
	MobileElement calculateTip;

	@AndroidFindBy(id = "org.traeg.fastip:id/tipAmtTextView")
	MobileElement tipAmount;

	@AndroidFindBy(id = "org.traeg.fastip:id/totalAmtTextView")
	MobileElement totalAmount;

    @AndroidBy(id = "org.traeg.fastip:id/calcTipButton")

    public MainScreenPageObject(AppiumDriver<?> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

	public void fillBillAmount(String amount) {
		billAmount.sendKeys(amount);
	}

	public void clickCalculateTip() {
		calculateTip.click();
	}
	
	public String getTipAmount() {
		return tipAmount.getText();
	}
	
	public String getTotalAmount() {
		return totalAmount.getText();
	}
	
}
