package com.ss.ita.rozetka.pageobject.elements.filters;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class FilterSideBar {
    private final String minPriceFieldXpath = "//rz-filter-stack//input[@formcontrolname='min']";
    private final String maxPriceFieldXpath = "//rz-filter-stack//input[@formcontrolname='max']";
    private final String priceOkButtonXpath = "//rz-filter-slider//fieldset//button";

    @Step("FilterSideBar: get filter with name {filterName}")
    public Filter getFilter(String filterName) {
        String filterXpath = String.format("//rz-filter-stack/div[@data-filter-name='%s']", filterName);
        $x(filterXpath).should(exist);
        return new Filter(filterXpath);
    }

    public Filter getFilter(FilterName filterName) {
        return getFilter(filterName.getFilterName());
    }

    @Step("FilterSideBar: get filter names")
    public List<String> getFilterNames() {
        return $$x("//rz-filter-stack/div[contains(@class,'sidebar-block')]")
                .stream()
                .map(element -> element.getAttribute("data-filter-name"))
                .collect(Collectors.toList());
    }

    @Step("FilterSideBar: get minimum price bound")
    public int getMinPrice() {
        String minPrice = $x(minPriceFieldXpath).getAttribute("value");
        return Integer.parseInt(minPrice);
    }

    @Step("FilterSideBar: set minimum price bound to {minPrice}")
    public FilterSideBar setMinPrice(int minPrice) {
        SelenideElement minPriceField = $x(minPriceFieldXpath);
        minPriceField.click();
        minPriceField.clear();
        minPriceField.sendKeys(String.valueOf(minPrice));
        return this;
    }

    @Step("FilterSideBar: get maximum price bound")
    public int getMaxPrice() {
        String maxPrice = $x(maxPriceFieldXpath).getAttribute("value");
        return Integer.parseInt(maxPrice);
    }

    @Step("FilterSideBar: set maximum price bound to {maxPrice}")
    public FilterSideBar setMaxPrice(int maxPrice) {
        SelenideElement maxPriceField = $x(maxPriceFieldXpath);
        maxPriceField.click();
        maxPriceField.clear();
        maxPriceField.sendKeys(String.valueOf(maxPrice));
        return this;
    }

    @Step("FilterSideBar: get price correctness status")
    public boolean isPriceRangeValid() {
        return $x(priceOkButtonXpath).isEnabled();
    }

    @Step("FilterSideBar: submit price filter")
    public FilterSideBar filterByPrice() {
        $x(priceOkButtonXpath).click();
        return this;
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Filter {
        private final String filterContainerXpath;

        private SelenideElement getOptionCheckBox(String optionName) {
            return $x(format(filterContainerXpath + "//input[@id='%s']", optionName));
        }

        private SelenideElement getOptionCheckBoxLink(String optionName) {
            return $x(format(filterContainerXpath + "//input[@id='%s']/parent::a", optionName));
        }

        private SelenideElement getSearchField() {
            String searchFieldXpathPostfix = "//div[@class='sidebar-search']/input";
            return $x(filterContainerXpath + searchFieldXpathPostfix);
        }

        private ElementsCollection getAllOptionCheckBoxes() {
            return $$x(filterContainerXpath + "//input[@type='checkbox']");
        }

        @Step("Filter: get options visibility status")
        public boolean isFilterBlockToggledOn() {
            return $x(filterContainerXpath + "//rz-filter-checkbox").is(visible);
        }

        @Step("Filter: toggle filter block")
        public Filter toggleFilterBlock() {
            $x(filterContainerXpath + "//button[contains(@class,'sidebar-block__toggle')]")
                    .scrollIntoView(false)
                    .click();
            return this;
        }

        @Step("Filter: get filter title")
        public String getTitle() {
            return $x(filterContainerXpath + "//span[@class='sidebar-block__toggle-title']")
                    .text()
                    .replaceAll("\\d", EMPTY)
                    .trim();
        }

        @Step("Filter: get filter options")
        public List<String> getOptionNames() {
            return $$x(filterContainerXpath + "//input[@class='custom-checkbox']/following-sibling::label")
                    .texts()
                    .stream()
                    .map(name -> name.replaceAll("[\\(]\\d+[\\)]", EMPTY))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }

        @Step("Filter: select option with name {optionName}")
        public Filter selectOption(String optionName) {
            SelenideElement optionCheckBox = getOptionCheckBox(optionName);

            optionCheckBox.shouldNotBe(checked);
            getOptionCheckBoxLink(optionName).scrollIntoView(false).click();

            optionCheckBox.shouldBe(checked);
            return this;
        }

        @Step("Filter: unselect option with name {optionName}")
        public Filter unselectOption(String optionName) {
            SelenideElement optionCheckBox = getOptionCheckBox(optionName);

            optionCheckBox.shouldBe(checked);
            getOptionCheckBoxLink(optionName).scrollIntoView(false).click();

            optionCheckBox.shouldNotBe(checked);
            return this;
        }

        @Step("Filter: get selection status of option with name {optionName}")
        public boolean isOptionSelected(String optionName) {
            return getOptionCheckBox(optionName).isSelected();
        }

        @Step("Filter: get quantity of options in filter")
        public int getOptionsQuantityInFilter() {
            return Integer.parseInt(
                    $x(filterContainerXpath + "//span[contains(@class, 'sidebar-block__toggle-quantity')]").text()
            );
        }

        @Step("Filter: get products quantity that corresponds to option with name {optionName}")
        public int getOptionProductsQuantity(String optionName) {
            String optionQuantityXpath = format(filterContainerXpath + "//label[@for='%s']/span", optionName);

            return Integer.parseInt(
                    $x(optionQuantityXpath)
                            .text()
                            .replaceAll("\\D|\\s", EMPTY)
            );
        }

        @Step("Filter: get search field presence status")
        public boolean hasOptionsFilter() {
            return getSearchField().is(exist);
        }

        @Step("Filter: filter options by term {filterTerm}")
        public Filter filterOptions(String filterTerm) {
            int optionsQuantityBeforeSearch = getAllOptionCheckBoxes().size();

            getSearchField().sendKeys(filterTerm);

            getAllOptionCheckBoxes().shouldHave(sizeLessThan(optionsQuantityBeforeSearch));
            return this;
        }

        @Step("Filter: clear search field")
        public Filter clearOptionsFilter() {
            int optionsQuantityBeforeClearing = getAllOptionCheckBoxes().size();

            $x(filterContainerXpath + "//button[contains(@class,'sidebar-search__clear')]").click();

            getAllOptionCheckBoxes().shouldHave(sizeGreaterThan(optionsQuantityBeforeClearing));
            return this;
        }

        @Step("Filter: get all selected options")
        public List<String> getSelectedOptionsNames() {
            return getAllOptionCheckBoxes()
                    .filter(checked)
                    .stream()
                    .map(element -> element.getAttribute("id"))
                    .collect(Collectors.toList());
        }
    }

}
