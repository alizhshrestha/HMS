package com.example.reportservice.util;

import com.example.reportservice.model.Account;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class PdfGenerator {

    private Account account;

    public void generate(List<Account> accountList, HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document
        Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        //Opening the created document to change it
        document.open();

        //Creating font
        //Setting font style and size
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        // Creating paragraph
        Paragraph paragraph = new Paragraph("List of accounts", fontTitle);
        //Aligning the paragraph in the document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        //Adding the created paragraph in the document
        document.add(paragraph);
        //Creating a table of the 17 columns
        PdfPTable table = new PdfPTable(17);
        //Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
//        table.setWidths(new int[]{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3});
//        table.setWidths(new int[]{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3});
        table.setSpacingBefore(5);
        //create Table cells for the table header
        PdfPCell cell = new PdfPCell();
        //Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);

        //Creating font
        //Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        //Adding headings in the created table cell or header
        //Adding Cell to table
        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Uuid", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("title", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("addressLine1", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("addressLine2", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("city", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("country", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("zip", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("logo", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("favicon", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("contact", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("phone", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("metaTitle", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("metaKeyword", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("metaDescription", font));
        table.addCell(cell);
//        cell.setPhrase(new Phrase("createdAt", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("updatedAt", font));
//        table.addCell(cell);

        //Iterating the list of accounts
        for (Account account: accountList){
            table.addCell(String.valueOf(account.getId()));
            table.addCell(account.getUuid().toString());
            table.addCell(account.getTitle());
            table.addCell(account.getDescription());
            table.addCell(account.getAddressLine1());
            table.addCell(account.getAddressLine2());
            table.addCell(account.getCity());
            table.addCell(account.getCountry());
            table.addCell(account.getZip());
            table.addCell(account.getLogo());
            table.addCell(account.getFavicon());
            table.addCell(account.getEmail());
            table.addCell(account.getContact());
            table.addCell(account.getPhone());
            table.addCell(account.getMetaTitle());
            table.addCell(account.getMetaKeyword());
            table.addCell(account.getMetaDescription());
//            table.addCell(account.getCreatedAt().toString());
//            table.addCell(account.getUpdatedAt().toString());
        }

        document.add(table);

        document.close();


    }
}
