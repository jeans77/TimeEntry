package com.libertymutual.goforcode.timeless.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.libertymutual.goforcode.timeless.models.TimeEntryItem;

@Service
public class TimeEntryRepository {

	File inputFile = new File("timeEntry.csv");
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	List<TimeEntryItem> timeEntryItems;

	int itemId;
	String week;
	Double monday;
	Double tuesday;
	Double wednesday;
	Double thursday;
	Double friday;
	Double total;
	String status;
	String buttonChoice;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
	public List<TimeEntryItem> getAll() {

		System.out.println("Repository getAll: starting");
		timeEntryItems = new ArrayList<TimeEntryItem>();
		TimeEntryItem timeEntryItem = null;

		// Create the Update record if not present.
		if (inputFile.length() == 0) {
			try (CSVPrinter printer = new CSVPrinter(new FileWriter(inputFile, false), CSVFormat.DEFAULT)) {
				printer.printRecord("yyyy-MM-dd",0.0,0.0,0.0,0.0,0.0,0.0,"U");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try (FileReader fr = new FileReader(inputFile);
					CSVParser csvParser = new CSVParser(fr, CSVFormat.DEFAULT)) {
				// Read CSV file
				int i = 0;
				for (CSVRecord entry : csvParser) {
						System.out.println("Repository FileReadingLoop (week): " + entry.get(0));
					timeEntryItem = new TimeEntryItem();
//					timeEntryItem.setWeek(String.valueOf(entry.get(0)));

					
				    //week = formatter.parse(entry.get(0));
					timeEntryItem.setWeek(String.valueOf(entry.get(0)));
					timeEntryItem.setMonday(Double.valueOf(entry.get(1)));
					timeEntryItem.setTuesday(Double.valueOf(entry.get(2)));
					timeEntryItem.setWednesday(Double.valueOf(entry.get(3)));
					timeEntryItem.setThursday(Double.valueOf(entry.get(4)));
					timeEntryItem.setFriday(Double.valueOf(entry.get(5)));
					timeEntryItem.setTotal(Double.valueOf(entry.get(6)));
					timeEntryItem.setStatus(entry.get(7));
					timeEntryItems.add(i, timeEntryItem);
					i++;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Reverse rows order in array
		for (int i = 0; i < timeEntryItems.size() / 2; i++) {
			TimeEntryItem temp = timeEntryItems.get(i);
			timeEntryItems.set(i, timeEntryItems.get(timeEntryItems.size() - 1 - i));
			timeEntryItems.set(timeEntryItems.size() - 1 - i, temp);
		}

		return timeEntryItems;
	}

	public void update(TimeEntryItem item, String buttonChoice) {
		Boolean append = false;
		System.out.println("Repository Start update-button: " + buttonChoice);

		for (int i = 0; i < timeEntryItems.size(); i++) {
			// System.out.println("Repository --- item(-" + i + "-) : " +
			// timeEntryItems.get(i).toString());

			List<String> record = new ArrayList<String>();

			if (timeEntryItems.get(i).getStatus().equals("U")) {
				status = "U";
			} else {
				status = "S";
			}
			
			if (buttonChoice == "update") {
				System.out.println("Repository --- Starting Update-----------");
				if (status == "U") {
					System.out.println("Repository --- Update of a U-----------");
					record.add(item.getWeek());
					record.add(Double.toString(item.getMonday()));
					record.add(Double.toString(item.getTuesday()));
					record.add(Double.toString(item.getWednesday()));
					record.add(Double.toString(item.getThursday()));
					record.add(Double.toString(item.getFriday()));
					// record.add(Double.toString(item.getTotal()));
					record.add(Double.toString(item.getMonday() + item.getTuesday() + item.getWednesday()
							+ item.getThursday() + item.getFriday()));
					record.add("U");

				} else {
					System.out.println("Repository --- Update of a S-----------");
					record.add(timeEntryItems.get(i).getWeek());
					record.add(Double.toString(timeEntryItems.get(i).getMonday()));
					record.add(Double.toString(timeEntryItems.get(i).getTuesday()));
					record.add(Double.toString(timeEntryItems.get(i).getWednesday()));
					record.add(Double.toString(timeEntryItems.get(i).getThursday()));
					record.add(Double.toString(timeEntryItems.get(i).getFriday()));
					record.add(Double.toString(timeEntryItems.get(i).getTotal()));
					record.add(timeEntryItems.get(i).getStatus());

				}
				
			} else {

				if (status == "S") {
					System.out.println("Repository --- Submit of a S-----------");
					record.add(timeEntryItems.get(i).getWeek());
					record.add(Double.toString(timeEntryItems.get(i).getMonday()));
					record.add(Double.toString(timeEntryItems.get(i).getTuesday()));
					record.add(Double.toString(timeEntryItems.get(i).getWednesday()));
					record.add(Double.toString(timeEntryItems.get(i).getThursday()));
					record.add(Double.toString(timeEntryItems.get(i).getFriday()));
					record.add(Double.toString(timeEntryItems.get(i).getTotal()));
					record.add(timeEntryItems.get(i).getStatus());

				} else {
					
					System.out.println("Repository --- Submit of a U-----------");

					try (CSVPrinter printer = new CSVPrinter(new FileWriter(inputFile, append), CSVFormat.DEFAULT)) {
						append = true;
						printer.printRecord("yyyy-MM-dd",0.0,0.0,0.0,0.0,0.0,0.0,"U");
						System.out.println("Repository   Creating U Record : ");
					} catch (IOException e) {
						e.printStackTrace();
					}
					record.add(String.valueOf(item.getWeek()));
					record.add(Double.toString(item.getMonday()));
					record.add(Double.toString(item.getTuesday()));
					record.add(Double.toString(item.getWednesday()));
					record.add(Double.toString(item.getThursday()));
					record.add(Double.toString(item.getFriday()));
					record.add(Double.toString(item.getTotal()));
//					record.add(Double.toString(item.getMonday() + item.getTuesday() + item.getWednesday() + item.getThursday() + item.getFriday()));
					record.add("S");
				}
			}
			
			try (CSVPrinter printer = new CSVPrinter(new FileWriter(inputFile, append), CSVFormat.DEFAULT)) {
				System.out.println("Repository --- Writing record " + record);
				printer.printRecord(record);
				append = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}