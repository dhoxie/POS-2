package com.scottsdaleair;
import com.scottsdaleair.controller.DatabaseGetter;
import com.scottsdaleair.pdfGenerator.PDFInvoice;
import com.scottsdaleair.data.Invoice;
public class App {

  /**
   * Test mainster.
   * 
   * @param args Currently unused list of args
   */
  public static void main(String[] args)throws Throwable {
  	Invoice [] large = DatabaseGetter.getAllInvoices();
  	int count = 0;
  	for(Invoice tmp  :  large){
  		count++;
  		new PDFInvoice(tmp).start();
  		/*if(count  >= 2 ){
  			break;
  		}
  		*/

  	}
  	
  	//new PDFInvoice(Invoice.getFromDb("79136944")).start();
  }

}
