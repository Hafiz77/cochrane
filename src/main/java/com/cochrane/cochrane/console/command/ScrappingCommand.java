package com.cochrane.cochrane.console.command;

import com.cochrane.cochrane.scraper.SeleniumSiteScraper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;


@ShellComponent
public class ScrappingCommand {
    @ShellMethod("Start scraping")
    public void cochrane(@ShellOption(defaultValue = "1") int topicsNumber)
    {
        SeleniumSiteScraper scraper = new SeleniumSiteScraper();
        scraper.writeToFile(topicsNumber);


    }
}
