package com.example.reportservice.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PdfGenerator {

    public void generate(List<Map<String, Object>> dataList, HttpServletResponse response) throws DocumentException, IOException {

        Map<String, Object> setupData = dataList.get(0);

        // Creating the Object of Document
        Document document = new Document(PageSize.A2);

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
        PdfPTable table = new PdfPTable(setupData.size());
        //Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
//        table.setWidths(new int[]{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3});
//        table.setWidths(new int[]{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3});
        table.setSpacingBefore(5);
        //create Table cells for the table header
        PdfPCell cell = new PdfPCell();
        //Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.ORANGE);
        cell.setPadding(5);

        //Creating font
        //Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        //Adding headings in the created table cell or header
        //Adding Cell to table
        List<String> titleList = new ArrayList<>();

        setupData.forEach((k, v) -> {
            titleList.add(k);
        });

        for (String strTitle : titleList) {
            cell.setPhrase(new Phrase(strTitle, font));
            table.addCell(cell);
        }

        for (Map<String, Object> data : dataList) {
            data.forEach((k, v) -> {
                if (v != null) {
                    String currentData = v.toString();
                    table.addCell(currentData);
                }
            });
        }

        document.add(table);

        document.close();
    }
}
